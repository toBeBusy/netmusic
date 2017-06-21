package music.spider.service;

import music.spider.jooq.tables.daos.MusicSongDao;
import music.spider.jooq.tables.pojos.MusicSong;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static music.spider.jooq.tables.MusicSong.MUSIC_SONG;

/**
 * Created by qiupeng on 2017/6/20.
 */
@Component
public class SongService {

    @Autowired
    private MusicSongDao musicSongDao;

    @Autowired
    private DSLContext sql;

    public void insertSongs(List<MusicSong> songs){
        sql.delete(MUSIC_SONG).execute();
        musicSongDao.insert(songs);
    }
}
