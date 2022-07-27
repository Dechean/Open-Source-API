package com.example.demolast.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Weather implements Serializable { // for converting obj to byte streams

    private BigDecimal temp;
    private BigDecimal feels_like;
    private String description;
    private String country;


}
