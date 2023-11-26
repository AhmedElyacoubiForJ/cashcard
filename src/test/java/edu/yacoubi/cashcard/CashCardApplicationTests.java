package edu.yacoubi.cashcard;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CashCardApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void shouldReturnACashCardWhenDataIsSaved() {
		ResponseEntity<String> response =
				restTemplate.getForEntity(
						"/cashcards/99",
						String.class
				);

		assertThat(response.getStatusCode())
				.isEqualTo(HttpStatus.OK);
		DocumentContext documentContext = JsonPath.parse(response.getBody());
		// test id
		Number id = documentContext.read("$.id");
		assertThat(id).isEqualTo(99);
		// test amount
		Double amount = documentContext.read("$.amount");
		assertThat(amount).isEqualTo(123.45);
	}

	@Test
	void shouldNotReturnACashCardWithAnUnknownId() {
		ResponseEntity<String> response = restTemplate
				.getForEntity("/cashcards/1000", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isBlank();
	}

	@Test
	void shouldCreateANewCashCard() {

		CashCard newCashCard = new CashCard(null, 250.00);
		//CashCard newCashCard = new CashCard(44L, 250.00);
		ResponseEntity<Void> createResponse = restTemplate.postForEntity("/cashcards", newCashCard, Void.class);
		// the origin server SHOULD send a 201 (Created) response
		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		// send a 201 (Created) response containing a Location header field
		// that provides an identifier for the primary resource created
		URI locationOfNewCashCard = createResponse.getHeaders().getLocation();
		// In other words, when a POST request results in the successful creation of a resource,
		// such as a new CashCard, the response should include information
		// for how to retrieve that resource.
		// We'll do this by supplying a URI in a Response Header named "Location".
		ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewCashCard, String.class);
		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
		Number id = documentContext.read("$.id");
		Double amount = documentContext.read("$.amount");

		assertThat(id).isNotNull();
		assertThat(amount).isEqualTo(250.00);
	}
}
