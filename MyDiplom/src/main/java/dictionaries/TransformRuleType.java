package dictionaries;

import java.util.HashMap;
import java.util.Map;

public enum TransformRuleType {
    INSERT("I"),
    UPDATE("U"),
    DELETE("D"),
    INSERT_UPDATE("A"),
    UPDATE_INSERT("M");

    private String name;
    private static Map<String, TransformRuleType> tagsMap;

    private TransformRuleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static TransformRuleType getByName(String name) {
        if (tagsMap == null) {
            initTagsMap();
        }

        return tagsMap.get(name);
    }

    private static void initTagsMap() {
        tagsMap = new HashMap<String, TransformRuleType>();

        for (TransformRuleType type : values()) {
            tagsMap.put(type.getName(), type);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
