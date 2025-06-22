import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class ProcessScanner {
    public static List<String> loadSuspiciousList(String filePath) {
        return SuspiciousListLoader.load(filePath);
    }

    public static List<String> scanProcesses(List<String> suspiciousList) {
        Set<String> detected = new HashSet<>();
        List<String> detections = new ArrayList<>();

        try {
            ProcessBuilder pb = new ProcessBuilder("tasklist");
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                for (String suspect : suspiciousList) {
                    if (line.toLowerCase().contains(suspect.toLowerCase()) && detected.add(suspect)) {
                        detections.add("Detected: " + suspect);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return detections;
    }

    public static void logDetections(List<String> detections, String filePath) {
        SuspiciousListLoader.writeToFile(detections, filePath);
    }
}
