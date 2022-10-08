import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static jdk.internal.org.jline.utils.InfoCmp.Capability.lines;


public class YearReport {
    public ArrayList<YearlyLineRecord> records = new ArrayList<>();
    public YearReport(String path) {
        String content = readFileContentOrNull(path);
        String[] lines = content.split("\r?\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);
            YearlyLineRecord record = new YearlyLineRecord(month, amount, isExpense);
            records.add(record);

    }

    private String readFileContentOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException) {
            System.out.println("Невозможно прочитать файл");
            return null;
        }
    }
}
