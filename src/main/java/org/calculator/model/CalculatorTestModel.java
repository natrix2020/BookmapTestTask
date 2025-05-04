package org.calculator.model;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CalculatorTestModel {
    private String expression;
    private String expected;
}
