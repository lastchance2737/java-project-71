package hexlet.code.formatter;

import java.util.List;
import java.util.Map;

public class Stylish {
    private static final String TEXT_KEY = "key";
    private static final String TEXT_OLD_VALUE = "oldValue";
    private static final String TEXT_NEW_VALUE = "newValue";

    protected static String getDiff(List<Map<String, Object>> differ) {
        StringBuilder builder = new StringBuilder().append("{\n");

        for (Map<String, Object> diff : differ) {
            switch (diff.get("status").toString()) {
                case "removed" -> builder.append("  - ")
                        .append(diff.get(TEXT_KEY))
                        .append(": ")
                        .append(diff.get(TEXT_OLD_VALUE))
                        .append("\n");
                case "added" -> builder.append("  + ")
                        .append(diff.get(TEXT_KEY))
                        .append(": ")
                        .append(diff.get(TEXT_NEW_VALUE))
                        .append("\n");
                case "unchanged" -> builder.append("    ")
                        .append(diff.get(TEXT_KEY))
                        .append(": ")
                        .append(diff.get(TEXT_OLD_VALUE))
                        .append("\n");
                default -> {
                    builder.append("  - ")
                            .append(diff.get(TEXT_KEY))
                            .append(": ")
                            .append(diff.get(TEXT_OLD_VALUE))
                            .append("\n");

                    builder.append("  + ")
                            .append(diff.get(TEXT_KEY))
                            .append(": ")
                            .append(diff.get(TEXT_NEW_VALUE))
                            .append("\n");
                }

            }
        }
        return builder.append("}").toString();
    }
}
