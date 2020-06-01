package challenge.service;

import challenge.model.Quote;
import challenge.repository.QuoteRepository;
import challenge.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteServiceImpl implements QuoteService {

	@Autowired
	private QuoteRepository repository;

	@Override
	public Quote getQuote() {
		return null;
	}

	@Override
	public Quote getQuoteByActor(String actor) {
		return null;
	}

}
