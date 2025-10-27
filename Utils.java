import java.util.Scanner;

public class Utils {
    public static void printHeader(String message) {
        System.out.println("\n----------------- " + message + " -----------------");
    }

    public static String readNonEmptyString(Scanner scanner, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    public static String readMultiLineContent(Scanner scanner, String prompt, String endKeyword) {
        StringBuilder contentBuilder = new StringBuilder();
        System.out.print(prompt);
        String line;
        while (!(line = scanner.nextLine()).equals(endKeyword)) {
            contentBuilder.append(line).append("\n");
        }
        return contentBuilder.toString().trim();
    }

    public static int readValidInt(Scanner scanner, String prompt, int min, int max) {
        int choice;
        while (true) {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice >= min && choice <= max) {
                return choice;
            }
            System.out.println("Please enter a number between " + min + " and " + max + ".");
        }
    }
}