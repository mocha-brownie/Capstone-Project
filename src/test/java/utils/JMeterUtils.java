package utils;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class JMeterUtils {

    static String JMETER_PATH = "C:\\Users\\Amey\\Downloads\\apache-jmeter-5.6.3\\apache-jmeter-5.6.3\\bin\\jmeter.bat";
    static String TEST_PLAN = "src/test/resources/jmeter/testplan.jmx";
    static String RESULT_FILE = "target/results.jtl";

    public static void runJMeter() throws Exception {
        Files.deleteIfExists(Paths.get(RESULT_FILE)); 

        ProcessBuilder pb = new ProcessBuilder(
                JMETER_PATH, "-n",
                "-t", TEST_PLAN,
                "-l", RESULT_FILE
        );

        pb.redirectErrorStream(true);
        Process process = pb.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        if (process.waitFor() != 0) {
            throw new RuntimeException("JMeter failed!");
        }
    }

    public static void validatePerformance(int threshold) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(RESULT_FILE));
        
        for (int i = 1; i < lines.size(); i++) { 
            String[] data = lines.get(i).split(",");
            int responseTime = Integer.parseInt(data[1]);
            String responseCode = data[3];
            boolean success = Boolean.parseBoolean(data[7]);

            if (!success) {
                throw new AssertionError("HTTP Request failed with code: " + responseCode);
            }
            if (responseTime > threshold) {
                throw new AssertionError("Performance issue: " + responseTime + "ms");
            }
        }
    }
}