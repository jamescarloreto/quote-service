package com.quotemedia.interview.quoteservice.model;

import java.io.Serializable;
import java.time.LocalDate;

public class QuoteId implements Serializable {

    public String symbol;
    public LocalDate symbolDay;
}
