package org.calculator.calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BcCalculator implements Calculator {

    public String calculate(String expression) {
        log.info("[BC] Calculating: {}", expression);
        ProcessBuilder pb = new ProcessBuilder("bc", "-l");
        Process process = null;
        try {
            process = pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (OutputStreamWriter writer = new OutputStreamWriter(process.getOutputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {

            writer.write(expression + "\n");
            writer.flush();
            writer.close();

            String result = reader.readLine();
            process.waitFor();

            log.info("[BC] Result: {}", result);
            return result != null ? result.trim() : "";
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}