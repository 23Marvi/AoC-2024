package Solutions;

import java.util.ArrayList;
import java.util.List;

public class Day10 {
    public static void SolveOne(char[][] input) {
        List<String> peaksFound = Solve(input, true);
        System.out.println("Day 10: Part 1 solution " + peaksFound.size());
    }

    public static void SolveTwo(char[][] input) {
        List<String> peaksFound = Solve(input, false);
        System.out.println("Day 10: Part 2 solution " + peaksFound.size());
    }

    private static List<String> Solve(char[][] input, boolean hashSet) {
        List<String> peaksFound = new ArrayList<>();

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] == '0') {
                    peaksFound.addAll(HasIntAround(input, i, j, '1', i + "," + j));
                }
            }
        }
        return hashSet ? peaksFound.stream().distinct().toList() : peaksFound;
    }

    private static ArrayList<String> HasIntAround(char[][] input, int y, int x, char lookFor, String origin) {
        ArrayList<String> peaksFound = new ArrayList<>();

        for (int[] offset : new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }) {
            int newY = y + offset[0];
            int newX = x + offset[1];

            if (newY < 0 || newY >= input.length || newX < 0 || newX >= input[newY].length)
                continue;

            char foundInt = input[newY][newX];

            if (foundInt == lookFor) {
                if (lookFor == '9') {
                    peaksFound.add(origin + ": " + newX + "," + newY);
                    continue;
                }

                peaksFound.addAll(HasIntAround(input, newY, newX, GetNextChar(lookFor), origin));
            }
        }
        return peaksFound;
    }

    private static char GetNextChar(char original) {
        return (char) (original + 1);
    }
}
