import java.util.ArrayList;
import java.util.HashMap;

public class ReportManager {
    final ArrayList<YearlyReport> yearlyReports = new ArrayList<>();
    final HashMap<Integer, MonthlyReport> monthlyReports = new HashMap<>();
    final ArrayList<Integer> totalExpensesMonth = new ArrayList<>();
    final ArrayList<Integer> totalIncomesMonth = new ArrayList<>();
    final ArrayList<Integer> totalExpensesYear = new ArrayList<>();
    final ArrayList<Integer> totalIncomesYear = new ArrayList<>();

    FileReader fileReader = new FileReader();

    public void readMonthlyReports() {
        for (int i = 1; i < 4; i++) {
            String fileName = "m.20210" + i + ".csv";
            ArrayList<String> lines = fileReader.readFileContents(fileName);

            if (lines.isEmpty()) {
                System.out.println("Файл" + fileName + "пустой");
                return;
            }

            ArrayList<MonthlyReportRecord> expenses = new ArrayList<>();
            ArrayList<MonthlyReportRecord> incomes = new ArrayList<>();


            for (int j = 1; j < lines.size(); j++) {
                String line = lines.get(j);
                String[] tokens = line.split(",");
                MonthlyReportRecord monthlyReportRecord = lineToRecordMonth(tokens);

                if (monthlyReportRecord.isExpense) {
                    expenses.add(monthlyReportRecord);
                } else {
                    incomes.add(monthlyReportRecord);
                }
            }
            monthlyReports.put(i, new MonthlyReport(expenses, incomes));
            System.out.println("Отчет " + fileName + " считан");
        }
    }

    public void readYearlyReport() {
        String fileName = "y.2021.csv";
        ArrayList<String> lines = fileReader.readFileContents(fileName);

        if (lines.isEmpty()) {
            System.out.println("Файл" + fileName + "Пустой");
            return;
        }

        ArrayList<YearlyReportRecord> expenses = new ArrayList<>();
        ArrayList<YearlyReportRecord> incomes = new ArrayList<>();


        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] tokens = line.split(",");
            YearlyReportRecord yearlyReportRecord = lineToRecordYear(tokens);

