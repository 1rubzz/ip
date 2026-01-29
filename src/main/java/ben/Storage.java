package ben;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;

/**
 * Handles loading and saving of task data to data file.
 *
 */
public class Storage {

    private final Path filePath; // for different OSs

    /**
     * Create a Storage Object with a file path.
     *
     * @param filePath Relative path to the file.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath); // string will be used by caller
    }

    /**
     * Saves task data to the data file.
     *
     * @param lines Lines to be saved.
     * @throws IOException If the writing to disk fails.
     */
    public void save(List<String> lines) throws IOException {
        // make sure the directory and file are there
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, lines);
    }

    /**
     * Loads data from the data file.
     *
     * @return List of lines from the data file.
     * @throws IOException If reading fails.
     */
    public List<String> load() throws IOException {
        if (!Files.exists(filePath)) {
            // return an empty list
            return List.of();
        }
        return Files.readAllLines(filePath);
    }

}
