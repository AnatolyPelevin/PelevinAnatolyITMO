package job.transform.types;

public interface ETLRecordFactory {
    ETLRecord createETLRecord(RwRecord metadata);
}
