package hexlet.code.formatter;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String getDiff(String format,
                                 List<Map<String, Object>> result) throws Exception {

        if (format.equals("stylish")) {
            return Stylish.getDiff(result);
        }

        if (format.equals("plain")) {
            return Plain.getDiff(result);
        }

        if (format.equals("json")) {
            return Json.getDiff(result);
        }
        throw new Exception("Incorrect format");
    }
}
