package music.spider.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 * Created by qiupeng on 2017/6/20.
 */
public class HttpUtil {


    private static Connection connection = null;

    /**
     * 获取Jsoup链接
     * @param url
     * @return
     */
    public static Connection getConnection(String url){
        if(connection == null){
            synchronized (HttpUtil.class){
                if(connection == null){
                    connection = Jsoup.connect(url);
                }
            }
        }
        connection.url(url);
        return connection;
    }
}
