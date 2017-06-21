package music.spider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class NetMusicSpiderApplication {

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String test(){
		return "collection success！";
	}

	public static void main(String[] args) {
		SpringApplication.run(NetMusicSpiderApplication.class, args);
	}

}
