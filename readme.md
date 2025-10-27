```markdown
# Personal Journal Application

## Description
The Personal Journal Application is a simple and intuitive command-line tool designed to help you keep track of your thoughts, ideas, and daily reflections. Whether you're a writer, a student, or just someone who likes to journal, this application makes it easy to add, view, edit, and delete journal entries.

## Features
- **Add Entry:** Create a new journal entry with a title and content.
- **View Entries:** List and view all your previous entries.
- **Edit Entry:** Modify the title, content, or date of an existing entry.
- **Delete Entry:** Remove entries you no longer need.
- **Persistent Storage:** Entries are saved to text files in a dedicated directory, ensuring your data is always safe.

## Usage Instructions
### Prerequisites
- Java 8 or higher installed on your system.

### Running the Application
1. **Clone the Repository:**
   ```sh
   git clone https://github.com/DDurgeshmahajan/Personal-Journal-Application.git
   ```

2. **Navigate to the Project Directory:**
   ```sh
   cd Personal-Journal-Application
   ```

3. **Compile the Java Files:**
   ```sh
   javac Main.java JournalEntry.java JournalManager.java Utils.java
   ```

4. **Run the Application:**
   ```sh
   java Main
   ```

### Using the Application
- **Add Entry:**
  - Select option `1` from the menu.
  - Enter a title and content for your entry.
  - Type `END_ENTRY` on a new line to finish the entry.

- **View Entries:**
  - Select option `2` from the menu.
  - Choose a number to view the detailed content of a specific entry.
  - Type `0` to go back to the main menu.

- **Edit Entry:**
  - Select option `3` from the menu.
  - Choose a number to edit a specific entry.
  - Enter new title and content if you want to make changes.
  - Type `END_ENTRY` on a new line to finish editing.

- **Delete Entry:**
  - Select option `4` from the menu.
  - Choose a number to delete a specific entry.
  - Confirm the deletion by typing `yes`.

- **Exit the Application:**
  - Select option `5` from the menu.

## Example/Preview
### Sample Output
```
----------------- Personal Journal Application -----------------
1. Add Entry
2. View Entries
3. Edit Entry
4. Delete Entry
5. Exit
Select an option: 1
Enter the title of the entry: My First Day
Enter the content of the entry (type 'END_ENTRY' on a new line to finish):
Today was a great day. I started my new job and met some amazing people.
END_ENTRY
Entry added successfully.
```

## Contact / Author
If you have any questions or suggestions, feel free to reach out to me.

- **Name:** Durgesh Mahajan
- **Email:** ashamahajan955@gmail.com

Happy journaling!
```

This `README.md` file provides a clear and concise overview of the Personal Journal Application, including its features, usage instructions, and contact information. It is designed to be beginner-friendly and easy to follow.