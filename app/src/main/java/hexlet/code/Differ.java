package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;
//import java.util.*;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        // 'generate' - поиск различий между двумя файлами
        Map<String, Object> data1 = getData(filepath1);
        Map<String, Object> data2 = getData(filepath2);
        List<Map<String, Object>> list = getDiff(data1, data2);
        return listToString(list);
    }


    public static String generate(String filepath1, String filepath2) throws Exception {
        // Перезагрузка метода 'generate', если не задан 'format'
        return generate(filepath1, filepath2, "stylish");
    }

    private static Map<String, Object> getData(String filepath) throws Exception {
        // Получение данных из файла
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        String data = Files.readString(path);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(data, new TypeReference<Map<String, Object>>() {
        });
    }

    private static List<Map<String, Object>> getDiff(Map<String, Object> data1, Map<String, Object> data2) {
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
        return list;
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
