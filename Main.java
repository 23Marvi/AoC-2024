import Solutions.*;

public class Main {
    public static void main(String[] args) {
        String[] input = FileReader.ReadFileLines(2);

        Day2.SolveOne(input);
        Day2.SolveTwo(input);
    }
}