package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.util.Arrays.asList;

public class day1 {
    static int sortedListDistance(List<Integer> left, List<Integer> right) {
        int totalDistance = 0;
        left.sort(Comparator.naturalOrder());
        right.sort(Comparator.naturalOrder());

        for (int i = 0; i < left.size(); i++) {
            int leftValue = left.get(i);
            int rightValue = right.get(i);

            if (leftValue >= rightValue) {
                totalDistance += leftValue - rightValue;
            } else {
                totalDistance += rightValue - leftValue;
            }
        }

        return totalDistance;
    }

    static int listSimilarityScore (List<Integer> left, List<Integer> right) {
        Map<Integer, List<Integer>> similarityMap = new HashMap<>();
        int similarityScore = 0;

        for (int i = 0; i < left.size(); i++) {
            int leftValue = left.get(i);
            int rightValue = right.get(i);
            List<Integer> countList;

            if (similarityMap.containsKey(leftValue)) {
                countList = similarityMap.get(leftValue);
                countList.set(0, countList.get(0) + 1);
                similarityMap.put(leftValue, countList);
            } else {
                similarityMap.put(leftValue, asList(1, 0));
            }

            if (similarityMap.containsKey(rightValue)) {
                countList = similarityMap.get(rightValue);
                countList.set(1, countList.get(1) + 1);
                similarityMap.put(rightValue, countList);
            } else {
                similarityMap.put(rightValue, asList(0, 1));
            }
        }

        for (Map.Entry<Integer, List<Integer>> entry : similarityMap.entrySet()) {
            int key = entry.getKey();
            List<Integer> valueList = entry.getValue();

            similarityScore += key * valueList.get(0) * valueList.get(1);
        }

        return similarityScore;
    }

    public static void main(String[] args) {
        String line;
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        try {
            File input = new File("public/day1_input.txt");
            Scanner scanner = new Scanner(input);

            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] values = line.split("\\s+");
                left.add(Integer.valueOf(values[0]));
                right.add(Integer.valueOf(values[1]));
            }
            int totalDistance = sortedListDistance(left, right);
            int similarityScore = listSimilarityScore(left, right);
            System.out.println("Answer to part 1: " + totalDistance);
            System.out.println("Answer to part 2: " + similarityScore);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the file");
            e.printStackTrace();
        }
    }
}