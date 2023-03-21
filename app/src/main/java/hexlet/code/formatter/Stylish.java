package hexlet.code.formatter;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Map;

public class Stylish {
    protected static String getDiff(Map<String, Object> data1, Map<String, Object> data2) {
        // Алгоритм поиска различий между двумя файлами
        Set<String> keysSet = new TreeSet<>(data1.keySet());
        keysSet.addAll(data2.keySet());

        List<Map<String, Object>> list = new ArrayList<>();
        for (String key : keysSet) {
            Map<String, Object> map = new LinkedHashMap<>();
            if (data1.containsKey(key) && !data2.containsKey(key)) {
                map.put("- " + key, data1.get(key));
            } else if (!data1.containsKey(key) && data2.containsKey(key)) {
                map.put("+ " + key, data2.get(key));
            } else if (!Objects.equals(data1.get(key), data2.get(key))) {
                map.put("- " + key, data1.get(key));
                map.put("+ " + key, data2.get(key));
            } else {
                map.put("  " + key, data1.get(key));
            }
            list.add(map);
        }
        return listToString(list);
    }

    private static String listToString(List<Map<String, Object>> list) {
        // Перевод List в String определенного формата
        if (list.size() == 0) {
            return "{}";
        }

        StringBuilder builder = new StringBuilder().append("{\n");
        for (Map<String, Object> map : list) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                builder.append("  ").append(entry.getKey()).append(": ");
                builder.append(entry.getValue()).append("\n");
            }
        }
        builder.append("}");
        return builder.toString();
    }
}
