package music.spider.service;

import music.spider.util.HttpUtil;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by qiupeng on 2017/6/21.
 */
@Service
public class ProductSpiderService {

    public void test() throws IOException {
        Connection connection = HttpUtil.getConnection("https://www.etao.com");
        Document document = connection.get();
        Elements elements = document.getAllElements();
        elements.forEach(e -> {
           System.out.println(e.data());
        });
    }
}
