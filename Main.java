import Solutions.*;

public class Main {
    public static void main(String[] args) {
        char[][] input = FileReader.ReadFileDoubleDimensionCharArray(10);

        Day10.SolveOne(input);
        Day10.SolveTwo(input);
    }
}