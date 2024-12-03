package Solutions;

import java.util.Arrays;
import java.util.List;
import java.util.regex.*;
import java.util.stream.Collectors;

public class Day3 {
    private static Pattern mulRegex = Pattern.compile("mul\\([0-9]{0,3},[0-9]{0,3}\\)");
    private static String dontUntilDoRemover = "(?ims)don't\\(\\).*?do\\(\\)";

    public static void SolveOne(String input) {
        int sum = 0;

        Matcher mulMatcher = mulRegex.matcher(input);
        while (mulMatcher.find()) {
            String match = mulMatcher.group();
            List<Integer> digits = Arrays.stream(match.split("[()]")[1].split(",")).map(Integer::valueOf).collect(Collectors.toList());
            sum += digits.get(0) * digits.get(1);
        }

        System.out.println("Day 3: Part 1 solution " + sum);
    }

    public static void SolveTwo(String input) {
        int sum = 0;

        input= input.replaceAll(dontUntilDoRemover, "");

        Matcher mulMatcher = mulRegex.matcher(input);
        while (mulMatcher.find()) {
            String match = mulMatcher.group();
            List<Integer> digits = Arrays.stream(match.split("[()]")[1].split(",")).map(Integer::valueOf).collect(Collectors.toList());
            sum += digits.get(0) * digits.get(1);
        }

        System.out.println("Day 3: Part 2 solution " + sum);
    }
}
