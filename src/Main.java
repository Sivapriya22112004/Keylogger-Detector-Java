import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Keylogger Detector for Windows ===");

        // Step 1: Load suspicious process names
       List<String> suspiciousList = ProcessScanner.loadSuspiciousList("suspicious_list.txt");

        // Step 2: Scan current system processes
        List<String> detections = ProcessScanner.scanProcesses(suspiciousList);

        // Step 3: Log results
        ProcessScanner.logDetections(detections, "logs/detections.txt");


        // Step 4: Print results
        if (!detections.isEmpty()) {
            for (String s : detections) {
                System.out.println(s);
            }

            // Step 5: Send summary email
            String summary = "Suspicious processes detected:\n\n" + String.join("\n", detections);
            EmailSender.sendEmail(summary);
        } else {
            System.out.println("No suspicious processes found.");
        }
    }
}
