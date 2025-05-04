package org.calculator.calc;

import org.calculator.reporting.ExtentTestNGListener;
import org.calculator.util.CommandExecutor;
import org.calculator.util.ExpressionValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AwkCalculator implements Calculator {

    public String calculate(String expression) {
        ExpressionValidator.validateExpression(expression);

        log.info("[AWK] Calculating: {}", expression);
        String cmd = String.format("awk 'BEGIN {print %s}'", expression.replace(" ", ""));
        ExtentTestNGListener.logCalculationStep("[AWK] Calculating: " + expression);
        try {
            String result = CommandExecutor.executeCommand(cmd);
            log.info("[AWK] Result: {}", result);
            ExtentTestNGListener.logCalculationStep("[AWK] Result: " + result);
            return result;
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            log.error("[AWK] Error while calculating '{}': {}", expression, e.getMessage(), e);
            throw new RuntimeException("Error calculating expression in Awk", e);
        }
    }
}
