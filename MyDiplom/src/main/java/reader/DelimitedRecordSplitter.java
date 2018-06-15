package reader;

public interface DelimitedRecordSplitter {
    String[] split(String record, String delimiter);
}
