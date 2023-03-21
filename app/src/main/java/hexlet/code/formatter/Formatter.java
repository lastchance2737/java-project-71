package hexlet.code.formatter;

import java.util.Map;

public class Formatter {
    public static String getDiff(Map<String, Object> data1, Map<String, Object> data2, String format) throws Exception {
        if (format.equals("stylish")) {
            return Stylish.getDiff(data1, data2);
        }
        throw new Exception("Incorrect format");
    }
}
