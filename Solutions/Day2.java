package Solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {
    private static int minDifference = 1;
    private static int maxDifference = 3;

    public static void SolveOne(String[] input) {
        int sum = 0;

        for (String report : input) {
            List<Integer> levels = Arrays.stream(report.split(" ")).map(Integer::valueOf).collect(Collectors.toList());
            byte mode = 0;

            for (int i = 1; i < levels.size(); i++) {
                int difference = levels.get(i) - levels.get(i - 1);
                if (Math.abs(difference) < minDifference || Math.abs(difference) > maxDifference)
                    break;
                byte localMode = (byte) (difference > 0 ? 1 : 0);

                if (i == 1) {
                    mode = localMode;
                } else if (localMode != mode) {
                    break;
                } else if (i == levels.size() - 1) {
                    sum++;
                }
            }
        }

        System.out.println("Day 2: Part 1 solution " + sum);
    }

    public static void SolveTwo(String[] input) {
        int sum = 0;

        for (String report : input) {
            List<Integer> levels = Arrays.stream(report.split(" ")).map(Integer::valueOf).collect(Collectors.toList());

            boolean isSafe = isReportSafe(levels);
            if (isSafe) {
                sum++;
                continue;
            }

            boolean canBeSafe = false;
            for (int i = 0; i < levels.size(); i++) {
                List<Integer> modifiedLevels = new ArrayList<>(levels);
                modifiedLevels.remove(i);

                if (isReportSafe(modifiedLevels)) {
                    canBeSafe = true;
                    break;
                }
            }

            if (canBeSafe) {
                sum++;
            }
        }

        System.out.println("Day 2: Part 2 solution " + sum);
    }

    private static boolean isReportSafe(List<Integer> levels) {
        boolean isIncreasing = true, isDecreasing = true;

        for (int i = 1; i < levels.size(); i++) {
            int first = levels.get(i - 1);
            int second = levels.get(i);

            if (DifferenceTooBig(second, first)) {
                return false;
            }

            if (second > first) {
                isDecreasing = false;
            } else if (second < first) {
                isIncreasing = false;
            }
        }

        return isIncreasing || isDecreasing;
    }

    private static boolean DifferenceTooBig(int int1, int int2) {
        int difference = Math.abs(int1 - int2);
        return difference < minDifference || difference > maxDifference;
    }
}
