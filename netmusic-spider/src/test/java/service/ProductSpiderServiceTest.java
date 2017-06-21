package service;

import music.spider.NetMusicSpiderApplication;
import music.spider.service.ProductSpiderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Created by qiupeng on 2017/6/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= NetMusicSpiderApplication.class)
public class ProductSpiderServiceTest {

    @Autowired
    private ProductSpiderService productSpiderService;

    @Test
    public void test() throws IOException {
        productSpiderService.test();
    }
}
