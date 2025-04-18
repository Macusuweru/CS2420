import java.io.*;
import java.util.*;

public class TimingFramework {

    // Configuration variables (modify these as needed)
    private static final String EXPERIMENT_NAME = "MyExperiment";  // Name of the experiment
    private static final int START_N = 1000;                      // Starting value of N
    private static final int STEP_SIZE = 1000;                    // Amount to increase N by each step
    private static final int NUM_STEPS = 10;                      // Number of different N values to test
    private static final int NUM_REPEATS = 5;                     // Number of times to repeat each experiment
    private static final String CSV_FILE = "results.csv";         // Output CSV file name

    // Column names for the CSV file
    private static final String COL_EXPERIMENT = "Experiment";    // Column for experiment name
    private static final String COL_N = "N";                      // Column for N value
    private static final String COL_TIME = "Nanoseconds";         // Column for time in nanoseconds

    // Placeholder method to set up the experiment
    private static void setupExperiment(int N) {
        // TODO: Implement setup for the experiment with parameter N
        // Example: Initialize data structures or inputs based on N
    }

    // Placeholder method to run the experiment
    private static void runExperiment(int N) {
        // TODO: Implement the experiment run with parameter N
        // Example: Execute the algorithm or process to be timed
    }

    public static void main(String[] args) throws IOException {
        // Check if the CSV file exists to determine if the header should be written
        File file = new File(CSV_FILE);
        boolean writeHeader = !file.exists();

        // Open a PrintWriter in append mode to write results to the CSV
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE, true))) {
            // Write the header if the file is new
            if (writeHeader) {
                writer.println(COL_EXPERIMENT + "," + COL_N + "," + COL_TIME);
            }

            // Iterate over different values of N
            for (int i = 0; i < NUM_STEPS; i++) {
                int N = START_N + i * STEP_SIZE;
                List<Long> times = new ArrayList<>();  // Store timing results for this N

                // Repeat the experiment setup and run NUM_REPEATS times
                for (int j = 0; j < NUM_REPEATS; j++) {
                    setupExperiment(N);                // Set up the experiment
                    long startTime = System.nanoTime(); // Start timing
                    runExperiment(N);                  // Run the experiment
                    long endTime = System.nanoTime();  // End timing
                    long time = endTime - startTime;   // Calculate elapsed time
                    times.add(time);                   // Record the time
                }

                // Calculate the median time
                Collections.sort(times);               // Sort the recorded times
                long medianTime;
                int size = times.size();
                if (size % 2 == 1) {
                    // If odd number of repeats, take the middle value
                    medianTime = times.get(size / 2);
                } else {
                    // If even number of repeats, average the two middle values
                    long mid1 = times.get(size / 2 - 1);
                    long mid2 = times.get(size / 2);
                    medianTime = (long) Math.round((mid1 + mid2) / 2.0);
                }

                // Write the result to the CSV file
                writer.println(EXPERIMENT_NAME + "," + N + "," + medianTime);
            }
        }
    }
}