import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;

public class MonthReport {

    public List<MonthlyLineRecord> records = new ArrayList<>();

    public void monthlyReport(String path) {
        String content = readFileContentsOrNull(path);
        String[] lines = content.split("\r?\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i]; // "02,14000,true"
            String[] parts = line.split(",");
            String itemName = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sumOfOne = Integer.parseInt(parts[3]);
            MonthlyLineRecord record = new MonthlyLineRecord(itemName, isExpense, quantity, sumOfOne);
            records.add(record);
        }
    }

    private String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return "";
        }
    }

    public int sumExpense() {
        int result = 0;
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).isExpense) {
                int sum = 0;
                sum = records.get(i).sumOfOne * records.get(i).quantity;
                result += sum;
            }
        }
        return result;
    }

    public int sumNonExpense() {
        int result = 0;
        for (int i = 0; i < records.size(); i++) {
            if (!(records.get(i).isExpense)) {
                int sum = 0;
                sum = records.get(i).sumOfOne * records.get(i).quantity;
                result += sum;
            }
        }
        return result;
    }

    public int findMax() {
        int maxExpense = 0;
        for (int i = 0; i < records.size(); i++) {
            if (!(records.get(i).isExpense) && ((records.get(i).quantity * records.get(i).sumOfOne) > maxExpense)) {
                maxExpense = records.get(i).quantity * records.get(i).sumOfOne;
            }
        }
        return maxExpense;
    }

    public int profitability() {
        int result = 0;
        if (sumExpense() > sumNonExpense()) {
            result = sumExpense() - sumNonExpense();
            System.out.println("Этот месяц был убыточным на " + result);
        } else {
            result = sumNonExpense() - sumExpense();
            System.out.println("Этот месяц был прибыльным на " + result);
        }
        return result;
    }

}