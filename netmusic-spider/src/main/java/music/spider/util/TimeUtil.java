package music.spider.util;

import java.sql.Timestamp;

/**
 * Created by qiupeng on 2017/6/20.
 */
public class TimeUtil {
    public static Timestamp now(){
        return new Timestamp(System.currentTimeMillis());
    }
}
