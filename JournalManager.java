import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JournalManager {
    private static final String JOURNALS_DIR_NAME = "journals";
    private Path journalsDirectory;

    public JournalManager() {
        journalsDirectory = Paths.get(JOURNALS_DIR_NAME);
        try {
            Files.createDirectories(journalsDirectory);
        } catch (IOException e) {
            System.err.println("Error creating journals directory: " + e.getMessage());
        }
    }

    public void saveEntry(JournalEntry entry) {
        Path entryPath = journalsDirectory.resolve(entry.getFilename());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(entryPath.toFile()))) {
            writer.write("Title: " + entry.getTitle() + "\n");
            writer.write("Date: " + entry.getDate().toString() + "\n\n");
            writer.write(entry.getContent());
        } catch (IOException e) {
            System.err.println("Error saving entry: " + e.getMessage());
        }
    }

    public List<JournalEntry> loadAllEntries() {
        List<JournalEntry> entries = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(journalsDirectory)) {
            File[] files = journalsDirectory.toFile().listFiles((dir, name) -> name.endsWith(".txt"));
            if (files != null) {
                for (File file : files) {
                    try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                        String title = fileReader.readLine().substring(7); // Skip "Title: "
                        fileReader.readLine(); // Skip empty line
                        String dateStr = fileReader.readLine().substring(6); // Skip "Date: "
                        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
                        fileReader.readLine(); // Skip empty line
                        StringBuilder contentBuilder = new StringBuilder();
                        String line;
                        while ((line = fileReader.readLine()) != null) {
                            contentBuilder.append(line).append("\n");
                        }
                        String content = contentBuilder.toString().trim();
                        entries.add(new JournalEntry(date, title, content));
                    } catch (IOException | DateTimeParseException e) {
                        System.err.println("Error reading entry file: " + file.getName() + " - " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading entries: " + e.getMessage());
        }
        return entries;
    }

    public JournalEntry findEntryByFilename(String filename) {
        File file = journalsDirectory.resolve(filename).toFile();
        if (!file.exists()) {
            return null;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String title = reader.readLine().substring(7); // Skip "Title: "
            reader.readLine(); // Skip empty line
            String dateStr = reader.readLine().substring(6); // Skip "Date: "
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            reader.readLine(); // Skip empty line
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
            String content = contentBuilder.toString().trim();
            return new JournalEntry(date, title, content);
        } catch (IOException | DateTimeParseException e) {
            System.err.println("Error reading entry file: " + filename + " - " + e.getMessage());
        }
        return null;
    }

    public void updateEntry(JournalEntry oldEntry, JournalEntry newEntry) {
        File oldFile = journalsDirectory.resolve(oldEntry.getFilename()).toFile();
        File newFile = journalsDirectory.resolve(newEntry.getFilename()).toFile();

        if (oldFile.delete()) {
            saveEntry(newEntry);
            System.out.println("Entry updated successfully.");
        } else {
            System.out.println("Error updating entry.");
        }
    }

    public void deleteEntry(JournalEntry entry) {
        File file = journalsDirectory.resolve(entry.getFilename()).toFile();
        if (file.delete()) {
            System.out.println("Entry deleted successfully.");
        } else {
            System.out.println("Error deleting entry.");
        }
    }
}