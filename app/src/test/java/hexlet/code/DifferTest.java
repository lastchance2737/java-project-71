package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DifferTest {
    private static final String PLAIN_FORMAT = "plain";
    private static final String STYLISH_FORMAT = "stylish";
    private static final String INCORRECT_FORMAT = "incorrect-format";
    private static final String JSON_FORMAT = "json";

    private static final Path EXPECTED_1 = Paths.get("src/test/resources/expected1").toAbsolutePath().normalize();
    private static final Path EXPECTED_2 = Paths.get("src/test/resources/expected2").toAbsolutePath().normalize();
    private static final Path EXPECTED_3 = Paths.get("src/test/resources/expected3").toAbsolutePath().normalize();
    private static final Path EXPECTED_4 = Paths.get("src/test/resources/expected4").toAbsolutePath().normalize();
    private static final String FILEPATH_EMPTYFILE_JSON = "src/test/resources/emptyfile.json";
    private static final String FILEPATH_FILE1_JSON = "src/test/resources/file1.json";
    private static final String FILEPATH_FILE1_YML = "src/test/resources/file1.yml";
    private static final String FILEPATH_FILE2_JSON = "src/test/resources/file2.json";
    private static final String FILEPATH_FILE2_YAML = "src/test/resources/file2.yaml";
    private static final String FILEPATH_FILE3_JSON = "src/test/resources/file3.json";
    private static final String FILEPATH_FILE3_YML = "src/test/resources/file3.yml";
    private static final String FILEPATH_FILE4_JSON = "src/test/resources/file4.json";
    private static final String FILEPATH_FILE4_YAML = "src/test/resources/file4.yaml";

    @Test
    public void emptyJSONTest() throws Exception {
        String result = "{\n}";
        assertEquals(result, Differ.generate(FILEPATH_EMPTYFILE_JSON, FILEPATH_EMPTYFILE_JSON));
    }

    @Test
    public void jsonTest() throws Exception {
        String result = Files.readString(EXPECTED_1);
        assertEquals(result, Differ.generate(FILEPATH_FILE1_JSON, FILEPATH_FILE2_JSON));
    }

    @Test
    public void yamlTest() throws Exception {
        String result = Files.readString(EXPECTED_1);
        assertEquals(result, Differ.generate(FILEPATH_FILE1_YML, FILEPATH_FILE2_YAML));
    }

    @Test
    public void stylishJSONTest() throws Exception {
        String result = Files.readString(EXPECTED_2);
        assertEquals(result, Differ.generate(FILEPATH_FILE3_JSON, FILEPATH_FILE4_JSON, STYLISH_FORMAT));
    }

    @Test
    public void stylishYAMLTest() throws Exception {
        String result = Files.readString(EXPECTED_2);
        assertEquals(result, Differ.generate(FILEPATH_FILE3_YML, FILEPATH_FILE4_YAML, STYLISH_FORMAT));
    }

    @Test
    public void incorrectFormat() throws Exception {
        Assertions.assertThrows(Exception.class, () ->
                Differ.generate(FILEPATH_FILE3_YML, FILEPATH_FILE4_YAML, INCORRECT_FORMAT));
    }

    @Test
    public void plainJSONTest() throws Exception {
        String result = Files.readString(EXPECTED_3);
        assertEquals(result, Differ.generate(FILEPATH_FILE3_JSON, FILEPATH_FILE4_JSON, PLAIN_FORMAT));
    }

    @Test
    public void plainYAMLTest() throws Exception {
        String result = Files.readString(EXPECTED_3);
        assertEquals(result, Differ.generate(FILEPATH_FILE3_YML, FILEPATH_FILE4_YAML, PLAIN_FORMAT));
    }

    @Test
    public void jsonJSONTest() throws Exception {
        String result = Files.readString(EXPECTED_4);
        assertEquals(result, Differ.generate(FILEPATH_FILE3_JSON, FILEPATH_FILE4_JSON, JSON_FORMAT));
    }

    @Test
    public void jsonYAMLTest() throws Exception {
        String result = Files.readString(EXPECTED_4);
        assertEquals(result, Differ.generate(FILEPATH_FILE3_YML, FILEPATH_FILE4_YAML, JSON_FORMAT));
    }
}
