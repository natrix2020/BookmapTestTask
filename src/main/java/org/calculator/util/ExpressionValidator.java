package org.calculator.util;

public class ExpressionValidator {
    private static final String VALID_EXPRESSION_PATTERN = "[0-9+\\-*/(). ]+";

    public static void validateExpression(String expression) {
        if (expression == null || expression.trim().isEmpty() || !expression.matches(VALID_EXPRESSION_PATTERN)) {
            throw new IllegalArgumentException("Invalid expression: " + expression);
        }
    }
}
