package ch.bbw.obelix.webshop.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import jakarta.annotation.PostConstruct;

import ch.bbw.obelix.quarry.api.DecorativenessDto;
import ch.bbw.obelix.quarry.api.QuarryApi;
import ch.bbw.obelix.webshop.dto.BasketDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.StandardException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@Service
@RequiredArgsConstructor
public class BasketService {

	private final QuarryApi quarryApi;

	private BasketDto basket;

	static <T> List<T> append(List<T> immutableList, T element) {
		var tmpList = new ArrayList<>(immutableList);
		tmpList.add(element);
		return Collections.unmodifiableList(tmpList);
	}

	public BasketDto offer(@NonNull BasketDto.BasketItem basketItem) {
		basket = basket.withItems(append(basket.items(), basketItem));
		return basket;
	}

	@PostConstruct
	public void leave() {
		basket = BasketDto.empty();
	}

	public boolean isGoodOffer(final DecorativenessDto decorativeness) {
		var stoneValue = decorativeness.ordinal();
		var basketValue = basket.items()
				.stream()
				.mapToInt(item -> switch (item.name().toLowerCase(Locale.ROOT)) {
					case "boar" -> 5;
					case "honey" -> 2;
					case "magic potion" -> 0;
					default -> 1;
				} * item.count())
				.sum();
		log.info("Basket value: {} vs Menhir value: {} ({})", basketValue, decorativeness, stoneValue);
		return basketValue >= stoneValue;
	}

	public void exchange(final UUID menhirId) {
		var menhir = quarryApi.getMenhirById(menhirId);
		var decorativeness = menhir.decorativeness();
		if (!isGoodOffer(decorativeness)) {
			throw new BadOfferException("Bad Offer: That won't even feed Idefix!");
		}
		quarryApi.deleteById(menhirId);
		leave();
	}

	@StandardException
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public static class BadOfferException extends RuntimeException {}
}


