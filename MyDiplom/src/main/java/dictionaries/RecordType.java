package dictionaries;

import java.util.HashMap;
import java.util.Map;

public enum RecordType {
    DELIMITED("Delimit"),
    FIXED("Fixed"),
    XML("XML"),
    XLSX("XLSX"),
    XLS("XLS");

    private String name;
    private static Map<String, RecordType> tagsMap;

    private RecordType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static RecordType getByName(String name) {
        if (tagsMap == null) {
            initTagsMap();
        }

        return tagsMap.get(name);
    }

    private static void initTagsMap() {
        tagsMap = new HashMap<String, RecordType>();

        for (RecordType type : values()) {
            tagsMap.put(type.getName(), type);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
