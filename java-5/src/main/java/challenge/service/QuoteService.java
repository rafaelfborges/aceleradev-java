package challenge.service;

import challenge.model.Quote;

public interface QuoteService {

	Quote getQuote();
	
	Quote getQuoteByActor(String actor);

}
