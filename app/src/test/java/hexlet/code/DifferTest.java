package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DifferTest {

    @Test
    public void emptyFilesTest() throws Exception {
        String result = "{}";
        String filepath1 = "src/test/resources/emptyfile.json";
        String filepath2 = "src/test/resources/emptyfile.json";
        assertEquals(result, Differ.generate(filepath1, filepath2));
    }

    @Test
    public void normalFilesTest() throws Exception {
        String result = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        String filepath1 = "src/test/resources/file1.json";
        String filepath2 = "src/test/resources/file2.json";
        assertEquals(result, Differ.generate(filepath1, filepath2));
    }
}
