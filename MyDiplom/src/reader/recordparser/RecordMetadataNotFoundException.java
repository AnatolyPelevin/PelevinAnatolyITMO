package reader.recordparser;

public class RecordMetadataNotFoundException extends RecordParserException {
    public RecordMetadataNotFoundException(String record) {
        super(record);
    }
}
