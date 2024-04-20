package com.quotemedia.interview.quoteservice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(QuoteId.class)
public class Quote implements Serializable {

    @Id
    private String symbol;
    @Id
    private LocalDate symbolDay;

    private BigDecimal bid;

    private BigDecimal ask;

}
