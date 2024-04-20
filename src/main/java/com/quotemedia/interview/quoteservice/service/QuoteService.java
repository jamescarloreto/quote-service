package com.quotemedia.interview.quoteservice.service;

import java.time.LocalDate;
import java.util.Map;

public interface QuoteService {
    public Map<String, Object> getLatestQuoteBySymbol(String symbol);

    public Map<String, Object> retrieveHighestAskForGivenDay(LocalDate givenDay);
}
