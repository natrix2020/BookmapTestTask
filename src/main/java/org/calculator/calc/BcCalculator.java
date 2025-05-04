package org.calculator.calc;

import org.calculator.reporting.ExtentTestNGListener;
import org.calculator.util.CommandExecutor;
import org.calculator.util.ExpressionValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BcCalculator implements Calculator {

    public String calculate(String expression) {
        ExpressionValidator.validateExpression(expression);

        log.info("[BC] Calculating: {}", expression);
        ExtentTestNGListener.logCalculationStep("[BC] Calculating: " + expression);
        String cmd = String.format("echo '%s' | bc -l", expression);

        try {
            // Use the CommandExecutor utility
            String result = CommandExecutor.executeCommand(cmd);
            log.info("[BC] Result: {}", result);
            ExtentTestNGListener.logCalculationStep("[BC] Result:: " + result);
            return result;
        } catch (Exception e) {
            Thread.currentThread().interrupt(); // Preserve interrupt status
            log.error("[BC] Error while calculating '{}': {}", expression, e.getMessage(), e);
            throw new RuntimeException("Error calculating expression in Bc", e);
        }
    }
}