package reader.recordparser;

import dictionaries.RecordType;
import job.transform.types.DefaultETLRecordFactory;
import job.transform.types.ETLRecordFactory;
import job.transform.types.RwRecord;
import objectmodel.RwFile;
import reader.DefaultDelimitedRecordSplitter;
import utils.CollectionUtils;

import java.util.Set;

public class RawRecordParserFactoryImpl implements RawRecordParserFactory {

    public RawRecordParserInterface createRecordParser(RwFile file) throws RecordParserException {
        return createRecordParser(file.getRecordType(), file.getRwRecords());
    }

    private RawRecordParserInterface createRecordParser(RecordType fileRecordType, Set<RwRecord> records) throws RecordParserException {

        if (CollectionUtils.isEmpty(records)) {
            throw new IllegalArgumentException("Records collection is empty.");
        }

        RwRecord firstRecord = records.iterator().next();
        RawRecordParserInterface recordParser;

        RecordType recordType = (firstRecord.getRecordType() != null)
                ? firstRecord.getRecordType()
                : fileRecordType;

        ETLRecordFactory etlRecordFactory = new DefaultETLRecordFactory();

        switch (recordType) {
            case DELIMITED:
              return new DelimitedRecordParser(records, getDelimiter(firstRecord), new DefaultDelimitedRecordSplitter(), etlRecordFactory);

//            case FIXED:
////TODO
//
//            case XLSX:
//            case XLS:
////TODO
//
//            case XML:
////TODO

            default:
                throw new IllegalArgumentException("Raw record parser not supported for record type: " + recordType);
        }

    }

    private String getDelimiter(RwRecord record) {
        String delimiter = record.getDelimiter();

        if ("TAB".equals(delimiter)) {
            delimiter = "\t";
        }

        return delimiter;
    }
}
