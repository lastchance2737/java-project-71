package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    protected static Map<String, Object> getData(String filepath) throws Exception {
        // Получение данных из файла
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        String data = Files.readString(path);

        ObjectMapper mapper = getObjectMapper(filepath);
        return mapper.readValue(data, new TypeReference<Map<String, Object>>() {
        });
    }

    private static String getFileExtension(String filepath) {
        return filepath.substring(filepath.lastIndexOf('.') + 1).toLowerCase();
    }

    private static ObjectMapper getObjectMapper(String filepath) {
        String fileExtension = getFileExtension(filepath);

        if (fileExtension.equals("yml") || fileExtension.equals("yaml")) {
            return new YAMLMapper();
        }
        return new ObjectMapper();
    }
}
