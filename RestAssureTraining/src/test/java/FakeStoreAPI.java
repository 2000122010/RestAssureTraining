import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class FakeStoreAPI {

	@Test
	public void validateListSize() {
		Response productsResponse = given()
				.contentType(ContentType.JSON)
				.when()
				.get("https://fakestoreapi.com/products");
		
		assertEquals(200, productsResponse.getStatusCode());
		
		//System.out.println(productsResponse.body().asPrettyString());
		
		List<Object> products = productsResponse.jsonPath().getList("$");
		
		assertEquals(20, products.size());
	}
	
	@Test
	public void validateFourRatingProducts() {
		Response productsResponse = given()
				.contentType(ContentType.JSON)
				.when()
				.get("https://fakestoreapi.com/products");
		
		assertEquals(200, productsResponse.getStatusCode());
		
		List<Object> products = productsResponse.jsonPath().getList("findAll {it.rating.rate >= 4}");
		
		assertEquals(7, products.size());
	}
	
	@Test
	public void validateIdSingleProduct() {
				given()
				.param("id", 12)
				.contentType(ContentType.JSON)
				.when()
				.get("https://fakestoreapi.com/products/12")
				.then()
				.statusCode(200)
				.body("id", equalTo(12));
	}
	
	@Test
	public void createProduct() {
		/*
		 * {
		 		title: 'test product'
		 		price: 13.5,
		 		description: 'lorem ipsum set',
		 		image: 'https://i.pravatar.cc',
		 		category: 'electronic'
		 * }
		 * */
		
		Map<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("title", "Isaac´s product");
		requestBody.put("price", 3.14);
		requestBody.put("description", "Hey, it PI!");
		requestBody.put("image", "image_utl");
		requestBody.put("category", "electronic");
		
		given()
		.body(requestBody)
		.when()
		.post("https://fakestoreapi.com/products")
		;//.then()
		//.assertThat()
		//.body("id", equalTo(24));
		
	}
	
	@Test
	public void updateProduct() {
		/*
		 *  Update a product’s: title, price, and add a custom property.
			Validate:
			-the product’s title was updated.
			-the product’s price was updated.
			-the product’s ignored the custom property
		 * */
		
		/*
		 * fetch('https://fakestoreapi.com/products/7',{
            method:"PUT",
            body:JSON.stringify(
                {
                    title: 'test product',
                    price: 13.5,
                    description: 'lorem ipsum set',
                    image: 'https://i.pravatar.cc',
                    category: 'electronic'
                }
            )
        })
            .then(res=>res.json())
            .then(json=>console.log(json))
		 * */
		//Update the product
		
		Map<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("title", "Isaac");
		requestBody.put("price", 20);		
		requestBody.put("customProperty", "math");
		
		//Response responseUpdate= 
		given()
		.body(requestBody)
		.when()
		.put("https://fakestoreapi.com/products/12")
		.then()
		.statusCode(200)
		.assertThat()
		.body("title", equalTo(12))
		.body("title", equalTo("Isaac"))
		.body("price", equalTo(20))
		.body(not(hasKey("customProperty")));
	}
	
	@Test
	public void getItemByCategory() {
		/*
		 * 	Get all items from category electronics
			validate all elements has the electronics category
		 * */
		//Get item by category
		//https://fakestoreapi.com/products/category/jewelery'
		Response categoryResponse = given()
	            .contentType(ContentType.JSON)
	            .when()
	            .get("https://fakestoreapi.com/products/category/jewelery");

	    assertEquals(200, categoryResponse.getStatusCode());

	    List<Object> products = categoryResponse.jsonPath().getList("$");
	    for (Object product : products) {
	        assertEquals("jewelery", ((Map<String, String>) product).get("category"));
	    }
	}
	
	@Test
	public void deleteProduct() {
		/*
		 *  Delete a product
			Get data from product, then delete it and validate the data of the deleted item.
		 * */
		/*
		 * fetch('https://fakestoreapi.com/products/6',{
            method:"DELETE"
        	})
            .then(res=>res.json())
            .then(json=>console.log(json))*/
		 // Fetch data of the product to be deleted
	    Response productResponseBeforeDeletion = given()
	            .contentType(ContentType.JSON)
	            .when()
	            .get("https://fakestoreapi.com/products/6");

	    assertEquals(200, productResponseBeforeDeletion.getStatusCode());

	    // Delete the product
	    Response deletionResponse = given()
	            .contentType(ContentType.JSON)
	            .when()
	            .delete("https://fakestoreapi.com/products/6");

	    assertEquals(200, deletionResponse.getStatusCode());

	    // Validate the data of the deleted product
	    Response productResponseAfterDeletion = given()
	            .contentType(ContentType.JSON)
	            .when()
	            .get("https://fakestoreapi.com/products/6");

	    // Assuming 404 means the product is not found
	    assertEquals(404, productResponseAfterDeletion.getStatusCode()); 
	}
	
	
}
