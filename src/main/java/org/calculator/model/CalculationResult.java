package org.calculator.model;

import java.util.List;

public record CalculationResult(List<String> stdout, String stderr) {

    public CalculationResult {
        stderr = stderr == null ? "" : stderr.trim().replaceAll("\\s+", " ");

    }
}
