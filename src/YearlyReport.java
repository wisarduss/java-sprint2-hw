import java.util.ArrayList;

public class YearlyReport {
    final ArrayList<YearlyReportRecord> expense;
    final ArrayList<YearlyReportRecord> incomes;

    public YearlyReport(ArrayList<YearlyReportRecord> expense, ArrayList<YearlyReportRecord> incomes) {
        this.expense = expense;
        this.incomes = incomes;
    }
}
