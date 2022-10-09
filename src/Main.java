import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static YearReport report21 = new YearReport();
    static MonthReport report21m01 = new MonthReport();
    static MonthReport report21m02 = new MonthReport();
    static MonthReport report21m03 = new MonthReport();
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            int command = scanner.nextInt();

            if (command == 1) {
                new Main().monthRead();
            } else if (command == 2) {
                new Main().yearRead();
            } else if (command == 3) {
                if(!(report21.recordYear.isEmpty())) {
                    new Main().reconciliationOfReports();
                } else System.out.println("Считайте сначала отчеты");
            } else if (command == 4) {
                if (!(report21.recordYear.isEmpty())) {
                    new Main().printMonth();
                } else System.out.println("Считайте сначала отчеты");
            } else if (command == 5) {
                if (!(report21.recordYear.isEmpty())) {
                    new Main().printAll2021();
                } else System.out.println("Считайте сначала отчеты");
            } else if (command == 0) {
                System.out.println("Выход");
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
        }

    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход");
    }
    public void reconciliationOfReports() {
        for (int i = 0; i < report21.recordYear.size(); i++) {

            if (report21.recordYear.get(i).isItExpense) {
                if ((report21.recordYear.get(i).month == 1)&&(report21.recordYear.get(i).amount == report21m01.sumExpense())) {
                    System.out.println("Сверка расходов за январь месяц прошла успешно");
                } else System.out.println("Сверка расходов за январь месяц не прошла");

                if ((report21.recordYear.get(i).month == 2)&&(report21.recordYear.get(i).amount == report21m02.sumExpense())) {
                    System.out.println("Сверка расходов за февраль месяц прошла успешно");
                } else System.out.println("Сверка расходов за февраль месяц не прошла");

                if ((report21.recordYear.get(i).month == 3)&&(report21.recordYear.get(i).amount == report21m03.sumExpense())) {
                    System.out.println("Сверка расходов за март месяц прошла успешно");
                }else System.out.println("Сверка расходов за март месяц не прошла");

            } else {
                if ((report21.recordYear.get(i).month == 1)&&(report21.recordYear.get(i).amount == report21m01.sumNonExpense())) {
                    System.out.println("Сверка доходов за январь месяц прошла успешно");
                } else System.out.println("Сверка доходов за январь месяц не прошла");

                if ((report21.recordYear.get(i).month == 2)&&(report21.recordYear.get(i).amount == report21m02.sumNonExpense())) {
                    System.out.println("Сверка доходов за февраль месяц прошла успешно");
                } else System.out.println("Сверка доходов за февраль месяц не прошла");

                if ((report21.recordYear.get(i).month == 3)&&(report21.recordYear.get(i).amount == report21m03.sumNonExpense())) {
                    System.out.println("Сверка доходов за март месяц прошла успешно");
                } else System.out.println("Сверка доходов за март месяц не прошла");
            }

        }
    }

    public void printAll2021() {
        System.out.println("Доходность в 2021 году:");
        System.out.println("Январь");
        report21m01.profitability();

        System.out.println("Февраль");
        report21m02.profitability();

        System.out.println("Март");
        report21m03.profitability();

        System.out.println("Средний доход в этом году составил: " + averagePlus());
        System.out.println("Средние расходы в этом году составили: " + averageMinus());
    }

    public void printMonth(){
        HashMap<String, MonthReport> month = new HashMap<>();
        month.put("Январь", report21m01);
        month.put("Февраль", report21m02);
        month.put("Март", report21m03);
        for (String key : month.keySet()) {
            System.out.println(key);
            for (int i = 0; i < month.get(key).records.size(); i++) {
                if (month.get(key).findMax() == (month.get(key).records.get(i).quantity * month.get(key).records.get(i).sumOfOne)) {
                    System.out.println("Самый прибыльный товар " + month.get(key).records.get(i).itemName);
                    System.out.println("С доходом равным " + month.get(key).findMax());
                }
            }
        }
    }


    int averagePlus(){
        return (report21m01.sumNonExpense() + report21m02.sumNonExpense() + report21m03.sumNonExpense())/3;
    }
    int averageMinus(){
        return (report21m01.sumExpense() + report21m02.sumExpense() + report21m03.sumExpense())/3;
    }

    public void yearRead(){
        report21.yearReport("resources/y.2021.csv");
        System.out.println("Годовой отчет загружен");
    }

    public void monthRead(){
        report21m01.monthlyReport("resources/m.202101.csv");
        report21m02.monthlyReport("resources/m.202102.csv");
        report21m03.monthlyReport("resources/m.202103.csv");
        System.out.println("Месячные отчеты загружены");
    }

}