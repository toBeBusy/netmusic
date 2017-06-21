package music.spider.service;

import music.spider.configuration.MusicSpiderConfiguration;
import music.spider.jooq.tables.pojos.MusicRanking;
import music.spider.jooq.tables.pojos.MusicSong;
import music.spider.model.Artist;
import music.spider.model.SongModel;
import music.spider.util.HttpUtil;
import music.spider.util.JsonUtil;
import music.spider.util.TimeUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by qiupeng on 2017/6/19.
 */
@Service
public class SpiderService {

    @Autowired
    private MusicSpiderConfiguration confniguration;

    @Autowired
    private RankingService rankingService;

    @Autowired
    private SongService songService;

    public void doSpider(String url) throws IOException {
        //获取链接
        Connection connection = Jsoup.connect(url);

        //设置链接参数
        this.paramSeter(connection);

        //获取返回的html文件
        Document document = connection.get();

        //获取所有的超链接
        Elements elements = document.getElementsByTag("a");

        //获取排行榜超链接
        List<MusicRanking> urlList = this.getRankingList(elements);

        //更新数据库排行榜
        rankingService.updateRanking(urlList);

        List<MusicSong> songs = this.getSongInfo(urlList);
        this.getCommentInfo(songs);
        songService.insertSongs(songs);
    }


    /**
     * 设置请求头
     **/
    private void paramSeter(Connection connection){
        connection.header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        connection.header("Accept-Encoding", "gzip, deflate, sdch");
        connection.header("Accept-Language", "zh-CN,zh;q=0.8");
        connection.header("Connection", "keep-alive");
        connection.header("Host", "music.163.com");
        connection.header("Referer", "http://music.163.com/");
        connection.header("Upgrade-Insecure-Requests", "1");
        connection.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36");
        connection.cookie("JSESSIONID-WYYY", "994e65d00d8b0c36ce913ca37bab10ad3869f40f5a43fe90e8515ca3a013f570a22dc361c38f16f4ecb7e03ff942ec57ecdc260f938bc1d49d732caacfb7772c2b19686a87b8b33d7facccf48d224da5b312b60fa6eb57f461341f67390f6361278f26786e9f3667e1800fec1372804008ad9c1584e9cdd3a5bdb376e573fdc15659e288%3A1475301121073");
        connection.cookie("_iuqxldmzr_","25");
        connection.cookie("__utma","94650624.1768368046.1475299324.1475299324.1475300885.2");
        connection.cookie("__utmb","94650624.4.10.1475300885");
        connection.cookie("__utmc","94650624");
        connection.cookie("__utmz","94650624.1475300885.2.2.utmcsr=baidu|utmccn=(organic)|utmcmd=organic");
        connection.cookie("_ntes_nnid","a96d908bf2183997b0f770db41c95565,1475300954822");
        connection.cookie("_ntes_nuid","a96d908bf2183997b0f770db41c95565");
    }

    /**
     * 获取排行榜单信息
     * @param elements
     * @return
     */
    private List<MusicRanking> getRankingList(Elements elements){
        List<MusicRanking> urlList = new ArrayList<>();

        elements.forEach(element -> {
            if(checkRegular(element)){
                MusicRanking musicRanking = new MusicRanking();
                musicRanking.setName(element.html());
                musicRanking.setUrl("http://music.163.com" + element.attr("href"));
                urlList.add(musicRanking);
            }
        });
        return urlList;
    }

    /**
     * 判断该链接是否为排行榜超链接
     * @param element 超链接
     * @return
     */
    private boolean checkRegular(Element element){
        //排行榜超链接的格式为"/discover/toplist?id=3779629"
        Pattern patt = Pattern. compile("/discover/toplist\\?id={1}[0-9]*");
        Matcher matcher = patt.matcher(element.attr("href"));
        return matcher.matches() && element.getElementsByTag("img").isEmpty();
    }

    /**
     * 获取每个榜单的歌曲信息，综合返回，已经排序好了
     * @param rankingList
     * @return
     */
    public List<MusicSong> getSongInfo(List<MusicRanking> rankingList){
        List<MusicSong> songs = new ArrayList<>();
        final Long[] songId = {1L};
        rankingList.forEach(ranking -> {
            //获取链接
            Connection connection = Jsoup.connect(ranking.getUrl());
            Document document = null;
            //设置链接参数
            this.paramSeter(connection);
            try {
                document = connection.get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //获取歌曲信息
            Element textareaElement = document.getElementsByTag("textarea").get(0);
            System.out.println(textareaElement.html());
            List<SongModel> songList = JsonUtil.getObject(textareaElement.html());
            //设置排名
            for(int i = 0; i < songList.size(); i++){
                SongModel songModel = songList.get(i);
                songModel.setRankingNo(i + 1);
            }
            songs.addAll(this.dataConvertData(songList, ranking, songId));
        });
        return songs;
    }

    private List<MusicSong> dataConvertData(List<SongModel> models, MusicRanking musicRanking, final Long[] songId){
        List<MusicSong> songList = new ArrayList<>();
        models.forEach(model -> {
            MusicSong song = new MusicSong();
            song.setId(songId[0]);
            songId[0]++;
            song.setRankingId(musicRanking.getId());
            song.setRankingNo(model.getRankingNo());
            song.setName(model.getName());
            song.setUrl("http://music.163.com/song?id=" + model.getId());
            List<Artist> artists = model.getArtists();
            StringBuilder sb = new StringBuilder();
            //歌手不为空
            if(!CollectionUtils.isEmpty(artists)){
                artists.forEach(a -> {
                    sb.append(a.getName()).append("；");
                });
            }
            song.setSinger(sb.toString());
            song.setTime(model.getDuration());
            song.setUpdateTime(TimeUtil.now());
            songList.add(song);
        });
        //排序
        songList.sort(Comparator.comparing(MusicSong :: getRankingId));
        return songList;
    }

    public void getCommentInfo(List<MusicSong> songList){
        songList.forEach(song -> {
            Connection connection = HttpUtil.getConnection(song.getUrl());
            Document document = null;
            try {
                document = connection.get();
                System.out.println(document.html());
                System.out.println(document.data());
                Elements elements = document.getElementsByTag("textarea");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
