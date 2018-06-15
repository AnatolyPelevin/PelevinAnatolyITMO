package objectmodel;

public class FieldUtilised {
    private Integer fieldUtilisedId;
    private String type;
    private String infieldName;
    private String outfieldValue;
    private String outfieldName;
    private String uniqueid;
    private Integer determineOut;
    private RwField inRwField;
    private RwField outRwField;
    private RwTransform rwTransform;


    public Integer getFieldUtilisedId() {
        return fieldUtilisedId;
    }

    public void setFieldUtilisedId(Integer fieldUtilisedId) {
        this.fieldUtilisedId = fieldUtilisedId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getInfieldName() {
        return infieldName;
    }

    public void setInfieldName(String infieldName) {
        this.infieldName = infieldName;
    }

    public String getOutfieldValue() {
        return outfieldValue;
    }

    public void setOutfieldValue(String outfieldValue) {
        this.outfieldValue = outfieldValue;
    }


    public String getOutfieldName() {
        return outfieldName;
    }

    public void setOutfieldName(String outfieldName) {
        this.outfieldName = outfieldName;
    }


    public Integer getDetermineOut() {
        return determineOut;
    }

    public void setDetermineOut(Integer determineOut) {
        this.determineOut = determineOut;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public RwField getInRwField() {
        return inRwField;
    }

    public void setInRwField(RwField inRwField) {
        this.inRwField = inRwField;
    }

     public RwField getOutRwField() {
        return outRwField;
    }

    public void setOutRwField(RwField outRwField) {
        this.outRwField = outRwField;
    }


    public RwTransform getRwTransform() {
        return rwTransform;
    }

    public void setRwTransform(RwTransform rwTransform) {
        this.rwTransform = rwTransform;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("FieldUtilised [");

        sb.append(rwTransform).append(", ");
        sb.append("infield = ").append(inRwField).append(", ");
        sb.append("outfield = ").append(outRwField).append("]");

        return sb.toString();
    }
}
