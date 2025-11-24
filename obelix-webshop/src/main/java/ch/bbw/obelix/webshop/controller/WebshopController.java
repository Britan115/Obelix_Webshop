package ch.bbw.obelix.webshop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebshopController {

	@GetMapping("/api")
	public String welcome() {
		return "Welcome to Obelix's Menhir Shop! The finest menhirs in all of Gaul! Ces Romains sont fous!";
	}
}


