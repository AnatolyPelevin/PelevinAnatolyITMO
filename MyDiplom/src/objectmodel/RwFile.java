package objectmodel;

import dictionaries.RecordType;
import job.transform.types.RwRecord;
import utils.CollectionUtils;
import utils.NullArgumentException;

import java.util.HashSet;
import java.util.Set;

public class RwFile {
    private String filename;
    private String recordTypeString;
    private String attributeParserClass;
    private Set<RwRecord> rwRecords = new HashSet<>();
    private String recordFilterClass;
    private Set<RecordFilterParameter> recordFilterParameters  = new HashSet<>();


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

    public Set<RwRecord> getRwRecords() {
        return rwRecords;
    }

    public void setRwRecords(Set<RwRecord> rwRecords) {
        this.rwRecords = rwRecords;
    }


    public void setRwRecord(RwRecord rwRecords) {
        this.rwRecords.add(rwRecords);
    }


    public String getRecordFilterClass() {
        return recordFilterClass;
    }

    public void setRecordFilterClass(String recordFilterClass) {
        this.recordFilterClass = recordFilterClass;
    }

    public Set<RecordFilterParameter> getRecordFilterParameters() {
        return recordFilterParameters;
    }

    public void setRecordFilterParameters(Set<RecordFilterParameter> recordFilterParameters) {
        this.recordFilterParameters = recordFilterParameters;
    }

    public RwRecord findRecordByName(String name) {
        if (name == null) {
            throw new NullArgumentException("name");
        }

        for (RwRecord record : CollectionUtils.safeCollection(getRwRecords())) {
            if (name.equals(record.getRecordName())) {
                return record;
            }
        }

        return null;
    }

    public RwRecord findRecordByUniqueId(String uniqueId) {
        if (uniqueId == null) {
            throw new NullArgumentException("uniqueId");
        }

        for (RwRecord record : CollectionUtils.safeCollection(getRwRecords())) {
            if (uniqueId.equals(record.getUniqueid())) {
                return record;
            }
        }

        return null;
    }


}
