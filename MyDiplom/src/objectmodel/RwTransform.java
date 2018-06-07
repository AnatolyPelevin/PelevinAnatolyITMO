package objectmodel;

import dictionaries.TransformRuleType;
import utils.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RwTransform {
    private Integer rwTransformId;
    private String transformName;
    private TransformRuleType transformRuleType;
    private String uniqueid;
    private Integer batchSize;
    private Set<FieldUtilised> fieldUtiliseds;
    private RwProfile rwProfile;
    private Map<String, FieldUtilised> mappingsByInOutFieldsIdsMap;


    public Integer getRwTransformId() {
        return rwTransformId;
    }

    public void setRwTransformId(Integer rwTransformId) {
        this.rwTransformId = rwTransformId;
    }


    public String getTransformName() {
        return transformName;
    }

    public void setTransformName(String transformName) {
        this.transformName = transformName;
    }

    public TransformRuleType getTransformRuleType() {
        return transformRuleType;
    }

    public void setTransformRuleType(TransformRuleType transformRuleType) {
        this.transformRuleType = transformRuleType;
    }


    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public Integer getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }


    public Set<FieldUtilised> getFieldUtiliseds() {
        return fieldUtiliseds;
    }

    public void setFieldUtiliseds(Set<FieldUtilised> fieldUtiliseds) {
        this.fieldUtiliseds = fieldUtiliseds;
    }

     public RwProfile getRwProfile() {
        return rwProfile;
    }

    public void setRwProfile(RwProfile rwProfile) {
        this.rwProfile = rwProfile;
    }



    public FieldUtilised findFieldMapping(RwField inField, RwField outField) {
        if (mappingsByInOutFieldsIdsMap == null) {
            mappingsByInOutFieldsIdsMap = new HashMap<>();

            for (FieldUtilised fu : CollectionUtils.safeCollection(getFieldUtiliseds())) {
                String key = getMapKey(fu.getInRwField(), fu.getOutRwField());
                mappingsByInOutFieldsIdsMap.put(key, fu);
            }
        }

        String key = getMapKey(inField, outField);

        return mappingsByInOutFieldsIdsMap.get(key);
    }

    private String getMapKey(RwField inField, RwField outField) {
        StringBuilder sb = new StringBuilder();

        sb.append((inField != null) ? inField.getRwFieldId() : "null");
        sb.append(".");
        sb.append((outField != null) ? outField.getRwFieldId() : "null");

        return sb.toString();
    }

    @Override
    public String toString() {
        return "Transformation [name = " + transformName + "]";
    }
}
