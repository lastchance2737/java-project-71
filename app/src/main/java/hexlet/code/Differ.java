package hexlet.code;

import hexlet.code.formatter.Formatter;

import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        // 'generate' - поиск различий между двумя файлами
        Map<String, Object> data1 = Parser.getData(filepath1);
        Map<String, Object> data2 = Parser.getData(filepath2);
        return Formatter.getDiff(data1, data2, format);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        // Перезагрузка метода 'generate', если не задан 'format'
        return generate(filepath1, filepath2, "stylish");
    }
}
