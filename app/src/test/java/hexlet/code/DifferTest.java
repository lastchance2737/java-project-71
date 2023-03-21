package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DifferTest {

    @Test
    public void emptyJSONTest() throws Exception {
        String result = "{}";
        String filepath1 = "src/test/resources/emptyfile.json";
        String filepath2 = "src/test/resources/emptyfile.json";
        assertEquals(result, Differ.generate(filepath1, filepath2));
    }

    @Test
    public void jsonTest() throws Exception {
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

    @Test
    public void yamlTest() throws Exception {
        String result = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        String filepath1 = "src/test/resources/file1.yml";
        String filepath2 = "src/test/resources/file2.yaml";
        assertEquals(result, Differ.generate(filepath1, filepath2));
    }

    @Test
    public void stylishJSONTest() throws Exception {
        String result = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";
        String filepath1 = "src/test/resources/file3.json";
        String filepath2 = "src/test/resources/file4.json";
        String format = "stylish";
        assertEquals(result, Differ.generate(filepath1, filepath2, format));
    }

    @Test
    public void stylishYAMLTest() throws Exception {
        String result = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";
        String filepath1 = "src/test/resources/file3.yml";
        String filepath2 = "src/test/resources/file4.yaml";
        String format = "stylish";
        assertEquals(result, Differ.generate(filepath1, filepath2, format));
    }

    @Test
    public void incorrectFormat() throws Exception {
        String filepath1 = "src/test/resources/file3.yml";
        String filepath2 = "src/test/resources/file4.yaml";
        String format = "incorrect-format";
        Assertions.assertThrows(Exception.class, () -> Differ.generate(filepath1, filepath2, format));
    }
}
