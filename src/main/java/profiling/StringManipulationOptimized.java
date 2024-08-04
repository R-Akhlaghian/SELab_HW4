package profiling;

import java.util.Random;
import java.util.Scanner;

public class StringManipulationOptimized {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of strings to generate: ");
        int numStrings = scanner.nextInt();
        System.out.println("Enter the length of each string: ");
        int length = scanner.nextInt();

        String[] strings = generateRandomStrings(numStrings, length);

        long startTime, endTime;

        startTime = System.currentTimeMillis();
        String concatenatedString = concatenateStrings(strings);
        endTime = System.currentTimeMillis();
        System.out.println("String concatenation completed in " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        String reversedString = reverseString(concatenatedString);
        endTime = System.currentTimeMillis();
        System.out.println("String reversal completed in " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        boolean found = searchString(concatenatedString, "test");
        endTime = System.currentTimeMillis();
        System.out.println("String search completed in " + (endTime - startTime) + " ms");
        System.out.println("Search result: " + (found ? "Found" : "Not Found"));
    }

    private static String[] generateRandomStrings(int numStrings, int length) {
        Random random = new Random();
        String[] strings = new String[numStrings];
        for (int i = 0; i < numStrings; i++) {
            StringBuilder sb = new StringBuilder(length);
            for (int j = 0; j < length; j++) {
                sb.append((char) ('a' + random.nextInt(26)));
            }
            strings[i] = sb.toString();
        }
        return strings;
    }

    private static String concatenateStrings(String[] strings) {
        StringBuilder sb = new StringBuilder();
        for (String str : strings) {
            sb.append(str);
        }
        return sb.toString();
    }

    private static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    private static boolean searchString(String str, String substr) {
        return str.contains(substr);
    }
}
