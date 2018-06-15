package dictionaries;

import job.transform.types.RwRecord;

import java.util.function.Function;

public enum RecordTerminator {
    CR("\r"),
    CRLF("\r\n"),
    LF("\n"),
    DELIMITER(new Function<RwRecord, String>() {
        @Override
        public String apply(RwRecord rwRecord) {
            return rwRecord.getDelimiter();
        }
    });

    private Function<RwRecord, String> rwRecordFunction; //


    private RecordTerminator(final String separator) {
        this.rwRecordFunction = new Function<RwRecord, String>() {
            @Override
            public String apply(RwRecord rwRecord) {
                return separator;
            }
        };
    }

    private RecordTerminator(Function<RwRecord, String> rwRecordFunction) {
        this.rwRecordFunction = rwRecordFunction;
    }


    public String separator(RwRecord rwRecord) {
        return rwRecordFunction.apply(rwRecord);
    }
}

