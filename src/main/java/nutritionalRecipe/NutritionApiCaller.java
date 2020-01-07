package nutritionalRecipe;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;

public class NutritionApiCaller {
	private OkHttpClient client = new OkHttpClient();
	private String nutritionURLBase = "https://api.edamam.com/api/food-database/";
	private String appId = "a18ca0db";
	private String appKey = "d055692ab758629576da744a74a9cfb4";
	public static final MediaType JSON
    = MediaType.parse("application/json; charset=utf-8");
	
	public NutritionApiCaller() {
		
	}
	
	/** THIS IS NOT FINISHED YET SO IT WON'T WORK PROPERLY
	 * Gets nutritional info for an ingredient
	 * @param ingredient the ingredient you want to get nutritional info for
	 */
	public int makeNutritionalCall(Ingredient ingredient) {
		String name = urlEncodeString(ingredient.getName());
		String requestUrl = nutritionURLBase + "parser?ingr=" + name +
				"&app_id=" + appId + "&app_key="  + appKey; 
		System.out.println(requestUrl);
		Request request = new Request.Builder()
				.url(requestUrl)
				.build();
		try {
			Response response = client.newCall(request).execute();
			JSONParser parser = new JSONParser();
			try {
				JSONObject json = (JSONObject) parser.parse(response.body().string());
				System.out.println(json.keySet());
				JSONArray temp = (JSONArray) json.get("hints");
				JSONObject currItem = (JSONObject) temp.get(0);
				JSONObject currItemInfo = (JSONObject) currItem.get("food");
				String foodId = (String) currItemInfo.get("foodId");
				String ingredientJSON = makePostRequestBody(ingredient.getUnitOfMeasure(),
						foodId);
				System.out.println(ingredientJSON);
				String nutritionalInfoJSONString = doPostRequest(ingredientJSON);
				parsePostRequest(ingredient, nutritionalInfoJSONString);
				return 1;
			} catch (ParseException e) {
				return -1;
			}
			
		} catch (IOException e) {
			return -1;
		}
		
	}
	
	
	private int parsePostRequest(Ingredient ingredient, String JSONstring) {
		JSONParser parser = new JSONParser();
		try {
			JSONObject json = (JSONObject) parser.parse(JSONstring);
			System.out.println(JSONstring);
			System.out.println(json.keySet());
			if (json.containsKey("totalNutrients")) {
				json = (JSONObject) json.get("totalNutrients");
			} else {
				return -1;
			}
			
			// Get and set calories
			JSONObject currJson = (JSONObject) json.get("ENERC_KCAL");
			if (currJson != null) {
				String temp = (String) currJson.get("quantity");
				double currVal = Double.parseDouble(temp);
				ingredient.setCalories((int) (currVal * ingredient.getAmount())); 
			} else {
				ingredient.setCalories(0);
			}
			
			
			
			return 1;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return -1;
		}
		
	}
	
	/**
	 * Makes the post request to get the nutritional info
	 * @param ingredientJSON the JSON string to send in the request body
	 * @return String containing the response with nutritional info or null if there's an error
	 */
	private String doPostRequest(String ingredientJSON) {
		RequestBody body = RequestBody.create(JSON, ingredientJSON);
		String requestUrl = nutritionURLBase + "nutrients?app_id=" + appId + "&app_key="  + appKey;
		Request request = new Request.Builder()
				.url(requestUrl)
				.post(body)
				.build();
		Response response;
		try {
			response = client.newCall(request).execute();
			return response.body().string();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}	
	}
	
	/**
	 * Creates the body for the post request to get nutritional info
	 * @param unitOfMeasure the unit of measure for the ingredient
	 * @param foodId the id of the food
	 * @return String with the body for the post request.
	 */
	public String makePostRequestBody(String unitOfMeasure, String foodId) {
		JSONObject currItemInfo = new JSONObject();
		currItemInfo.put("quantity", 1);
		if (unitOfMeasure.equals("Whole")) {
			unitOfMeasure = "unit";
		} else {
			char[] temp = unitOfMeasure.toCharArray();
			temp[0] = (char) (temp[0] + 32);
			unitOfMeasure = new String(temp);
		}
		String measureURI = "http://www.edamam.com/ontologies/edamam.owl#Measure_";
		System.out.println(measureURI);
		currItemInfo.put("measureURI", measureURI + unitOfMeasure);
		currItemInfo.put("foodId", foodId);
		
		JSONArray ingredients = new JSONArray();
		ingredients.add(currItemInfo);
		
		JSONObject returnObject = new JSONObject();
		returnObject.put("ingredients", ingredients);
		
		return returnObject.toString();
		
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
	
	// THIS IS JSUT FOR TESTING PURPOSES.  DELETE EVENTUALLY
	public static void main (String[] args) {
		Ingredient test = new Ingredient("chicken breast");
		test.setUnitOfMeasure("Gram");
		NutritionApiCaller nac = new NutritionApiCaller();
		nac.makeNutritionalCall(test);
	}
}
