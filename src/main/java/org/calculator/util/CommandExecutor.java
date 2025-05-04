package org.calculator.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandExecutor {

    public static String executeCommand(String command)  {
        ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
        Process process = null;
        try {
            process = pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String result = reader.readLine();
            process.waitFor();
            return result != null ? result.trim() : "";
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
