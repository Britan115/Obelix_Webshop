package ch.bbw.obelix.webshop.controller;

import java.util.UUID;

import ch.bbw.obelix.webshop.dto.BasketDto;
import ch.bbw.obelix.webshop.service.BasketService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BasketController {

	private final BasketService basketService;

	@PutMapping("/api/basket/offer")
	public BasketDto offer(@RequestBody final BasketDto.BasketItem basketItem) {
		return basketService.offer(basketItem);
	}

	@DeleteMapping("/api/basket")
	public void leave() {
		basketService.leave();
	}

	@PostMapping("/api/basket/buy/{menhirId}")
	public void exchangeFor(@PathVariable final UUID menhirId) {
		basketService.exchange(menhirId);
	}

	@StandardException
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public static class UnknownMenhirException extends RuntimeException {}
}


