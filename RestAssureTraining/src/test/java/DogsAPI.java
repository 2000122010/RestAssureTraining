import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import io.restassured.response.Response;

public class DogsAPI {

	@Test
	public void validateDogFacts() {
		Response factsResponse = given()
		.param("limit", 5)
		.when()
		.get("https://dogapi.dog/api/v2/facts?limit=5");
		
		assertEquals(200, factsResponse.statusCode());
		
		//Validate request returned 5 facts
		List<Object> factsList = factsResponse.jsonPath().getList("data");
		assertEquals(5, factsList.size());
		
		//Validate each fact is different from each other
		/*
		 * Option 1: Get set of facts and the size should be 5.
		 * */
		Set<String> facts = new HashSet<String>();
		for(Object item: factsList) {
			Map<String, Object> fact = (Map<String, Object>) item;
			
			Map<String, String> attribute  = (Map<String, String>) fact.get("attributes");
			String body = attribute.get("body");
			facts.add(body);
		}
		
		assertEquals(5, facts.size());
		
		/*
		 * Option 2: Get the fact list directly and initialize a Set with it.
		 * Then compare its size. Should be 5.
		 * */
		List<String> factsRaw = factsResponse.jsonPath().getList("data.attributes.body");
		Set<String> factsUnique = new HashSet<String>(factsRaw);
		assertEquals(5, factsUnique.size());
	}
	
	@Test
	public void validateDogBreeds() {
		Response BreedsResponse = given()
				.when()
				.get("https://dogapi.dog/api/v2/breeds");
		
		assertEquals(200, BreedsResponse.statusCode());
		
		//Validate how many breeds exists
		List<Object> breedsList = BreedsResponse.jsonPath().getList("data");
		int howManyBreeds = breedsList.size();
		
		//Validate each breed is different from each other
		Set<String> breeds = new HashSet<String>();
		for(Object item: breedsList) {
			Map<String, Object> breed = (Map<String, Object>) item;
			
			Map<String, String> names  = (Map<String, String>) breed.get("attributes");
			String name = names.get("name");
			breeds.add(name);
		}
		
		assertEquals(howManyBreeds, breeds.size());
		//assertEquals(howManyBreeds-1, breeds.size());
		//assertEquals(howManyBreeds+1, breeds.size());
	}
	
	@Test
	public void validateDogGroups() {
		Response GroupsResponse = given()
				.when()
				.get("https://dogapi.dog/api/v2/breeds");
		
		assertEquals(200, GroupsResponse.statusCode());
		
		//Validate how many groups exists
		List<Object> groupsList = GroupsResponse.jsonPath().getList("data");
		int howManyRecords = groupsList.size();
		
		//Validate each group is different from each other
		Set<String> groups = new HashSet<String>();
		for(Object item: groupsList) {
			Map<String, Object> breed = (Map<String, Object>) item;
			
			Map<String, String> names  = (Map<String, String>) breed.get("attributes");
			String name = names.get("name");
			groups.add(name);
		}
		
		
		System.out.println(howManyRecords);
		System.out.println(groups.size());
		
		assertEquals(howManyRecords, groups.size());
		//assertEquals(howManyBreeds-1, breeds.size());
		//assertEquals(howManyBreeds+1, breeds.size());
		
		
		
		
	}
}
