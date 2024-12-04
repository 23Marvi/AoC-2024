package Solutions;

import java.util.Arrays;
import java.util.ArrayList;

public class Day4 {
    private static char[][] inputAsChars;
    private static ArrayList<Character> charSequence = new ArrayList<>(Arrays.asList('X', 'M', 'A', 'S'));

    public static void SolveOne(String[] input) {
        int sum = 0;

        InitializeCharsArray(input);

        for (int i = 0; i < input.length; i++) {
            char[] row = input[i].toCharArray();
            for (int j = 0; j < row.length; j++) {
                if (row[j] == 'X') {
                    sum += HasCharAround(i, j, 'M', 4);
                }
            }
        }

        System.out.println("Day 4: Part 1 solution " + sum);
    }

    public static void SolveTwo(String[] input) {
        int sum = 0;

        InitializeCharsArray(input);

        for (int i = 0; i < input.length; i++) {
            char[] row = input[i].toCharArray();
            for (int j = 0; j < row.length; j++) {
                if (row[j] == 'A') {
                    char topLeft = CheckCharAt(i - 1, j - 1);
                    char topRight = CheckCharAt(i - 1, j + 1);
                    char botLeft = CheckCharAt(i + 1, j - 1);
                    char botRight = CheckCharAt(i + 1, j + 1);

                    boolean strike1Match = topLeft == 'M' && botRight == 'S' || topLeft == 'S' && botRight == 'M';
                    boolean strike2Match = topRight == 'M' && botLeft == 'S' || topRight == 'S' && botLeft == 'M';

                    if (strike1Match && strike2Match) {
                        sum++;
                    }
                }
            }
        }

        System.out.println("Day 4: Part 2 solution " + sum);
    }

    private static void InitializeCharsArray(String[] input) {
        inputAsChars = new char[input.length][];
        for (int i = 0; i < input.length; i++) {
            char[] row = input[i].toCharArray();
            inputAsChars[i] = row;
        }
    }

    /**
     * The directions are as following:
     * 0 1 2
     * 3 4 5
     * 6 7 8
     * Where 4 is the current character and 5 as an example, is 1 to the right of the current
     */
    private static int HasCharAround(int y, int x, char charToLookFor, int direction) {
        int hasCharAround = 0;

        for (int Y = y - 1; Y < y + 2; Y++) {
            if (Y < 0 || Y > inputAsChars.length - 1) continue;
            for (int X = x - 1; X < x + 2; X++) {
                if (X < 0 || X > inputAsChars[Y].length - 1) continue;

                char foundChar = inputAsChars[Y][X];
                int localDirection = (Y - y + 1) * 3 + (X - x + 1);

                if (localDirection != direction && direction != 4) continue;
                if (foundChar == charToLookFor) {
                    if (foundChar == 'S') {
                        hasCharAround++;
                        continue;
                    }
                    int nextCharIndex = charSequence.indexOf(charToLookFor) + 1;
                    char nextChar = charSequence.get(nextCharIndex);
                    hasCharAround += HasCharAround(Y, X, nextChar, localDirection);
                }
            }
        }
        return hasCharAround;
    }

    private static char CheckCharAt(int y, int x) {
        if (y < 0 || y > inputAsChars.length - 1 || x < 0 || x > inputAsChars[y].length - 1) return '.';
        return inputAsChars[y][x];
    }
}
