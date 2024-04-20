package com.quotemedia.interview.quoteservice.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.quotemedia.interview.quoteservice.dto.QuoteDto;
import com.quotemedia.interview.quoteservice.repository.QuoteRepository;
import com.quotemedia.interview.quoteservice.service.QuoteService;

import jakarta.persistence.Tuple;

@Service
public class QuoteServiceImpl implements QuoteService {

    private static final Logger logger = LoggerFactory.getLogger(QuoteServiceImpl.class);

    public QuoteRepository quoteRepository;

    public QuoteServiceImpl(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public Map<String, Object> getLatestQuoteBySymbol(String symbol) {
        logger.info("QuoteServiceImpl | getLatestQuoteBySymbol | START");

        Map<String, Object> map = new HashMap<String, Object>();
        QuoteDto quoteDto = new QuoteDto();

        try {
            if (symbol == null) {
                map.put("status", HttpStatus.BAD_REQUEST);

                return map;
            }

            Boolean valid = quoteRepository.validateSymbol(symbol);

            logger.info("getLatestQuoteBySymbol | valid : " + valid);

            if (!valid) {
                map.put("status", HttpStatus.BAD_REQUEST);

                return map;
            }

            logger.info("getLatestQuoteBySymbol | symbol : " + symbol + ", symbol count : " + symbol.length());

            if (symbol.trim().length() >= 2 && symbol.trim().length() <= 6) {
                Tuple tuple = quoteRepository.findBidAskBySymbol(symbol);

                if (tuple == null) {
                    logger.info("getLatestQuoteBySymbol | tuple is null");

                    map.put("status", HttpStatus.NOT_FOUND);

                    return map;
                }

                quoteDto = setTupleToDto(tuple);

                map.put("quoteDto", quoteDto);
                map.put("status", HttpStatus.OK);
            } else {
                map.put("status", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            logger.error("QuoteServiceImpl | getLatestQuoteBySymbol | error: " + ex.getMessage());

            map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return map;
    }

    private QuoteDto setTupleToDto(Tuple tuple) {
        logger.info("QuoteServiceImpl | setTupleToDto | START");
        logger.info("tuple.get(bid,ask).toString() : " + tuple.get(0).toString() + ", " + tuple.get(1).toString());

        BigDecimal bid = new BigDecimal(tuple.get(0).toString());
        BigDecimal ask = new BigDecimal(tuple.get(1).toString());

        return new QuoteDto(bid, ask);
    }

    @Override
    public Map<String, Object> retrieveHighestAskForGivenDay(LocalDate givenDay) {
        logger.info("QuoteServiceImpl | retrieveHighestAskForGivenDay | START");

        Map<String, Object> map = new HashMap<String, Object>();
        try {

            if (givenDay == null) {
                logger.info("retrieveHighestAskForGivenDay | givenDay is null");

                map.put("status", HttpStatus.BAD_REQUEST);

                return map;
            }

            Tuple tuple = quoteRepository.retrieveHighestAskForGivenDay(givenDay);

            if (tuple == null) {
                logger.info("retrieveHighestAskForGivenDay | tuple is null");

                map.put("status", HttpStatus.NOT_FOUND);

                return map;
            }

            String symbol = tuple.get(0).toString();

            map.put("symbol", symbol);
            map.put("status", HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("QuoteServiceImpl | retrieveHighestAskForGivenDay | error: " + ex.getMessage());

            map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return map;
    }
}
