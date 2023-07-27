import java.util.ArrayList;

public class MonthlyReport {
    final ArrayList<MonthlyReportRecord> expenses;
    final ArrayList<MonthlyReportRecord> incomes;

    public MonthlyReport(ArrayList<MonthlyReportRecord> expenses, ArrayList<MonthlyReportRecord> incomes) {
        this.expenses = expenses;
        this.incomes = incomes;
    }
}
