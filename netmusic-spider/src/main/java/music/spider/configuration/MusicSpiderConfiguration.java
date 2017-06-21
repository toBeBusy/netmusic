package music.spider.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by qiupeng on 2017/6/19.
 */
@Component
@ConfigurationProperties(prefix = "spider.root")
public class MusicSpiderConfiguration {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
