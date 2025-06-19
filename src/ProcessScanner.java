import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProcessScanner {

    // Loads the suspicious process names
    public static List<String> loadSuspiciousList(String filePath) {
        return SuspiciousListLoader.load(filePath);
    }

    // Scans system processes and compares with suspicious list
    public static List<String> scanProcesses(List<String> suspiciousList) {
        Set<String> detectedNames = new HashSet<>(); // To avoid duplicate alerts
        List<String> detections = new ArrayList<>();

        try {
            // Run tasklist command to get active processes
            ProcessBuilder pb = new ProcessBuilder("tasklist");
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                for (String suspicious : suspiciousList) {
                    String suspect = suspicious.toLowerCase().trim();

                    if (line.toLowerCase().contains(suspect)) {
                        // Add only if not already added
                        if (detectedNames.add(suspect)) {
                            String result = "Detected: " + suspicious;
                            detections.add(result);
                        }
                    }
                }
            }

            reader.close();

            // Send summary email only if detections exist
            if (!detections.isEmpty()) {
                String summary = "Suspicious processes detected:\n\n" + String.join("\n", detections);
                EmailSender.sendEmail(summary);
            } else {
                System.out.println("No suspicious processes found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return detections;
    }

    // Log detections to file
    public static void logDetections(List<String> detections, String filePath) {
        SuspiciousListLoader.writeToFile(detections, filePath);
    }
}
