import Solutions.*;

public class Main {
    public static void main(String[] args) {
        String[] input = FileReader.ReadFileLines(4);

        Day4.SolveOne(input);
        Day4.SolveTwo(input);
    }
}