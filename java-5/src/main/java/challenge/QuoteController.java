package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/quote")
public class QuoteController {

	@Autowired
	private QuoteService service;

	@GetMapping
	public ResponseEntity<Quote> getQuote() {
		return ResponseEntity.ok(service.getQuote());
	}

	@GetMapping("/{actor}")
	public ResponseEntity<Quote> getQuoteByActor(@PathVariable String actor) {
		return ResponseEntity.ok(service.getQuoteByActor(actor));
	}

}
