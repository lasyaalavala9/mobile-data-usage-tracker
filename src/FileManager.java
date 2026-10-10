
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private final Path filePath = Paths.get("data", "usage.txt");

    public FileManager() {
        try {
            Files.createDirectories(filePath.getParent());

            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("Could not initialize data file: "
                    + e.getMessage());
        }
    }

    public void saveRecord(DataUsage usage) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(
                filePath,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {

            writer.write(usage.toFileString());
            writer.newLine();
        }
    }

    public List<DataUsage> loadRecords() throws IOException {
        List<DataUsage> records = new ArrayList<>();

        if (Files.notExists(filePath)) {
            return records;
        }

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split("\\|", -1);

                    if (parts.length != 3) {
                        continue;
                    }

                    String activity = parts[0];
                    double amount = Double.parseDouble(parts[1]);
                    LocalDate date = LocalDate.parse(parts[2]);

                    if (!activity.isBlank()
                            && Double.isFinite(amount)
                            && amount >= 0) {
                        records.add(
                                new DataUsage(activity, amount, date));
                    }
                } catch (RuntimeException e) {
                    System.out.println(
                            "Skipping an invalid saved record.");
                }
            }
        }

        return records;
    }
}
