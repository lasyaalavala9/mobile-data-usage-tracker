
import java.time.LocalDate;

public class DataUsage {
    private String activityName;
    private double dataUsed;
    private LocalDate date;

    public DataUsage(String activityName, double dataUsed,
                     LocalDate date) {
        this.activityName = activityName;
        this.dataUsed = dataUsed;
        this.date = date;
    }

    public String getActivityName() {
        return activityName;
    }

    public double getDataUsed() {
        return dataUsed;
    }

    public LocalDate getDate() {
        return date;
    }

    public String toFileString() {
        return activityName + "|" + dataUsed + "|" + date;
    }

    public void displayUsage() {
        System.out.printf("%-20s %-12.2f %s%n",
                activityName, dataUsed, date);
    }
}
