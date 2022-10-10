import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static YearReport yearReport = new YearReport();
    static MonthReport reportFirstMonth = new MonthReport();
    static MonthReport reportSecondMonth = new MonthReport();
    static MonthReport reportThirdMonth = new MonthReport();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            try {
                int command = Integer.parseInt(scanner.nextLine());

                if (command == 1) {
                    new Main().monthRead();
                } else if (command == 2) {
                    new Main().yearRead();
                } else if (command == 3) {
                    if (!(yearReport.recordYear.isEmpty())) {
                        new Main().reconciliationOfReports();
                    } else System.out.println("Считайте сначала отчеты");
                } else if (command == 4) {
                    if (!(yearReport.recordYear.isEmpty())) {
                        new Main().printMonth();
                    } else System.out.println("Считайте сначала отчеты");
                } else if (command == 5) {
                    if (!(yearReport.recordYear.isEmpty())) {
                        new Main().printAll2021();
                    } else System.out.println("Считайте сначала отчеты");
                } else if (command == 0) {
                    System.out.println("Выход");
                    break;
                } else {
                    System.out.println("Извините, такой команды пока нет.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Это не число");
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
        for (int i = 0; i < yearReport.recordYear.size(); i++) {

            if (yearReport.recordYear.get(i).isItExpense) {
                if ((yearReport.recordYear.get(i).month == 1) && (yearReport.recordYear.get(i).amount == reportFirstMonth.sumExpense())) {
                    System.out.println("Сверка расходов за январь месяц прошла успешно");
                } else System.out.println("Сверка расходов за январь месяц не прошла");

                if ((yearReport.recordYear.get(i).month == 2) && (yearReport.recordYear.get(i).amount == reportSecondMonth.sumExpense())) {
                    System.out.println("Сверка расходов за февраль месяц прошла успешно");
                } else System.out.println("Сверка расходов за февраль месяц не прошла");

                if ((yearReport.recordYear.get(i).month == 3) && (yearReport.recordYear.get(i).amount == reportThirdMonth.sumExpense())) {
                    System.out.println("Сверка расходов за март месяц прошла успешно");
                } else System.out.println("Сверка расходов за март месяц не прошла");

            } else {
                if ((yearReport.recordYear.get(i).month == 1) && (yearReport.recordYear.get(i).amount == reportFirstMonth.sumNonExpense())) {
                    System.out.println("Сверка доходов за январь месяц прошла успешно");
                } else System.out.println("Сверка доходов за январь месяц не прошла");

                if ((yearReport.recordYear.get(i).month == 2) && (yearReport.recordYear.get(i).amount == reportSecondMonth.sumNonExpense())) {
                    System.out.println("Сверка доходов за февраль месяц прошла успешно");
                } else System.out.println("Сверка доходов за февраль месяц не прошла");

                if ((yearReport.recordYear.get(i).month == 3) && (yearReport.recordYear.get(i).amount == reportThirdMonth.sumNonExpense())) {
                    System.out.println("Сверка доходов за март месяц прошла успешно");
                } else System.out.println("Сверка доходов за март месяц не прошла");
            }

        }
    }

    public void printAll2021() {
        System.out.println("Доходность в 2021 году:");
        System.out.println("Январь");
        reportFirstMonth.profitability();

        System.out.println("Февраль");
        reportSecondMonth.profitability();

        System.out.println("Март");
        reportThirdMonth.profitability();

        System.out.println("Средний доход в этом году составил: " + averagePlus());
        System.out.println("Средние расходы в этом году составили: " + averageMinus());
    }

    public void printMonth() {
        HashMap<String, MonthReport> month = new HashMap<>();
        month.put("Январь", reportFirstMonth);
        month.put("Февраль", reportSecondMonth);
        month.put("Март", reportThirdMonth);
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


    int averagePlus() {
        return (reportFirstMonth.sumNonExpense() + reportSecondMonth.sumNonExpense() + reportThirdMonth.sumNonExpense()) / 3;
    }

    int averageMinus() {
        return (reportFirstMonth.sumExpense() + reportSecondMonth.sumExpense() + reportThirdMonth.sumExpense()) / 3;
    }

    public void yearRead() {
        yearReport.yearReport("resources/y.2021.csv");
        System.out.println("Годовой отчет загружен");
    }

    public void monthRead() {
        reportFirstMonth.monthlyReport("resources/m.202101.csv");
        reportSecondMonth.monthlyReport("resources/m.202102.csv");
        reportThirdMonth.monthlyReport("resources/m.202103.csv");
        System.out.println("Месячные отчеты загружены");
    }

}