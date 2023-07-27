import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ReportManager reportManager = new ReportManager();
        Scanner scanner = new Scanner(System.in);

        while (true){
            printMenu();
            String command = scanner.next().trim();
            switch (command) {
                case "1":
                    reportManager.readMonthlyReports();
                    break;
                case "2":
                    reportManager.readYearlyReport();
                    System.out.println("Годовой отчет считан!");
                    break;
                case "3":
                    reportManager.dataChecked();
                    break;
                case "4":
                    reportManager.printMonth();
                    break;
                case "5":
                    reportManager.printYear();
                    break;
                case "0":
                    System.out.println("Завершение программы. До свидания!");
                    return;
            }
        }
    }




    private static void printMenu(){
        System.out.println("1-Считать все месячные отчёты");
        System.out.println("2-Считать годовой отчёт");
        System.out.println("3-Сверить отчёты");
        System.out.println("4-Вывести информацию обо всех месячных отчётах");
        System.out.println("5-Вывести информацию о годовом отчёте");
        System.out.println("0-Завершить программу");
    }
}

