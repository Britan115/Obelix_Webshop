package ch.bbw.obelix.webshop;

import java.util.List;
import java.util.UUID;

import ch.bbw.obelix.quarry.api.DecorativenessDto;
import ch.bbw.obelix.quarry.api.MenhirDto;
import ch.bbw.obelix.quarry.api.QuarryApi;
import ch.bbw.obelix.webshop.dto.BasketDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ObelixWebshopApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private QuarryApi quarryApi;

	private UUID testMenhirId;
	private MenhirDto testMenhir;

	@BeforeEach
	void setUp() {
		testMenhirId = UUID.randomUUID();
		testMenhir = new MenhirDto(testMenhirId, 2.5, "Test Stone", DecorativenessDto.DECORATED, "Test description");
		when(quarryApi.getAllMenhirs()).thenReturn(List.of(testMenhir));
		when(quarryApi.getMenhirById(testMenhirId)).thenReturn(testMenhir);
		when(quarryApi.getMenhirById(any(UUID.class))).thenAnswer(invocation -> {
			var id = invocation.getArgument(0, UUID.class);
			return id.equals(testMenhirId) ? testMenhir : null;
		});
	}

	@Test
	void buyMenhir() {
		// Should fail without basket items
		webTestClient.post().uri("/api/basket/buy/{id}", testMenhirId)
				.exchange()
				.expectStatus().isBadRequest();
		
		// Add item to basket
		webTestClient.put().uri("/api/basket/offer")
				.bodyValue(new BasketDto.BasketItem("boar", 2))
				.exchange()
				.expectStatus().isOk();
		
		// Should succeed with enough value in basket
		webTestClient.post().uri("/api/basket/buy/{id}", testMenhirId)
				.exchange()
				.expectStatus().isOk();
		
		// Should fail again after purchase (basket emptied)
		webTestClient.post().uri("/api/basket/buy/{id}", testMenhirId)
				.exchange()
				.expectStatus().isBadRequest();
	}
}
