package service;

import music.spider.NetMusicSpiderApplication;
import music.spider.configuration.MusicSpiderConfiguration;
import music.spider.jooq.tables.pojos.MusicRanking;
import music.spider.service.SpiderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiupeng on 2017/6/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= NetMusicSpiderApplication.class)
//@ContextConfiguration("../../application.properties")
public class SpiderServiceTest {

    @Autowired
    private SpiderService spiderService;

    @Autowired
    private MusicSpiderConfiguration configuration;

    @Test
    public void testDoSpider() throws IOException {
//        http://music.163.com/#/discover/toplist?id=3779629
//        spiderService.doSpider(configuration.getUrl());
        List<MusicRanking> list = new ArrayList<>();
        MusicRanking model = new MusicRanking();
        model.setUrl("http://music.163.com/discover/toplist?id=3779629");
        list.add(model);
        spiderService.doSpider("http://music.163.com/discover/toplist/");
    }

}
