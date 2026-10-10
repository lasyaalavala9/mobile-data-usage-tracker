
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        System.out.println("       MOBILE DATA USAGE TRACKER");

        try {
            List<DataUsage> savedRecords = fileManager.loadRecords();

            System.out.print("Enter your data limit in MB: ");
            double limit = readPositiveNumber();

            DataUsageTracker tracker =
                    new DataUsageTracker(limit, savedRecords);

            System.out.println("Loaded " + savedRecords.size()
                    + " saved usage records.");

            boolean running = true;

            while (running) {
                System.out.println("\n============== MAIN MENU ==============");
                System.out.println("1. Add Data Usage");
                System.out.println("2. View Usage History");
                System.out.println("3. View Data Summary");
                System.out.println("4. Exit");
                System.out.println("=======================================");
                System.out.print("Enter your choice: ");

                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        addUsage(tracker, fileManager);
                        break;

                    case "2":
                        tracker.displayHistory();
                        break;

                    case "3":
                        tracker.displaySummary();
                        break;

                    case "4":
                        running = false;
                        System.out.println(
                                "Thank you for using Mobile Data Usage Tracker!");
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Please enter 1, 2, 3, or 4.");
                }
            }
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid data limit: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void addUsage(DataUsageTracker tracker,
                                 FileManager fileManager) {
        System.out.print("Enter activity name: ");
        String activity = scanner.nextLine().trim();

        if (activity.isBlank() || activity.contains("|")) {
            System.out.println(
                    "Activity name cannot be empty or contain '|'.");
            return;
        }

        System.out.print("Enter data used in MB: ");
        double amount;

        try {
            amount = Double.parseDouble(scanner.nextLine().trim());

            if (!Double.isFinite(amount) || amount <= 0) {
                System.out.println(
                        "Data usage must be a positive number.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println(
                    "Invalid input. Please enter a number.");
            return;
        }

        DataUsage usage = new DataUsage(
                activity, amount, LocalDate.now());

        try {
            fileManager.saveRecord(usage);
            tracker.addUsage(usage);

            System.out.println("Usage record saved successfully!");
            tracker.displaySummary();
        } catch (IOException e) {
            System.out.println(
                    "Could not save the record: " + e.getMessage());
        }
    }

    private static double readPositiveNumber() {
        while (true) {
            try {
                double number = Double.parseDouble(
                        scanner.nextLine().trim());

                if (Double.isFinite(number) && number > 0) {
                    return number;
                }

                System.out.print(
                        "Enter a number greater than zero: ");
            } catch (NumberFormatException e) {
                System.out.print(
                        "Invalid number. Please try again: ");
            }
        }
    }
}
