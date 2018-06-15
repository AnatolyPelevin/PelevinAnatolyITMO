package writer;

import job.transform.types.RwRecord;
import objectmodel.RwField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class AbstractFileWriter implements FileWriterInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractFileWriter.class);

    @Override
    public int writeRecord(RwRecord rwRecord) throws RecordWriterException {
        Set<RwField> rwFields = rwRecord.getRwFields();
        return 0;
    }

    @Override
    public void close() throws RecordWriterException {

    }
}
