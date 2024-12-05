import Solutions.*;

public class Main {
    public static void main(String[] args) {
        String[] input = FileReader.ReadFileLines(5);

        Day5.SolveOne(input);
        Day5.SolveTwo(input);
    }
}