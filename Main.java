import Solutions.*;

public class Main {
    public static void main(String[] args) {
        char[][] input = FileReader.ReadFileDoubleDimensionCharArray(6);

        Day6.SolveOne(input);
        Day6.SolveTwo(input);
    }
}