package com.quotemedia.interview.quoteservice.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.quotemedia.interview.quoteservice.model.Quote;
import com.quotemedia.interview.quoteservice.model.QuoteId;

import jakarta.persistence.Tuple;

public interface QuoteRepository extends JpaRepository<Quote, QuoteId> {

    @Query(value = """
            SELECT
                q.BID AS bid,
                q.ASK AS ask
            FROM
                QUOTE q
            WHERE
                q.SYMBOL = :symbol
            ORDER BY
                q.SYMBOL_DAY DESC
            LIMIT 1
            """, nativeQuery = true)
    Tuple findBidAskBySymbol(@Param("symbol") String symbol);

    @Query(value = """
            SELECT
                CASE
                    WHEN EXISTS (SELECT 1 FROM QUOTE q WHERE q.SYMBOL = :symbol) THEN
                        'TRUE'
                    ELSE
                        'FALSE'
                END
                """, nativeQuery = true)
    Boolean validateSymbol(@Param("symbol") String symbol);

    @Query(value = """
            SELECT
                q.SYMBOL
            FROM
                QUOTE q
            WHERE
                SYMBOL_DAY = :givenDay
            ORDER BY
                ASK DESC
            LIMIT 1
            """, nativeQuery = true)
    Tuple retrieveHighestAskForGivenDay(LocalDate givenDay);
}
