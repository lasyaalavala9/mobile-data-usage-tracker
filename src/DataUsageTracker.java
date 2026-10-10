
import java.util.ArrayList;
import java.util.List;

public class DataUsageTracker {
    private double dataLimit;
    private final List<DataUsage> records;

    public DataUsageTracker(double dataLimit,
                            List<DataUsage> savedRecords) {
        setDataLimit(dataLimit);
        records = new ArrayList<>(savedRecords);
    }

    public void setDataLimit(double dataLimit) {
        if (!Double.isFinite(dataLimit) || dataLimit <= 0) {
            throw new IllegalArgumentException(
                    "Data limit must be greater than zero.");
        }

        this.dataLimit = dataLimit;
    }

    public void addUsage(DataUsage usage) {
        records.add(usage);
    }

    public List<DataUsage> getRecords() {
        return new ArrayList<>(records);
    }

    public double getTotalUsage() {
        double total = 0;

        for (DataUsage record : records) {
            total += record.getDataUsed();
        }

        return total;
    }

    public double getRemainingData() {
        return Math.max(0, dataLimit - getTotalUsage());
    }

    public double getUsagePercentage() {
        return getTotalUsage() / dataLimit * 100;
    }

    public void displaySummary() {
        double used = getTotalUsage();

        System.out.println("\n========== DATA SUMMARY ==========");
        System.out.printf("Data limit       : %.2f MB%n", dataLimit);
        System.out.printf("Total used       : %.2f MB%n", used);
        System.out.printf("Remaining data   : %.2f MB%n",
                getRemainingData());
        System.out.printf("Usage percentage : %.2f%%%n",
                getUsagePercentage());

        if (used >= dataLimit) {
            System.out.println("ALERT: Your data limit has been reached!");
        } else if (getUsagePercentage() >= 90) {
            System.out.println("WARNING: You have used at least 90% of your data.");
        } else if (getUsagePercentage() >= 80) {
            System.out.println("NOTICE: You have used at least 80% of your data.");
        }

        System.out.println("==================================");
    }

    public void displayHistory() {
        if (records.isEmpty()) {
            System.out.println("No usage records found.");
            return;
        }

        System.out.printf("%-20s %-12s %s%n",
                "ACTIVITY", "DATA (MB)", "DATE");
        System.out.println("------------------------------------------------");

        for (DataUsage record : records) {
            record.displayUsage();
        }
    }
}
