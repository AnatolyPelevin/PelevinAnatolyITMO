package dictionaries;

import java.util.HashMap;
import java.util.Map;

public enum ViewType {
    TABLE("Table"),
    STORED_PROCEDURE("SPOut");

    private String name;
    private static Map<String, ViewType> tagsMap;

    private ViewType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ViewType getByName(String name) {
        if (tagsMap == null) {
            initTagsMap();
        }

        return tagsMap.get(name);
    }

    private static void initTagsMap() {
        tagsMap = new HashMap<String, ViewType>();

        for (ViewType type : values()) {
            tagsMap.put(type.getName(), type);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
