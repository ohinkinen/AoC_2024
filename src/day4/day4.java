package day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class day4 {
    static boolean checkWord(String word, ArrayList<ArrayList<Character>> charMatrix, Integer xCoord, Integer yCoord, Integer xChange, Integer yChange, Integer round) {
        if (round == 0) {
            int xLimit = xCoord + xChange * (word.length() - 1);
            int yLimit = yCoord + yChange * (word.length() - 1);

            if (xLimit < 0 || xLimit > charMatrix.get(yCoord).size() - 1 || yLimit < 0 || yLimit > charMatrix.size() - 1) {
                return false;
            }
        }

        if (round != word.length()-1) {
            if (word.charAt(round) == charMatrix.get(yCoord).get(xCoord)) {
                return checkWord(word, charMatrix, xCoord + xChange, yCoord + yChange, xChange, yChange, round + 1);
            } else {
                return false;
            }
        } else {
            return word.charAt(round) == charMatrix.get(yCoord).get(xCoord); 
        }
    }

    static int countXmas(ArrayList<ArrayList<Character>> charMatrix) {
        ArrayList<Character> row;
        int xmasCount = 0;
        for (int i = 0; i < charMatrix.size(); i++) {
            row = charMatrix.get(i);
            for (int j = 0; j < row.size(); j++) {
                if (row.get(j) == 'X') {
                    if (checkWord("XMAS", charMatrix, j, i, -1, 0, 0)) {
                        xmasCount++;
                    }

                    if (checkWord("XMAS", charMatrix, j, i, 1, 0, 0)) {
                        xmasCount++;
                    }

                    if (checkWord("XMAS", charMatrix, j, i, 0, -1, 0)) {
                        xmasCount++;
                    }

                    if (checkWord("XMAS", charMatrix, j, i, 0, 1, 0)) {
                        xmasCount++;
                    }

                    if (checkWord("XMAS", charMatrix, j, i, -1, -1, 0)) {
                        xmasCount++;
                    }

                    if (checkWord("XMAS", charMatrix, j, i, -1, 1, 0)) {
                        xmasCount++;
                    }

                    if (checkWord("XMAS", charMatrix, j, i, 1, -1, 0)) {
                        xmasCount++;
                    }

                    if (checkWord("XMAS", charMatrix, j, i, 1, 1, 0)) {
                        xmasCount++;
                    }
                }
            }
        }
        return xmasCount;
    }

    static int countX_mas(ArrayList<ArrayList<Character>> charMatrix) {
        ArrayList<Character> row;
        int xmasCount = 0;
        for (int i = 1; i < charMatrix.size() - 1; i++) {
            row = charMatrix.get(i);
            for (int j = 1; j < row.size() - 1; j++) {
                if (row.get(j) == 'A') {
                    String word1 = String.valueOf(charMatrix.get(i - 1).get(j - 1)) + charMatrix.get(i).get(j) + charMatrix.get(i + 1).get(j + 1);
                    String word2 = String.valueOf(charMatrix.get(i - 1).get(j + 1)) + charMatrix.get(i).get(j) + charMatrix.get(i + 1).get(j - 1);

                    if ((word1.equals("MAS") || word1.equals("SAM")) && (word2.equals("MAS") || word2.equals("SAM"))) {
                        xmasCount++;
                    }
                }
            }
        }
        return xmasCount;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Character>> charMatrix = new ArrayList<>();

        try {
            File input = new File("public/day4_input.txt");
            Scanner scanner = new Scanner(input);

            while(scanner.hasNextLine()) {
                charMatrix.add(new ArrayList<>(scanner.nextLine().chars().mapToObj(c -> (char) c).collect(Collectors.toList())));
            }

            int xmasCount = countXmas(charMatrix);
            int x_masCount = countX_mas(charMatrix);

            System.out.println("Answer for part 1: " + xmasCount);
            System.out.println("Answer for part 2: " + x_masCount);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the file");
        }
    }
}