package Solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 {
    public static void SolveOne(String[] input) {
        int sum = 0;

        List<ArrayList<Integer>> lists = PrepArrayLists(input);
        ArrayList<Integer> left = lists.get(0);
        ArrayList<Integer> right = lists.get(1);

        left.sort(null);
        right.sort(null);

        for (int i = 0; i < left.size(); i++) {
            sum += Math.abs(left.get(i) - right.get(i));
        }

        System.out.println("Day 1: Part 1 solution " + sum);
    }

    public static void SolveTwo(String[] input) {
        int sum = 0;

        List<ArrayList<Integer>> lists = PrepArrayLists(input);
        ArrayList<Integer> left = lists.get(0);
        ArrayList<Integer> right = lists.get(1);

        for (Integer integer : left) {
            sum += Collections.frequency(right, integer) * integer;
        }

        System.out.println("Day 1: Part 2 solution " + sum);
    }

    private static List<ArrayList<Integer>> PrepArrayLists(String[] input) {
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        for (String string : input) {
            String[] parts = string.trim().split("\\s+");
            left.add(Integer.parseInt(parts[0]));
            right.add(Integer.parseInt(parts[1]));
        }
        List<ArrayList<Integer>> lists = new ArrayList<>();
        lists.add(left);
        lists.add(right);
        return lists;
    }
}
