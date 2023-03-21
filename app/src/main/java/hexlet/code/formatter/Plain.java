package hexlet.code.formatter;

import java.util.Map;
import java.util.List;

public class Plain {
    private static final String TEXT_KEY = "key";
    private static final String TEXT_OLD_VALUE = "oldValue";
    private static final String TEXT_NEW_VALUE = "newValue";

    protected static String getDiff(List<Map<String, Object>> differ) {
        StringBuilder builder = new StringBuilder();

        for (Map<String, Object> diff : differ) {
            switch (diff.get("status").toString()) {
                case "removed" -> builder.append("Property '")
                        .append(diff.get(TEXT_KEY))
                        .append("' was removed\n");
                case "added" -> builder.append("Property '")
                        .append(diff.get(TEXT_KEY))
                        .append("' was added with value: ")
                        .append(isComplexValue(diff.get(TEXT_NEW_VALUE)))
                        .append("\n");
                case "updated" -> builder.append("Property '")
                        .append(diff.get(TEXT_KEY))
                        .append("' was updated. From ")
                        .append(isComplexValue(diff.get(TEXT_OLD_VALUE)))
                        .append(" to ")
                        .append(isComplexValue(diff.get(TEXT_NEW_VALUE)))
                        .append("\n");
                default -> builder.append("");
            }
        }
        return builder.toString().trim();
    }

    private static String isComplexValue(Object value) {
        if (value == null) {
            return null;
        }

        String str = String.valueOf(value);
        if (str.contains("[") || str.contains("{")) {
            return "[complex value]";
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        return value.toString();
    }
}
