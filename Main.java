//
// Author: Durgesh Mahajan
// Date: 2023-10-27
// Project: Personal Journal Application
//

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JournalManager journalManager = new JournalManager();

        while (true) {
            System.out.println("\n----------------- Personal Journal Application -----------------");
            System.out.println("1. Add Entry");
            System.out.println("2. View Entries");
            System.out.println("3. Edit Entry");
            System.out.println("4. Delete Entry");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addEntry(scanner, journalManager);
                    break;
                case "2":
                    viewEntries(scanner, journalManager);
                    break;
                case "3":
                    editEntry(scanner, journalManager);
                    break;
                case "4":
                    deleteEntry(scanner, journalManager);
                    break;
                case "5":
                    System.out.println("Exiting the application.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addEntry(Scanner scanner, JournalManager journalManager) {
        System.out.print("Enter the title of the entry: ");
        String title = scanner.nextLine();
        System.out.println("Enter the content of the entry (type 'END_ENTRY' on a new line to finish): ");
        StringBuilder contentBuilder = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).equals("END_ENTRY")) {
            contentBuilder.append(line).append("\n");
        }
        String content = contentBuilder.toString().trim();

        JournalEntry entry = new JournalEntry(title, content);
        journalManager.saveEntry(entry);
        System.out.println("Entry added successfully.");
    }

    private static void viewEntries(Scanner scanner, JournalManager journalManager) {
        List<JournalEntry> entries = journalManager.loadAllEntries();
        if (entries.isEmpty()) {
            System.out.println("No entries found.");
            return;
        }

        System.out.println("\nList of Entries:");
        for (int i = 0; i < entries.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, entries.get(i));
        }

        System.out.print("Enter the number of the entry to view (or '0' to go back): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice > 0 && choice <= entries.size()) {
            JournalEntry entry = entries.get(choice - 1);
            System.out.println(entry.getDetailedContent());
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private static void editEntry(Scanner scanner, JournalManager journalManager) {
        List<JournalEntry> entries = journalManager.loadAllEntries();
        if (entries.isEmpty()) {
            System.out.println("No entries found.");
            return;
        }

        System.out.println("\nList of Entries:");
        for (int i = 0; i < entries.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, entries.get(i));
        }

        System.out.print("Enter the number of the entry to edit (or '0' to go back): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice > 0 && choice <= entries.size()) {
            JournalEntry entry = entries.get(choice - 1);
            System.out.printf("Editing Entry: %s\n", entry.getTitle());
            System.out.print("Enter the new title (leave blank to keep the same): ");
            String newTitle = scanner.nextLine().trim();
            System.out.println("Enter the new content (type 'END_ENTRY' on a new line to finish): ");
            StringBuilder newContentBuilder = new StringBuilder();
            String line;
            while (!(line = scanner.nextLine()).equals("END_ENTRY")) {
                newContentBuilder.append(line).append("\n");
            }
            String newContent = newContentBuilder.toString().trim();

            if (!newTitle.isEmpty()) {
                entry.setTitle(newTitle);
            }
            if (!newContent.isEmpty()) {
                entry.setContent(newContent);
            }

            JournalEntry newEntry = new JournalEntry(entry.getDate(), entry.getTitle(), entry.getContent());
            journalManager.updateEntry(entry, newEntry);
            System.out.println("Entry updated successfully.");
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private static void deleteEntry(Scanner scanner, JournalManager journalManager) {
        List<JournalEntry> entries = journalManager.loadAllEntries();
        if (entries.isEmpty()) {
            System.out.println("No entries found.");
            return;
        }

        System.out.println("\nList of Entries:");
        for (int i = 0; i < entries.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, entries.get(i));
        }

        System.out.print("Enter the number of the entry to delete (or '0' to go back): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice > 0 && choice <= entries.size()) {
            JournalEntry entry = entries.get(choice - 1);
            System.out.printf("Are you sure you want to delete the entry '%s'? (yes/no): ", entry.getTitle());
            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (confirmation.equals("yes")) {
                journalManager.deleteEntry(entry);
                System.out.println("Entry deleted successfully.");
            } else {
                System.out.println("Deletion cancelled.");
            }
        } else {
            System.out.println("Invalid selection.");
        }
    }
}