package Solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class Day5 {
    public static void SolveOne(String[] input) {
        int sum = 0;
        boolean foundDivider = false;
        List<int[]> rules = new ArrayList<>();

        for (String string : input) {
            if (string == "") foundDivider = true;
            else if (!foundDivider) {
                String[] rule = string.split("\\|");
                rules.add(new int[]{Integer.parseInt(rule[0]), Integer.parseInt(rule[1])});
            } else if (foundDivider) {
                List<Integer> pages = Arrays.stream(string.split(",")).map(Integer::valueOf).collect(Collectors.toList());
                if (IsValidOrder(rules, pages)) sum += pages.get(pages.size() / 2);
            }
        }

        System.out.println("Day 5: Part 1 solution " + sum);
    }

    public static void SolveTwo(String[] input) {
        int sum = 0;
        boolean foundDivider = false;
        List<int[]> rules = new ArrayList<>();

        for (String string : input) {
            if (string == "") foundDivider = true;
            else if (!foundDivider) {
                String[] rule = string.split("\\|");
                rules.add(new int[]{Integer.parseInt(rule[0]), Integer.parseInt(rule[1])});
            } else if (foundDivider) {
                List<Integer> pages = Arrays.stream(string.split(",")).map(Integer::valueOf).collect(Collectors.toList());
                if (!IsValidOrder(rules, pages)) {
                    List<Integer> correctedOrder = CorrectOrder(pages, rules);
                    sum += correctedOrder.get(correctedOrder.size() / 2);
                }
            }
        }

        System.out.println("Day 5: Part 2 solution " + sum);
    }

    private static boolean IsValidOrder(List<int[]> rules, List<Integer> pages) {
        for (int[] rule : rules) {
            int beforeIndex = pages.indexOf(rule[0]);
            int afterIndex = pages.indexOf(rule[1]);
            if (beforeIndex != -1 && afterIndex != -1 && beforeIndex >= afterIndex) return false;
        }
        return true;
    }

    private static List<Integer> CorrectOrder(List<Integer> pages, List<int[]> rules) {
        List<Integer> sortedPages = new ArrayList<>(pages);
        boolean changed;
        do {
            changed = false;

            for (int[] rule : rules) {
                int beforeIndex = sortedPages.indexOf(rule[0]);
                int afterIndex = sortedPages.indexOf(rule[1]);

                if (beforeIndex != -1 && afterIndex != -1 && beforeIndex > afterIndex) {
                    Collections.swap(sortedPages, beforeIndex, afterIndex);
                    changed = true;
                }
            }
        } while (changed);
        return sortedPages;
    }
}
