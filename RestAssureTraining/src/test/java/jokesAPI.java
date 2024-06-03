import org.junit.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class jokesAPI {
	@Test
	public void validateCategory() {
		/*  Validate
		 * -The category is the one you specified
		 * -Error is false
		 * */
			given()
			.param("lang", "es")
			.param("ammount", 1)
			.when()
			.get("https://v2.jokeapi.dev/joke/Programming")
			.then()
			.statusCode(200)
			.body("error", equalTo(false))
			.body("category", equalTo("Programming"));
	}
	
	@Test
	public void validateJoke5() {
		/* Validate
		 * Joke should be :
		 * "Sólo hay 10 tipos de personas en este mundo, las que entienden binario y las que no."
		 * */
			 given()
			.param("lang", "es")
			.param("amount", 1)
			.param("idRange", 5)
			.when()
			.get("https://v2.jokeapi.dev/joke/Any")
			.then()
			.statusCode(200)
			.body("joke", equalTo("Sólo hay 10 tipos de personas en este mundo, las que entienden binario y las que no."));
	}
	
	@Test
	public void getTwoPartJoke() {
		/* Validate
		 * - Response body type is twopart
		 * - body has setup and delivery values
		 * - language is spanish
		 * - id <= 6
		 * */
		
		//hasPropety
			given()
			.param("lang", "es")
			.param("ammount", 1)
			.param("type", "twopart")
			.when()
			.get("https://v2.jokeapi.dev/joke")
			.then()
			.statusCode(200);
	}
}
