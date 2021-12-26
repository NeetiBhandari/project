package com.example.springboot;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class PersonForm {
    private String name;
    private Integer waterConsumed;
}