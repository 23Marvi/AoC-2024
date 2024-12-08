import Solutions.*;

public class Main {
    public static void main(String[] args) {
        String[] input = FileReader.ReadFileLines(8);

        Day8.SolveOne(input);
        Day8.SolveTwo(input);
    }
}