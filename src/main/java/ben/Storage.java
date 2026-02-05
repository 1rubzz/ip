package ben;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles loading and saving of task data to the data file.
 */
public class Storage {

    private final Path filePath; // for different OSs

    /**
     * Creates a Storage object with the given file path.
     *
     * @param filePath Relative path to the data file.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath); // string will be used by caller
    }

    /**
     * Saves task data to the data file.
     *
     * @param lines Lines to be saved.
     * @throws IOException If writing to the file fails.
     */
    public void save(List<String> lines) throws IOException {
        // make sure the directory and file are there
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, lines);
    }

    /**
     * Loads task data from the data file.
     *
     * @return List of lines from the data file.
     * @throws IOException If reading from the file fails.
     */
    public ArrayList<String> load() throws IOException, BenException {
        if (!Files.exists(filePath)) {
            // return an empty list
            ArrayList<String> list = new ArrayList<>();
            return list;
        }
        ArrayList<String> list = new ArrayList<>(Files.readAllLines(filePath));
        return list;
    }

}
