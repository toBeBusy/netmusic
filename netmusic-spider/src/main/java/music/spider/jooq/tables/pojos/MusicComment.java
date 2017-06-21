/**
 * This class is generated by jOOQ
 */
package music.spider.jooq.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MusicComment implements Serializable {

    private static final long serialVersionUID = 1715792477;

    private Long   id;
    private Long   songId;
    private String comment;

    public MusicComment() {}

    public MusicComment(MusicComment value) {
        this.id = value.id;
        this.songId = value.songId;
        this.comment = value.comment;
    }

    public MusicComment(
        Long   id,
        Long   songId,
        String comment
    ) {
        this.id = id;
        this.songId = songId;
        this.comment = comment;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSongId() {
        return this.songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MusicComment (");

        sb.append(id);
        sb.append(", ").append(songId);
        sb.append(", ").append(comment);

        sb.append(")");
        return sb.toString();
    }
}
