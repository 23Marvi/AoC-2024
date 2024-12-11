package Solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day11 {
    private static final Map<String, Long> cache = new HashMap<>();

    public static void SolveOne(String input) {
        long sum = Solve(input, 25);
        System.out.println("Day 11: Part 1 solution " + sum);
    }

    public static void SolveTwo(String input) {
        long sum = Solve(input, 75);
        System.out.println("Day 11: Part 2 solution " + sum);
    }

    private static long Solve(String input, int iterations) {
        List<Long> stones = Arrays.stream(input.split(" ")).map(Long::parseLong).toList();
        long sum = 0;
        for (long stone : stones) {
            sum += Blink(stone, iterations);
        }
        return sum;
    }

    public static long Blink(long stone, int remaining) {
        String cacheKey = stone + "," + remaining;
        if (cache.containsKey(cacheKey)) return cache.get(cacheKey);
        if (remaining == 0) return 1;

        long result;
        remaining--;

        if (stone == 0) {
            result = Blink(1, remaining);
        } else if (String.valueOf(stone).length() % 2 == 0) {
            String stoneString = String.valueOf(stone);
            int half = stoneString.length() / 2;
            int firstHalf = Integer.parseInt(stoneString.substring(0, half));
            int secondHalf = Integer.parseInt(stoneString.substring(half));
            result = Blink(firstHalf, remaining) + Blink(secondHalf, remaining);
        } else {
            result = Blink(stone * 2024, remaining);
        }

        cache.put(cacheKey, result);
        return result;
    }
}
