package actions;

import java.util.List;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import runner.Inputs;
import io.restassured.path.json.JsonPath;

public class ActionClass {

	public static String petStatus = null;
	public static Response responseBody;
	public static String id = null;

	public static int API_GetStatus(String parameter) {
		RestAssured.baseURI = Inputs.Endpoint;
		if(parameter.contains("-")) {
			
		}
		responseBody = given().log().all().get(parameter).then().log().all().extract().response();
		int statuscode = responseBody.getStatusCode();
		System.out.println(responseBody.asString());
		return statuscode;

	}

	public static String API_GetRates() {
		String concat = "";
		String response = responseBody.asString();
		JsonPath js = APIUtility.getValueJsonPath(response);
		String[] rates = (js.getString("rates")).replace("[", "").replace("]", "").split(",");
		for (String str : rates) {
			concat = concat + str + "\n";
		}
		return concat;
	}

	public static int API_GetStatus(String resource, String filter, List param) {
		RestAssured.baseURI = Inputs.Endpoint;
		responseBody = given().log().all().queryParam(filter, param).get(resource).then().log().all().extract()
				.response();
		int statuscode = responseBody.getStatusCode();

		return statuscode;

	}

	public static boolean API_VerifyRates(String resource, String base, String value) {

		
		JsonPath js = APIUtility.getValueJsonPath(responseBody.asString());
		String values = js.getString("rates." + base);
		if (values.contentEquals(value))
			return true;
		else
			return false;

	}

	public static int API_GetStatus(String resource, String base, String symbol, List<String> lst) {
		responseBody = given().log().all().queryParam(base, lst.get(0)).queryParam(symbol, lst.get(1)).get(resource)
				.then().log().all().extract().response();

		int statuscode = responseBody.getStatusCode();
		System.out.println(responseBody.asString());
		return statuscode;
	}

}
