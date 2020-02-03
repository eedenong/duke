import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private Path filePath;
    private File file;

    public Storage(String fileName) {
        // Load the file
        String currDir = System.getProperty("user.dir");
        this.filePath =  Paths.get(currDir, "list.txt");
        this.file = new File(filePath.toString());
        try {
            // create the file if it does not exist
            if (!file.exists()) {
                this.file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    public Path getFilePath() {
        return this.filePath;
    }

    public File getFile() {
        return this.file;
    }

    public ArrayList<String> loadFromFilePath() {
        List<String> lines = new ArrayList<>();
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNext()) {
                lines.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Task list save file not found!");
        }
        return new ArrayList<>(lines);
    }

    public void saveFile(ArrayList<Task> taskList) {
        try {
            File file = new File(this.filePath.toString());
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Task t : taskList) {
                String taskString = t.toString();
                bw.write(taskString);
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving to file! :-(");
        }

    }


}
