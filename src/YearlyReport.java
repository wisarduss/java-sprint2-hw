import java.util.ArrayList;

public class YearlyReport {
    ArrayList<YearlyReportRecord> expense;
    ArrayList<YearlyReportRecord> incomes;

    public YearlyReport(ArrayList<YearlyReportRecord> expense, ArrayList<YearlyReportRecord> incomes) {
        this.expense = expense;
        this.incomes = incomes;
    }
}
