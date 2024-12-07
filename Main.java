import Solutions.*;

public class Main {
    public static void main(String[] args) {
        String[] input = FileReader.ReadFileLines(7);

        Day7.SolveOne(input);
        Day7.SolveTwo(input);
    }
}