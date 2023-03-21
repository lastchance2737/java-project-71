package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DifferTest {
    private static final String PLAIN_FORMAT = "plain";
    private static final String STYLISH_FORMAT = "stylish";
    private static final String INCORRECT_FORMAT = "incorrect-format";

    @Test
    public void emptyJSONTest() throws Exception {
        String result = "{\n}";
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
        assertEquals(result, Differ.generate(filepath1, filepath2, STYLISH_FORMAT));
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
        assertEquals(result, Differ.generate(filepath1, filepath2, STYLISH_FORMAT));
    }

    @Test
    public void incorrectFormat() throws Exception {
        String filepath1 = "src/test/resources/file3.yml";
        String filepath2 = "src/test/resources/file4.yaml";
        Assertions.assertThrows(Exception.class, () -> Differ.generate(filepath1, filepath2, INCORRECT_FORMAT));
    }

    @Test
    public void plainJSONTest() throws Exception {
        String result = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'""";
        String filepath1 = "src/test/resources/file3.json";
        String filepath2 = "src/test/resources/file4.json";
        assertEquals(result, Differ.generate(filepath1, filepath2, PLAIN_FORMAT));
    }

    @Test
    public void plainYAMLTest() throws Exception {
        String result = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'""";
        String filepath1 = "src/test/resources/file3.yml";
        String filepath2 = "src/test/resources/file4.yaml";
        assertEquals(result, Differ.generate(filepath1, filepath2, PLAIN_FORMAT));
    }
}
