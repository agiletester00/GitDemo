import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import payloads.*;

import static io.restassured.RestAssured.*;
public class RestasssuredDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Add Place 
		//Update place
		//get updated Place
		
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
			String response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
			.body(payloads.AddPlace()).when().post("maps/api/place/add/json")
			.then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP")).header("server","Apache/2.4.18 (Ubuntu)").extract().response().asString();
		System.out.println(response);
		
		
		  JsonPath jp=new JsonPath(response); 
		  String placeid=jp.getString("place_id");
		  System.out.println(placeid);
		  
		
		  //Update Place
		  
		  
		  String newadd="70 dammaiguda, INDIA";
		 given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		 .body("{\r\n"
					+ "\"place_id\":\""+placeid+"\",\r\n"
					+ "\"address\":\""+newadd+"\",\r\n"
					+ "\"key\":\"qaclick123\"\r\n"
					+ "}\r\n"+"").when().put("maps/api/place/update/json")
		  .then().log().all().assertThat().statusCode(200).body("msg",equalTo( "Address successfully updated"));
		 
		 //getPlace
		 
		 String vnewadd=given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeid)
		 .when().post("maps/api/place/get/json").then().log().all().assertThat().statusCode(200).body("address",equalTo(newadd)).extract().response().asString();
	
		 JsonPath njp=new JsonPath(vnewadd);
		 String nadd=njp.getString("address");
		 System.out.println(nadd);
		 
		 Assert.assertEquals(newadd,nadd);
		 
	}

}
