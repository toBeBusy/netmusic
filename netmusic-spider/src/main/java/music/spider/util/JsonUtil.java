package music.spider.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import music.spider.model.SongModel;

import java.util.List;

public class JsonUtil {
	private static Gson gson = new Gson();
	
	public static List<SongModel> getObject(String jsonString){
		List<SongModel> entitys = gson.fromJson(jsonString, new TypeToken<List<SongModel>>(){}.getType());
		return entitys;
	}
}
