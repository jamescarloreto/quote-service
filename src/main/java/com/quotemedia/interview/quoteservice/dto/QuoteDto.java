package com.quotemedia.interview.quoteservice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class QuoteDto {

    public QuoteDto(BigDecimal bid, BigDecimal ask) {
        this.bid = bid;
        this.ask = ask;
    }

    @JsonIgnore
    public String symbol;

    @JsonIgnore
    public LocalDate symbolDay;

    public BigDecimal bid;

    public BigDecimal ask;
}