package job.transform.types;


import objectmodel.RwField;
import utils.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DefaultETLRecordFactory implements ETLRecordFactory {
    private Comparator<RwField> fieldComparator = new RwFieldComparator();
    private ConcurrentMap<String, RwRecord> rwRecordCache = new ConcurrentHashMap<String, RwRecord>();

    public ETLRecord createETLRecord(RwRecord rwRecord) {
        RwRecord rwRecordCopy = getRwRecordCopy(rwRecord);
        return new LazyETLRecord(rwRecordCopy);
    }

    private String getRwRecordKey(RwRecord rwRecord) {
        String uniqueid = rwRecord.getUniqueid();
        return (uniqueid != null) ? uniqueid : Integer.toString(rwRecord.hashCode());
    }

    RwRecord getRwRecordCopy(RwRecord rwRecord) {
        Collection<RwField> fields = CollectionUtils.safeCollection(rwRecord.getRwFields());
        String key = getRwRecordKey(rwRecord);
        RwRecord rwRecordCopy = rwRecordCache.get(key);

        if (rwRecordCopy == null) {
            RwRecord rwRecordCopyNew = rwRecord.copy();

            if (rwRecord.getRwFile() != null) {
                List<RwField> sortedFields = new ArrayList<RwField>(fields);
                Collections.sort(sortedFields, fieldComparator);
                rwRecordCopyNew.setRwFields(new LinkedHashSet<RwField>(sortedFields));
            }
            else {
                rwRecordCopyNew.setRwFields(new LinkedHashSet<RwField>(fields));
            }

            rwRecordCopy = rwRecordCache.putIfAbsent(key, rwRecordCopyNew);
            if (rwRecordCopy == null) {
                rwRecordCopy = rwRecordCopyNew;
            }
        }

        return rwRecordCopy;
    }

    private static class RwFieldComparator implements Comparator<RwField> {
        public int compare(RwField field1, RwField field2) {
            int position1 = (field1.getPosition() == null) ? Integer.MAX_VALUE : field1.getPosition();
            int position2 = (field2.getPosition() == null) ? Integer.MAX_VALUE : field2.getPosition();

            return position1 - position2;
        }
    }
}
