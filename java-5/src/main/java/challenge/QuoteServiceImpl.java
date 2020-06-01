package challenge;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class QuoteServiceImpl implements QuoteService {
	private final Random random = new Random();

	@Autowired
	private final QuoteRepository repository;

	@Override
	public Quote getQuote() {
		List<Quote> quotes = repository.findAll();
		return quotes.get(random.nextInt(quotes.size()));
	}

	@Override
	public Quote getQuoteByActor(String actor) {
		List<Quote> quotes = repository.findAllByActor(actor);
		return quotes.get(random.nextInt(quotes.size()));
	}

}
