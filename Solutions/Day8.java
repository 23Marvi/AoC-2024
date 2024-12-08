package Solutions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day8 {
    private static char filler = '.';

    public static void SolveOne(String[] input) {
        Solve(input, false, "Day 8: Part 1 solution");
    }

    public static void SolveTwo(String[] input) {
        Solve(input, true, "Day 8: Part 2 solution");
    }

    private static void Solve(String[] input, boolean flag, String message) {
        Set<String> multinodes = new HashSet<>();
        int[] mapSize = new int[] { input[0].length(), input.length };

        ArrayList<String> antennas = new ArrayList<>();
        Set<Character> frequencies = new HashSet<>();
        for (int y = 0; y < input.length; y++) {
            char[] row = input[y].toCharArray();
            for (int x = 0; x < row.length; x++) {
                if (row[x] != filler) {
                    antennas.add(row[x] + "," + x + "," + y);
                    frequencies.add(row[x]);
                }
            }
        }

        for (Character frequency : frequencies) {
            List<int[]> coordinates = antennas.stream()
                    .filter(s -> s.startsWith(frequency.toString()))
                    .map(s -> {
                        String[] parts = s.split(",");
                        return new int[] {
                                Integer.parseInt(parts[1]),
                                Integer.parseInt(parts[2])
                        };
                    })
                    .collect(Collectors.toList());
            multinodes.addAll(GetMultinodes(frequency, coordinates, mapSize, flag));
        }

        System.out.println(message + " " + multinodes.size());
    }

    private static Set<String> GetMultinodes(char frequency, List<int[]> coordinates, int[] mapSize, boolean extendMultinodes) {
        Set<String> multinodes = new HashSet<>();

        for (int i = 0; i < coordinates.size(); i++) {
            int[] firstCoordinates = coordinates.get(i);
            for (int j = i + 1; j < coordinates.size(); j++) {
                int[] secondCoordinates = coordinates.get(j);

                int xDifference = Math.abs(firstCoordinates[0] - secondCoordinates[0]);
                int yDifference = Math.abs(firstCoordinates[1] - secondCoordinates[1]);

                int xNode1Difference = firstCoordinates[0] > secondCoordinates[0] ? xDifference : -xDifference;
                int yNode1Difference = firstCoordinates[1] > secondCoordinates[1] ? yDifference : -yDifference;
                int xNode2Difference = secondCoordinates[0] < firstCoordinates[0] ? -xDifference : xDifference;
                int yNode2Difference = secondCoordinates[1] < firstCoordinates[1] ? -yDifference : yDifference;

                int[] multiNode1 = new int[] {
                    firstCoordinates[0] + xNode1Difference,
                    firstCoordinates[1] + yNode1Difference
                };

                int[] multiNode2 = new int[] {
                    secondCoordinates[0] + xNode2Difference,
                    secondCoordinates[1] + yNode2Difference
                };

                if (!IsOutOfBounds(mapSize, multiNode1)) {
                    multinodes.add(multiNode1[0] + "," + multiNode1[1]);
                    if (extendMultinodes) {
                        multinodes.addAll(ExtendMultinode(new int[] { multiNode1[0], multiNode1[1] }, new int[] { xNode1Difference, yNode1Difference }, mapSize));
                    }
                }
                if (!IsOutOfBounds(mapSize, multiNode2)) {
                    multinodes.add(multiNode2[0] + "," + multiNode2[1]);
                    if (extendMultinodes) {
                        multinodes.addAll(ExtendMultinode(new int[] { multiNode2[0], multiNode2[1] }, new int[] { xNode2Difference, yNode2Difference }, mapSize));
                    }
                }

                if (extendMultinodes) {
                    multinodes.add(firstCoordinates[0] + "," + firstCoordinates[1]);
                    multinodes.add(secondCoordinates[0] + "," + secondCoordinates[1]);
                }
            }
        }
        return multinodes;
    }

    private static boolean IsOutOfBounds(int[] mapSize, int[] coords) {
        return coords[1] < 0 || coords[1] >= mapSize[1] || coords[0] < 0 || coords[0] >= mapSize[0];
    }

    private static Set<String> ExtendMultinode(int[] originalCoords, int[] extension, int[] mapSize) {
        Set<String> multinodes = new HashSet<>();
        int[] newCoords = new int[] { originalCoords[0] + extension[0], originalCoords[1] + extension[1] };
        if (!IsOutOfBounds(mapSize, newCoords)) {
            multinodes.add(newCoords[0] + "," + newCoords[1]);
            multinodes.addAll(ExtendMultinode(newCoords, extension, mapSize));
        }
        return multinodes;
    }
}
