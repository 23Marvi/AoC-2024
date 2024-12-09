import Solutions.*;

public class Main {
    public static void main(String[] args) {
        String input = FileReader.ReadFile(9);

        Day9.SolveOne(input);
        Day9.SolveTwo(input);
    }
}