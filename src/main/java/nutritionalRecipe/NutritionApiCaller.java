package nutritionalRecipe;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import org.json.simple.JSONObject;

public class NutritionApiCaller {
	private OkHttpClient client = new OkHttpClient();
	private String nutritionURLBase = "https://api.edamam.com/api/food-database/";
	private String appId = "a18ca0db";
	private String appKey = "d055692ab758629576da744a74a9cfb4";
	
	public NutritionApiCaller() {
		
	}
	
	/**
	 * Gets nutritional info for an ingredient
	 * @param ingredient the ingredient you want to get nutritional info for
	 */
	public void getNutritionalInfo(Ingredient ingredient) {
		String name = urlEncodeString(ingredient.getName());
		String requestUrl = nutritionURLBase + "parser?ingr=" + name +
				"&app_id=" + appId + "&app_key="  + appKey; 
		Request request = new Request.Builder()
				.url(requestUrl)
				.build();
		try {
			Response response = client.newCall(request).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * URL encodes a string by changing spaces to "%20"
	 * @param s the string to URL encode
	 * @return the URL encoded string
	 */
	public String urlEncodeString(String s) {
		StringBuilder builder = new StringBuilder();
		char[] sNew = s.toCharArray();
		
		for (int i=0; i<sNew.length; i++) {
			if (sNew[i] == ' ') {
				builder.append('%');
				builder.append('2');
				builder.append('0');
			} else {
				builder.append(sNew[i]);
			}
		}
		
		return builder.toString();
	}
}
