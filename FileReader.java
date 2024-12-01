import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    private static int _MinDay = 1;
    private static int _MaxDay = 25;

    public static String[] ReadFileLines(int day) {
        String[] lines = new String[0];
        try {
            File file = GetFile(day);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] newLines = new String[lines.length + 1];
                System.arraycopy(lines, 0, newLines, 0, lines.length);
                newLines[lines.length] = scanner.nextLine();
                lines = newLines;
            }
            scanner.close();
            return lines;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private static File GetFile(int day) throws FileNotFoundException {
        if (day < _MinDay || day > _MaxDay) {
            throw new IllegalArgumentException("Day must be between" + _MinDay + " and " + _MaxDay);
        }

        String fullPath = "Inputs/Day" + day;

        try {
            File file = new File(fullPath);
            Scanner scanner = new Scanner(file);
            scanner.close();
            return file;
        } catch (Exception e) {
            throw new FileNotFoundException("File not found: " + fullPath);
        }
    }
}
