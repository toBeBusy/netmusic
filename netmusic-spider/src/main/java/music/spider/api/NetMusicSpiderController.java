package music.spider.api;

import music.spider.configuration.MusicSpiderConfiguration;
import music.spider.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by qiupeng on 2017/6/16.
 */
@RestController
public class NetMusicSpiderController {

    @Autowired
    private MusicSpiderConfiguration configuration;

    @Autowired
    private SpiderService spiderService;

    @RequestMapping(value="/test", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public String test(){
        try {
            spiderService.doSpider(configuration.getUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configuration.getUrl();
    }
}
