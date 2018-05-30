package objectmodel;

import dictionaries.RecordType;

public class RwFile {
    private String filename;
    private String recordTypeString;
    private String attributeParserClass;


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public RecordType getRecordType() {
        return (recordTypeString == null) ? null : RecordType.getByName(recordTypeString);
    }

    public String getRecordTypeString() {
        return recordTypeString;
    }

    public void setRecordTypeString(String recordTypeString) {
        this.recordTypeString = recordTypeString;
    }

     public String getAttributeParserClass() {
        return attributeParserClass;
    }

    public void setAttributeParserClass(String attributeParserClass) {
        this.attributeParserClass = attributeParserClass;
    }
}
