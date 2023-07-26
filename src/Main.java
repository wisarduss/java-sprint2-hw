import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ReportManager reportManager = new ReportManager();
        Scanner scanner = new Scanner(System.in);

        while (true){
            printMenu();
            String command = scanner.next();
            if (command.equals("1")){
                reportManager.readMonthlyReports();
            }
            else if(command.equals("2")){
                reportManager.readYearlyReport();
                System.out.println("Годовой отчет считан!");
            }
            else if (command.equals("3")){
                reportManager.dataChecked();
            }
            else if (command.equals("4")){
                reportManager.printMonth();
            }
            else if (command.equals("5")){
                reportManager.printYear();
            }
            else if(command.equals("0")){
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

