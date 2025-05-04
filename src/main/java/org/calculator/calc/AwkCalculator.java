package org.calculator.calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AwkCalculator implements Calculator {

    public String calculate(String expression)  {
        log.info("[AWK] Calculating: {}", expression);
        String cmd = String.format("awk 'BEGIN {print %s}'", expression.replace(" ", ""));
        ProcessBuilder pb = new ProcessBuilder("bash", "-c", cmd);
        Process process = null;
        try {
            process = pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String result = reader.readLine();
            process.waitFor();
            log.info("[AWK] Result: {}", result);
            return result != null ? result.trim() : "";
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
