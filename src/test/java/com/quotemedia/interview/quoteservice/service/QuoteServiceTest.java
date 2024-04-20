package com.quotemedia.interview.quoteservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.quotemedia.interview.quoteservice.dto.QuoteDto;
import com.quotemedia.interview.quoteservice.repository.QuoteRepository;
import com.quotemedia.interview.quoteservice.service.impl.QuoteServiceImpl;

import jakarta.persistence.Tuple;

public class QuoteServiceTest {

    private QuoteServiceImpl quoteService;
    private QuoteRepository quoteRepository;

    @BeforeEach
    public void setUp() {
        quoteRepository = mock(QuoteRepository.class);
        quoteService = new QuoteServiceImpl(quoteRepository);
    }

    @Test
    public void test_GetLatestQuoteBySymbol_ValidSymbol() {
        // Arrange
        String symbol = "GOOG";
        Tuple mockTuple = mock(Tuple.class);
        when(quoteRepository.validateSymbol(symbol)).thenReturn(true);
        when(quoteRepository.findBidAskBySymbol(symbol)).thenReturn(mockTuple);
        when(mockTuple.get(0)).thenReturn(new BigDecimal("100.00"));
        when(mockTuple.get(1)).thenReturn(new BigDecimal("105.00"));

        Map<String, Object> result = quoteService.getLatestQuoteBySymbol(symbol);

        assertEquals(HttpStatus.OK, result.get("status"));
        assertEquals(new BigDecimal("100.00"), ((QuoteDto) result.get("quoteDto")).getBid());
        assertEquals(new BigDecimal("105.00"), ((QuoteDto) result.get("quoteDto")).getAsk());
    }

    @Test
    public void test_RetrieveHighestAskForGivenDay() {
        LocalDate localDate = LocalDate.now();
        Tuple mockTuple = mock(Tuple.class);

        when(quoteRepository.retrieveHighestAskForGivenDay(localDate)).thenReturn(mockTuple);
        when(mockTuple.get(0)).thenReturn("FB");

        Map<String, Object> result = quoteService.retrieveHighestAskForGivenDay(localDate);

        assertEquals(HttpStatus.OK, result.get("status"));
        assertEquals("FB", result.get("symbol").toString());
    }
}
