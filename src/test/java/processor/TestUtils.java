package processor;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUtils {

    /**
     * Helper to get files from project resources.
     *
     * @param resourceName
     * @return
     */
    public static File getFileFromResources(String resourceName) {
        URL fileUrl = TestUtils.class
                .getClassLoader()
                .getResource(resourceName);
        assert fileUrl != null;
        File file = new File(fileUrl.getPath());
        assertTrue(file.exists());
        return file;
    }

    /**
     * Helper to get files from resources by referencing file number.
     *
     * @param inputFileIndex
     * @param formatString
     * @return
     */
    public static File getFileFromResources(int inputFileIndex, String formatString) {
        return TestUtils.getFileFromResources(String.format(formatString, inputFileIndex));
    }
}
