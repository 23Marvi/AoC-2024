package Solutions;

import java.util.ArrayList;
import java.util.Collections;

public class Day9 {
    private static String filler = ".";

    public static void SolveOne(String input) {
        ArrayList<String> diskmap = FileCompactFirstStage(input);

        boolean completeBreak = false;
        for (int i = 0; i < diskmap.size(); i++) {
            if (completeBreak)
                break;
            if (diskmap.get(i) == filler) {
                for (int j = diskmap.size() - 1; j >= 0; j--) {
                    if (diskmap.get(j) != filler) {
                        if (i >= j) {
                            completeBreak = true;
                            break;
                        }
                        Collections.swap(diskmap, i, j);
                        break;
                    }
                }
            }
        }
        long sum = GetSum(diskmap);

        System.out.println("Day 9: Part 1 solution " + sum);
    }

    public static void SolveTwo(String input) {
        ArrayList<String> diskmap = FileCompactFirstStage(input);

        int id = Integer.parseInt(diskmap.get(diskmap.size() - 1));
        do {
            if (id <= 0) {
                break;
            }

            String idString = String.valueOf(id);
            int idOccurrences = Collections.frequency(diskmap, idString);
            int indexOfId = diskmap.indexOf(idString);

            int freeSpaceStart = -1;
            int freeSpaceCount = 0;
            for (int i = 0; i < diskmap.size(); i++) {
                if (diskmap.get(i).equals(filler)) {
                    if (freeSpaceCount == 0) {
                        freeSpaceStart = i;
                    }
                    freeSpaceCount++;
                    if (freeSpaceCount == idOccurrences) break;
                } else {
                    freeSpaceCount = 0;
                }
            }

            if (freeSpaceCount == idOccurrences && freeSpaceStart < indexOfId) {
                int offset = freeSpaceStart - indexOfId;
                for (int i = 0; i < idOccurrences; i++) {
                    diskmap.set(indexOfId + i, filler);
                    diskmap.set(indexOfId + i + offset, idString);
                }
            }

            id--;
        } while (true);
        long sum = GetSum(diskmap);

        System.out.println("Day 9: Part 2 solution " + sum);
    }

    private static ArrayList<String> FileCompactFirstStage(String input) {
        ArrayList<String> diskmap = new ArrayList<>();
        int id = 0;

        for (int i = 0; i < input.length(); i++) {
            int digit = input.charAt(i) - '0';
            String value = (i % 2 == 0) ? String.valueOf(id++) : filler;
            for (int j = 0; j < digit; j++) {
                diskmap.add(value);
            }
        }

        return diskmap;
    }

    private static long GetSum(ArrayList<String> diskmap) {
        long sum = 0;
        for (int i = 0; i < diskmap.size(); i++) {
            String current = diskmap.get(i);
            if (current.equals(filler)) continue;
            sum += (i * Integer.parseInt(current));
        }
        return sum;
    }
}
