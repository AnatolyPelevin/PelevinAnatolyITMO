package job.transform.types;

import dictionaries.RecordTerminator;
import dictionaries.RecordType;
import objectmodel.RwField;
import objectmodel.RwFile;
import utils.CollectionUtils;
import utils.NullArgumentException;

import java.util.Set;

public class RwRecord {
    private Integer rwRecordId;
    private RecordTerminator recordTerminator;
    private String recordName;
    private String recordDescription;
    private String delimiter;
    private String uniqueid;
    private String recordTypeString;
    private Set<RwField> rwFields;
//    private RwView rwView;
     private RwFile rwFile;



    public Integer getRwRecordId() {
        return rwRecordId;
    }

    public void setRwRecordId(Integer rwRecordId) {
        this.rwRecordId = rwRecordId;
    }


    public RecordTerminator getRecordTerminator() {
        return recordTerminator;
    }

    public void setRecordTerminator(RecordTerminator recordTerminator) {
        this.recordTerminator = recordTerminator;
    }


    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }


    public String getRecordDescription() {
        return recordDescription;
    }

    public void setRecordDescription(String recordDescription) {
        this.recordDescription = recordDescription;
    }


    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }


    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
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

    public Set<RwField> getRwFields() {
        return rwFields;
    }

    public void setRwFields(Set<RwField> rwFields) {
        this.rwFields = rwFields;
    }


//    public RwView getRwView() {
//        return rwView;
//    }
//
//    public void setRwView(RwView rwView) {
//        this.rwView = rwView;
//    }
//
//
    public RwFile getRwFile() {
        return rwFile;
    }

    public void setRwFile(RwFile rwFile) {
        this.rwFile = rwFile;
    }



    public RwField findFieldByName(String name) {
        if (name == null) {
            throw new NullArgumentException("name");
        }

        for (RwField field : CollectionUtils.safeCollection(getRwFields())) {
            if (name.equals(field.getFieldName())) {
                return field;
            }
        }

        return null;
    }

    public RwField findFieldByUniqueId(String uniqueId) {
        if (uniqueId == null) {
            throw new NullArgumentException("uniqueId");
        }

        for (RwField field : CollectionUtils.safeCollection(getRwFields())) {
            if (uniqueId.equals(field.getFieldName())) {
                return field;
            }
        }

        return null;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Record [");

        if (rwFile != null) {
            sb.append(rwFile);
        }
//
//        if (rwView != null) {
//            sb.append(rwView);
//        }

        sb.append(", name = ").append(recordName).append("]");

        return sb.toString();
    }


    public RwRecord copy() {
        RwRecord copy = new RwRecord();

        copy.setRwRecordId(getRwRecordId());
        copy.setRecordName(getRecordName());
        copy.setDelimiter(getDelimiter());
        copy.setRecordDescription(getRecordDescription());
        copy.setRecordTerminator(getRecordTerminator());
        copy.setRecordTypeString(getRecordTypeString());
        copy.setUniqueid(getUniqueid());
        copy.setRwFile(getRwFile());
        copy.setRwFields(getRwFields());

        return copy;
    }
}

