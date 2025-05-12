package org.calculator.calc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.stream.Collectors;

import org.calculator.model.CalculationResult;
import org.calculator.reporting.ExtentTestNGListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BcCalculator {

    public CalculationResult calculateWithStreams(String expression) {
        ProcessBuilder builder = new ProcessBuilder("bc");
        Process process = null;
        try {
            process = builder.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ExtentTestNGListener.logCalculationStep("[BC] Calculating: " + expression);
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
                BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                BufferedReader stderrReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
            writer.write(expression);
            writer.newLine();
            writer.flush();
            process.getOutputStream().close();
            List<String> stdout = stdoutReader.lines().collect(Collectors.toList());
            ExtentTestNGListener.logCalculationStep("[bc] STDOUT: " + stdout);
            log.info("STDOUT: '{}'", stdout);
            String stderr = stderrReader.lines()
                    .filter(error -> error != null && !error.trim().isEmpty())
                    .findFirst()
                    .orElse("");

            log.info("STDERR: '{}'", stderr);
            ExtentTestNGListener.logCalculationStep("[bc] STDERR: " + stderr);
            return new CalculationResult(stdout, stderr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
