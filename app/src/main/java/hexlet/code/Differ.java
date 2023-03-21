package hexlet.code;

import hexlet.code.formatter.Formatter;

import java.util.Map;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.LinkedHashMap;

public class Differ {
    private static final String STATUS_REMOVED = "removed";
    private static final String STATUS_ADDED = "added";
    private static final String STATUS_UPDATED = "updated";
    private static final String STATUS_UNCHANGED = "unchanged";

    public static String generate(String filepath1,
                                  String filepath2,
                                  String format) throws Exception {

        Map<String, Object> data1 = Parser.getData(filepath1);
        Map<String, Object> data2 = Parser.getData(filepath2);

        List<Map<String, Object>> result = differ(data1, data2);

        return Formatter.getDiff(format, result);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    private static List<Map<String, Object>> differ(Map<String, Object> data1,
                                                    Map<String, Object> data2) {

        List<Map<String, Object>> list = new ArrayList<>();

        Set<String> keysSet = new TreeSet<>(data1.keySet());
        keysSet.addAll(data2.keySet());

        for (String key : keysSet) {
            Map<String, Object> map = new LinkedHashMap<>();
            if (data1.containsKey(key) && !data2.containsKey(key)) {
                map.put("key", key);
                map.put("oldValue", data1.get(key));
                map.put("status", STATUS_REMOVED);
            } else if (!data1.containsKey(key) && data2.containsKey(key)) {
                map.put("key", key);
                map.put("newValue", data2.get(key));
                map.put("status", STATUS_ADDED);
            } else if (!Objects.equals(data1.get(key), data2.get(key))) {
                map.put("key", key);
                map.put("oldValue", data1.get(key));
                map.put("newValue", data2.get(key));
                map.put("status", STATUS_UPDATED);
            } else {
                map.put("key", key);
                map.put("oldValue", data1.get(key));
                map.put("status", STATUS_UNCHANGED);
            }
            list.add(map);
        }

        return list;
    }
}
