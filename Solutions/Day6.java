package Solutions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Day6 {
    private static char guard = '^';
    private static char obstruction = '#';
    private static int[][] directions = {
        {1, -1},  // Up
        {0, 1},   // Right
        {1, 1},   // Down
        {0, -1}   // Left
    };

    public static void SolveOne(char[][] input) {
        HashSet<String> visited = GetVisited(input);
        System.out.println("Day 6: Part 1 solution " + visited.size());
    }

    public static void SolveTwo(char[][] input) {
        HashSet<String> visited = GetVisited(input);
        HashSet<String> newObsctructions = new HashSet<String>();

        for (String string : visited) {
            HashSet<String> localVisited = new HashSet<>();
            List<Integer> coordinates = Arrays.stream(string.split(",")).map(Integer::valueOf).toList();
            int x = coordinates.get(0), y = coordinates.get(1);
            char initialChar = input[y][x];
            int directionIndex = 0;
            int[] guardPos = GuardPos(input);
            input[y][x] = obstruction;

            do {
                int[] direction = directions[directionIndex];
                int newX = guardPos[0] + (direction[0] == 0 ? direction[1] : 0);
                int newY = guardPos[1] + (direction[0] == 1 ? direction[1] : 0);
                String toVisit = guardPos[0] + "," + guardPos[1] + "," + directionIndex;

                if (localVisited.contains(toVisit)) {
                    newObsctructions.add(x + "," + y);
                    break;
                }
                if (IsOutOfBounds(input, newX, newY)) break;
                if (input[newY][newX] == obstruction) {
                    directionIndex = (directionIndex + 1) % 4;
                    continue;
                }

                localVisited.add(toVisit);
                guardPos[0] = newX;
                guardPos[1] = newY;
            } while (true);

            input[y][x] = initialChar;
        }

        System.out.println("Day 6: Part 2 solution " + newObsctructions.size());
    }

    /**
     * Direction in an int[]
     * First int is horiziontal (0) or vertical (1)
     * Second int is direction (left/up (-1) or right/down (1))
     */
    public static HashSet<String> GetVisited(char[][] input) {
        HashSet<String> visited = new HashSet<>();
        int[] guardPos = GuardPos(input);
        int directionIndex = 0;

        do {
            visited.add(guardPos[0] + "," + guardPos[1]);
            int[] direction = directions[directionIndex];
            int newX = guardPos[0] + (direction[0] == 0 ? direction[1] : 0);
            int newY = guardPos[1] + (direction[0] == 1 ? direction[1] : 0);

            if (IsOutOfBounds(input, newX, newY)) break;
            if (input[newY][newX] == obstruction) {
                directionIndex = (directionIndex + 1) % 4;
                continue;
            }

            guardPos[0] = newX;
            guardPos[1] = newY;
        } while (true);

        return visited;
    }

    public static boolean IsOutOfBounds(char[][] input, int x, int y) {
        return y < 0 || y >= input.length || x < 0 ||  x >= input[y].length;
    }

    public static int[] GuardPos(char[][] input) {
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[y].length; x++) {
                if (input[y][x] == guard)
                    return new int[] { x, y };
            }
        }
        return null;
    }
}
