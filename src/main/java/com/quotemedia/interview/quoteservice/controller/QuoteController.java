package com.quotemedia.interview.quoteservice.controller;

import org.springframework.web.bind.annotation.RestController;

import com.quotemedia.interview.quoteservice.service.QuoteService;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@EnableCaching
public class QuoteController {

    @Autowired
    public QuoteService quoteService;

    @GetMapping("/symbol/{symbol}/quote/latest")
    @Cacheable(key = "#symbol", value = "Quote")
    public ResponseEntity<Object> getLatestQuoteBySymbol(@PathVariable String symbol) {

        Map<String, Object> map = quoteService.getLatestQuoteBySymbol(symbol);

        return new ResponseEntity<Object>(map.get("quoteDto"), (HttpStatusCode) map.get("status"));
    }

    @GetMapping("/symbol/quote/highestAsk/{givenDay}")
    @Cacheable(key = "#givenDay", value = "Quote")
    public ResponseEntity<Object> retrieveHighestAskForGivenDay(@PathVariable LocalDate givenDay) {

        Map<String, Object> map = quoteService.retrieveHighestAskForGivenDay(givenDay);

        return new ResponseEntity<Object>(map.get("symbol"), (HttpStatusCode) map.get("status"));
    }
}