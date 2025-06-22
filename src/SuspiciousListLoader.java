import java.io.*;
import java.util.*;

public class SuspiciousListLoader {
    public static List<String> load(String filePath) {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) list.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void writeToFile(List<String> lines, String filePath) {
        try {
            File dir = new File(filePath).getParentFile();
            if (!dir.exists()) dir.mkdirs();

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
