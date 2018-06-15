package dictionaries;

import java.util.HashMap;
import java.util.Map;

public enum DataType {
    DECIMAL("Decimal"),
    BOOLEAN("Boolean"),
    DOUBLE("Double"),
    INTEGER("Integer"),
    STRING("String"),
    DATE("Date");

    private String name;
    private static Map<String, DataType> enumMap;

    private DataType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static DataType getByName(String name) {
        if (enumMap == null) {
            initTagsMap();
        }

        return enumMap.get(name != null ? name.toLowerCase() : null);
    }

    private static void initTagsMap() {
        enumMap = new HashMap<String, DataType>();

        for (DataType type : values()) {
            enumMap.put(type.getName().toLowerCase(), type);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
