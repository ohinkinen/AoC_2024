package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class day2 {
    static boolean checkReport(String[] report) {
        int current;
        int next;
        double sign = 0.0;

        for (int j = 0; j < report.length - 1; j++) {
            current = Integer.parseInt(report[j]);
            next = Integer.parseInt(report[j+1]);

            if (j == 0) {
                sign = Math.signum(current - next);
            }

            if (current - next == 0 || Math.abs(current - next) > 3 || Math.signum(current - next) != sign) {
                return false;
            }
        }

        return true;
    }
    static int countSafeReports(List<String[]> reports, boolean dampener) {
        boolean isSafe;
        int safeCount = 0;

        for (String[] report : reports) {
            isSafe =  checkReport(report);

            if (dampener && !isSafe) {
                for (int i = 0; i < report.length; i++) {
                    ArrayList<String> permutation = new ArrayList<>(Arrays.asList(report));
                    permutation.remove(i);

                    isSafe = checkReport(permutation.toArray(new String[0]));

                    if (isSafe) {
                        break;
                    }
                }
            }

            if (isSafe)  {
                safeCount++;
            }
        }
        return safeCount;
    }

    public static void main(String[] args) {
        String line;
        List<String[]> reports = new ArrayList<>();

        try {
            File input = new File("public/day2_input.txt");
            Scanner scanner = new Scanner(input);

            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                reports.add(line.split("\\s+"));
            }

            int safeCountWithoutDampener = countSafeReports(reports, false);
            int safeCountWithDampener = countSafeReports(reports, true);


            System.out.println("Answer for part 1: " + safeCountWithoutDampener);
            System.out.println("Answer for part 2: " + safeCountWithDampener);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the file");
        }
    }
}
