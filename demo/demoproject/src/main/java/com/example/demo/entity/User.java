package com.example.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;                 
    private String name;              
    private BigDecimal depositAmount;  
    private String place;             
    private LocalDate date;           
}