            if (yearlyReportRecord.isExpense) {
                expenses.add(yearlyReportRecord);
            } else {
                incomes.add(yearlyReportRecord);
            }
        }
        yearlyReports.add(new YearlyReport(expenses, incomes));
        System.out.println("Отчет " + fileName + " считан");
    }

    private YearlyReportRecord lineToRecordYear(String[] tokens) {
        return new YearlyReportRecord(
                Integer.parseInt(tokens[0]),
                Integer.parseInt(tokens[1]),
                Boolean.parseBoolean(tokens[2])
        );
    }

    private MonthlyReportRecord lineToRecordMonth(String[] tokens) {
        return new MonthlyReportRecord(
                tokens[0],
                Boolean.parseBoolean(tokens[1]),
                Integer.parseInt(tokens[2]),
                Integer.parseInt(tokens[3])
        );
    }

    public void dataChecked() {
        if (monthlyReports.isEmpty() && yearlyReports.isEmpty()) {
            System.out.println("Файлы не считаны! Пожалуйста считайте файлы");
            return;
        }
        compareYearAndMonth();

    }

    private void addMonthToListTotalExpAndInc() {
        int totalSumExp = 0;
        int totalSumInc = 0;
        for (Integer month : monthlyReports.keySet()) {
            for (int i = 0; i < monthlyReports.get(month).expenses.size(); i++) {
                totalSumExp += monthlyReports.get(month).expenses.get(i).totalPrice();
            }
            int totalExp = totalSumExp;
            totalExpensesMonth.add(totalExp);
            totalSumExp = 0;

        }

        for (Integer month : monthlyReports.keySet()) {
            for (int i = 0; i < monthlyReports.get(month).incomes.size(); i++) {
                totalSumInc += monthlyReports.get(month).incomes.get(i).totalPrice();
            }
            int totalExp = totalSumInc;
            totalIncomesMonth.add(totalExp);
            totalSumInc = 0;
        }
    }

    private void compareYearAndMonth() {
        addMonthToListTotalExpAndInc();
        addYearToListTotalExpAndInc();
        boolean isEqualMonth = true;
        boolean isEqualYear = true;
        if (totalExpensesMonth.size() == totalExpensesYear.size()) {
            for (int i = 0; i < totalExpensesMonth.size(); i++) {
                if (!totalExpensesMonth.get(i).equals(totalExpensesYear.get(i))) {
                    isEqualMonth = false;
                    System.out.println("Расходы не сошлись в месяце " + returnMonth(i+1));
                    break;
                }
            }
        } else {
            isEqualMonth = false;
        }
        if (isEqualMonth) {
            System.out.println("Все расходы прошли проверку");
        }
        System.out.println();
        if (totalIncomesMonth.size() == totalIncomesYear.size()) {
            for (int i = 0; i < totalIncomesMonth.size(); i++) {
                if (!totalIncomesMonth.get(i).equals(totalIncomesYear.get(i))) {
                    isEqualYear = false;
                    System.out.println("Доходы не сошлись в месяце " + returnMonth(i+1));
                }
            }
        } else {
            isEqualYear = false;
        }
        if (isEqualYear) {
            System.out.println("Все доходы прошли проверку");
        }
        System.out.println();
    }

    private void addYearToListTotalExpAndInc() {
        for (YearlyReport yearlyReport : yearlyReports) {
            for (int j = 0; j < yearlyReport.expense.size(); j++) {
                totalExpensesYear.add(yearlyReport.expense.get(j).amount);
            }

        }

        for (YearlyReport yearlyReport : yearlyReports) {
            for (int j = 0; j < yearlyReport.incomes.size(); j++) {
                totalIncomesYear.add(yearlyReport.incomes.get(j).amount);
            }
        }
    }

    private String returnMonth(int month){
        String nameMonth = "";
        if (month == 1){
            nameMonth = "Январь";
        }
        else if (month == 2){
            nameMonth = "Февраль";
        }
        else if (month == 3){
            nameMonth = "Март";
        }
        return nameMonth;
    }



    public void printMonth(){
        if (monthlyReports.isEmpty()){
            System.out.println("Месячные отчеты не считаны!");
            return;
        }
        for (Integer month : monthlyReports.keySet()){
            int maxPrice = 0;
            String itemName = "";
            String monthName;
            for (int i = 0; i < monthlyReports.get(month).expenses.size(); i++){
                if (monthlyReports.get(month).expenses.get(i).totalPrice() > maxPrice){
                    maxPrice = monthlyReports.get(month).expenses.get(i).totalPrice();
                    itemName = monthlyReports.get(month).expenses.get(i).itemName;
                }
            }
            monthName = returnMonth(month);
            System.out.println("В месяце " + monthName.toLowerCase() + " самую большую трату принес "
                    + itemName.toLowerCase()
                    + " сумма товара - " + maxPrice);
        }
        System.out.println();
        for (Integer month : monthlyReports.keySet()){
            int maxPrice = 0;
            String itemName = "";
            String monthName;
            for (int i = 0; i < monthlyReports.get(month).incomes.size(); i++){
                if (monthlyReports.get(month).incomes.get(i).totalPrice() > maxPrice){
                    maxPrice = monthlyReports.get(month).incomes.get(i).totalPrice();
                    itemName = monthlyReports.get(month).incomes.get(i).itemName;
                }
            }
            monthName = returnMonth(month);
            System.out.println("В месяце " + monthName.toLowerCase() + " самую большую прибыль принес "
                    + itemName.toLowerCase()
                    + " сумма товара - " + maxPrice);
        }
        System.out.println();
    }
    private double averageExpense(){
        double sumOfExp = 0;
        for (Integer expense : totalExpensesYear) {
            sumOfExp += expense;
        }
        return sumOfExp / totalExpensesYear.size();
    }

    private double averageIncome(){
        double sumOfInc = 0;
        for (Integer income : totalIncomesYear) {
            sumOfInc += income;
        }
        return sumOfInc / totalExpensesYear.size();
    }
    public void printYear(){
        if (yearlyReports.isEmpty()){
            System.out.println("Годовой отчет не считан");
            return;
        }
        int year = 2021;
        System.out.println("В " + year + "году");
        for (YearlyReport yearlyReport : yearlyReports){
            int profit;
            for (int i = 0; i < yearlyReport.incomes.size(); i++) {
                profit = yearlyReport.incomes.get(i).amount - yearlyReport.expense.get(i).amount;
                System.out.println("Прибыль за месяц " + returnMonth(i+1) + " составила - " + profit);
            }
        }
        System.out.println();
        System.out.println("Средний расход составил " + averageExpense());
        System.out.println("Средний доход составил " + averageIncome());
    }
}
