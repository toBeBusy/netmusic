package music.spider.service;

import music.spider.jooq.tables.daos.MusicRankingDao;
import music.spider.jooq.tables.pojos.MusicRanking;
import music.spider.util.TimeUtil;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static music.spider.jooq.Tables.MUSIC_RANKING;
import static music.spider.jooq.Tables.MUSIC_SONG;

/**
 * Created by qiupeng on 2017/6/20.
 */
@Component
public class RankingService {

    @Autowired
    private MusicRankingDao musicRankingDao;

    @Autowired
    private DSLContext sql;

    public void updateRanking(List<MusicRanking> musicRankingList){
        for(int i = 0; i < musicRankingList.size(); i++){
            musicRankingList.get(i).setId((long) i + 1);
            musicRankingList.get(i).setUpdateTime(TimeUtil.now());
        }
        //外键约束，删排行榜之前要先删歌曲
        sql.delete(MUSIC_SONG).execute();
        sql.delete(MUSIC_RANKING).execute();
        musicRankingDao.insert(musicRankingList);
    }
}
