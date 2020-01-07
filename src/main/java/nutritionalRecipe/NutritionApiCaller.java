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
	
	/** 
	 * Gets nutritional info for an ingredient
	 * @param ingredient the ingredient you want to get nutritional info for
	 */
	public int makeNutritionalCall(Ingredient ingredient) {
		String name = urlEncodeString(ingredient.getName());
		String requestUrl = nutritionURLBase + "parser?ingr=" + name +
				"&app_id=" + appId + "&app_key="  + appKey; 
		Request request = new Request.Builder()
				.url(requestUrl)
				.build();
		try {
			Response response = client.newCall(request).execute();
			JSONParser parser = new JSONParser();
			try {
				JSONObject json = (JSONObject) parser.parse(response.body().string());
				JSONArray temp = (JSONArray) json.get("hints");
				if (temp.size() == 0) {
					return -1;
				}
				JSONObject currItem = (JSONObject) temp.get(0);
				JSONObject currItemInfo = (JSONObject) currItem.get("food");
				String foodId = (String) currItemInfo.get("foodId");
				String ingredientJSON = makePostRequestBody(ingredient.getUnitOfMeasure(),
						foodId);
				String nutritionalInfoJSONString = doPostRequest(ingredientJSON);
				if (nutritionalInfoJSONString == null) {
					return -1;
				}
				if (parsePostRequest(ingredient, nutritionalInfoJSONString) == 1) {
					return 1;
				} else {
					return -1;
				}		
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
			if (json.containsKey("totalNutrients")) {
				json = (JSONObject) json.get("totalNutrients");
			} else {
				return -1;
			}
			
			// Get and set calories
			JSONObject currJson = (JSONObject) json.get("ENERC_KCAL");
			if (currJson != null) {
				double currVal = (Double) currJson.get("quantity");
				ingredient.setCalories((int) (currVal * ingredient.getAmount())); 
			} else {
				ingredient.setCalories(0);
			}
			
			// Get and set carbs
			currJson = (JSONObject) json.get("CHOCDF");
			if (currJson != null) {
				double currVal = (Double) currJson.get("quantity");
				ingredient.setCarbs((int) (currVal * ingredient.getAmount())); 
			} else {
				ingredient.setCarbs(0);
			}
			
			// Get and set protein
			currJson = (JSONObject) json.get("PROCNT");
			if (currJson != null) {
				double currVal = (Double) currJson.get("quantity");
				ingredient.setProtein((int) (currVal * ingredient.getAmount())); 
			} else {
				ingredient.setProtein(0);
			}
			
			// Get and set fat
			currJson = (JSONObject) json.get("FAT");
			if (currJson != null) {
				double currVal = (Double) currJson.get("quantity");
				ingredient.setFat((int) (currVal * ingredient.getAmount())); 
			} else {
				ingredient.setFat(0);
			}
			
			// Get and set sugar
			currJson = (JSONObject) json.get("SUGAR");
			if (currJson != null) {
				double currVal = (Double) currJson.get("quantity");
				ingredient.setSugar((int) (currVal * ingredient.getAmount())); 
			} else {
				ingredient.setSugar(0);
			}
			
			// Get and set fiber
			currJson = (JSONObject) json.get("FIBTG");
			if (currJson != null) {
				double currVal = (Double) currJson.get("quantity");
				ingredient.setFiber((int) (currVal * ingredient.getAmount())); 
			} else {
				ingredient.setFiber(0);
			}
			
			return 1;
		} catch (ParseException e) {
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
		Ingredient test = new Ingredient("XXXYYY");
		test.setUnitOfMeasure("Gram");
		test.setAmount(100);
		NutritionApiCaller nac = new NutritionApiCaller();
		nac.makeNutritionalCall(test);
		System.out.println(test);
	}
}
