package org.calculator.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

@Builder
@Getter
public class CalcTestCases {
    private final String expression;

    @Singular
    private final List<String> expectedStdouts;

    private final String expectedStderr;

}