/**
 * This class is generated by jOOQ
 */
package music.spider.jooq;


import javax.annotation.Generated;

import music.spider.jooq.tables.MusicComment;
import music.spider.jooq.tables.MusicRanking;
import music.spider.jooq.tables.MusicSong;
import music.spider.jooq.tables.records.MusicCommentRecord;
import music.spider.jooq.tables.records.MusicRankingRecord;
import music.spider.jooq.tables.records.MusicSongRecord;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.AbstractKeys;


/**
 * A class modelling foreign key relationships between tables of the <code>music_spider</code> 
 * schema
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<MusicCommentRecord, Long> IDENTITY_MUSIC_COMMENT = Identities0.IDENTITY_MUSIC_COMMENT;
    public static final Identity<MusicRankingRecord, Long> IDENTITY_MUSIC_RANKING = Identities0.IDENTITY_MUSIC_RANKING;
    public static final Identity<MusicSongRecord, Long> IDENTITY_MUSIC_SONG = Identities0.IDENTITY_MUSIC_SONG;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<MusicCommentRecord> KEY_MUSIC_COMMENT_PRIMARY = UniqueKeys0.KEY_MUSIC_COMMENT_PRIMARY;
    public static final UniqueKey<MusicRankingRecord> KEY_MUSIC_RANKING_PRIMARY = UniqueKeys0.KEY_MUSIC_RANKING_PRIMARY;
    public static final UniqueKey<MusicSongRecord> KEY_MUSIC_SONG_PRIMARY = UniqueKeys0.KEY_MUSIC_SONG_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<MusicCommentRecord, MusicSongRecord> COMMENT_SONG_FORGIN_KEY = ForeignKeys0.COMMENT_SONG_FORGIN_KEY;
    public static final ForeignKey<MusicSongRecord, MusicRankingRecord> SONG_RANKING_FORGIN_KEY = ForeignKeys0.SONG_RANKING_FORGIN_KEY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 extends AbstractKeys {
        public static Identity<MusicCommentRecord, Long> IDENTITY_MUSIC_COMMENT = createIdentity(MusicComment.MUSIC_COMMENT, MusicComment.MUSIC_COMMENT.ID);
        public static Identity<MusicRankingRecord, Long> IDENTITY_MUSIC_RANKING = createIdentity(MusicRanking.MUSIC_RANKING, MusicRanking.MUSIC_RANKING.ID);
        public static Identity<MusicSongRecord, Long> IDENTITY_MUSIC_SONG = createIdentity(MusicSong.MUSIC_SONG, MusicSong.MUSIC_SONG.ID);
    }

    private static class UniqueKeys0 extends AbstractKeys {
        public static final UniqueKey<MusicCommentRecord> KEY_MUSIC_COMMENT_PRIMARY = createUniqueKey(MusicComment.MUSIC_COMMENT, "KEY_music_comment_PRIMARY", MusicComment.MUSIC_COMMENT.ID);
        public static final UniqueKey<MusicRankingRecord> KEY_MUSIC_RANKING_PRIMARY = createUniqueKey(MusicRanking.MUSIC_RANKING, "KEY_music_ranking_PRIMARY", MusicRanking.MUSIC_RANKING.ID);
        public static final UniqueKey<MusicSongRecord> KEY_MUSIC_SONG_PRIMARY = createUniqueKey(MusicSong.MUSIC_SONG, "KEY_music_song_PRIMARY", MusicSong.MUSIC_SONG.ID);
    }

    private static class ForeignKeys0 extends AbstractKeys {
        public static final ForeignKey<MusicCommentRecord, MusicSongRecord> COMMENT_SONG_FORGIN_KEY = createForeignKey(music.spider.jooq.Keys.KEY_MUSIC_SONG_PRIMARY, MusicComment.MUSIC_COMMENT, "comment_song_forgin_key", MusicComment.MUSIC_COMMENT.SONG_ID);
        public static final ForeignKey<MusicSongRecord, MusicRankingRecord> SONG_RANKING_FORGIN_KEY = createForeignKey(music.spider.jooq.Keys.KEY_MUSIC_RANKING_PRIMARY, MusicSong.MUSIC_SONG, "song_ranking_forgin_key", MusicSong.MUSIC_SONG.RANKING_ID);
    }
}
