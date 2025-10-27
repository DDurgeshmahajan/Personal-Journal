import java.time.LocalDate;

public class JournalEntry {
    private LocalDate date;
    private String title;
    private String content;
    private String filename;

    public JournalEntry(String title, String content) {
        this.date = LocalDate.now();
        this.title = title;
        this.content = content;
        this.filename = generateFilename();
    }

    public JournalEntry(LocalDate date, String title, String content) {
        this.date = date;
        this.title = title;
        this.content = content;
        this.filename = generateFilename();
    }

    private String generateFilename() {
        return date.toString() + "_" + sanitizeTitle(title) + ".txt";
    }

    private String sanitizeTitle(String title) {
        return title.replaceAll("[\\/:*?\"<>|]", "").replaceAll("\s+", "_");
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
        this.filename = generateFilename();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.filename = generateFilename();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilename() {
        return filename;
    }

    @Override
    public String toString() {
        return '[' + date + '] ' + title;
    }

    public String getDetailedContent() {
        return "Title: " + title + "\nDate: " + date + "\n\n" + content;
    }
}