import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
public class Restassur {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		

			

				RestAssured.baseURI="https://rahulshettyacademy.com";
					given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
					.when().get("place_id","c84725316addd8972b72b785b8f95da1").then().log().all().assertThat().statusCode(200);
				
			

		

		
		
		
		
	}

}
