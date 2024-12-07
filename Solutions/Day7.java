package Solutions;

import java.util.Arrays;
import java.util.List;

public class Day7 {
    public static void SolveOne(String[] input) {
        long sum = 0;

        for (String string : input) {
            String[] splitString = string.split(":");
            long target = Long.parseLong(splitString[0]);
            long[] values = Arrays.stream(splitString[1].split(" "))
                    .filter(s -> !s.isEmpty())
                    .mapToLong(Long::parseLong)
                    .toArray();
            if (EquationPossible(target, values, Arrays.asList("+", "*"), 0, values[0])) sum += target;
        }

        System.out.println("Day 7: Part 1 solution " + sum);
    }

    public static void SolveTwo(String[] input) {
        long sum = 0;

        for (String string : input) {
            String[] splitString = string.split(":");
            long target = Long.parseLong(splitString[0]);
            long[] values = Arrays.stream(splitString[1].split(" "))
                    .filter(s -> !s.isEmpty())
                    .mapToLong(Long::parseLong)
                    .toArray();
            if (EquationPossible(target, values, Arrays.asList("+", "*", "||"), 0, values[0])) sum += target;
        }

        System.out.println("Day 7: Part 2 solution " + sum);
    }

    private static boolean EquationPossible(long target, long[] numbers, List<String> operators, int index, long currentResult) {
        if (index == numbers.length - 1) return currentResult == target;

        for (String op : operators) {
            long result = 0;
            if (op == "*") {
                result= currentResult * numbers[index + 1];
            } else if (op == "+") {
                result = currentResult + numbers[index + 1];
            } else if (op == "||") {
                result = Long.parseLong(currentResult + "" + numbers[index + 1]);
            }
            if (EquationPossible(target, numbers, operators, index + 1, result)) {
                return true;
            }
        }
        return false;
    }
}
