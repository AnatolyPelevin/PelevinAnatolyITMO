package job;

import job.transform.types.RwRecord;
import objectmodel.RwField;
import reader.FileReaderInterface;
import reader.recordparser.RawRecordParserInterface;
import writer.FileWriterInterface;

import java.util.Set;

import static utils.Arguments.checkNotNull;

public class TransformJob {
    private FileReaderInterface fileReaderInterface;
    private FileWriterInterface fileWriterInterface;
    private RawRecordParserInterface rawRecordParserInterface;


    //private static final Log LOG = LogFactory.getLog(TransformJob.class);


    TransformJob(TransformJobBuilder builder) {
        this.fileReaderInterface = checkNotNull(builder.getFileReaderInterface(), "fileReaderInterface");
        this.rawRecordParserInterface = checkNotNull(builder.getRawRecordParserInterface(), "rawRecordParserInterface");
        this.fileWriterInterface = checkNotNull(builder.getFileWriterIntreface(), "fileWriterInterface");

    }

    public void runTransformation(){
        String thisLine = null;

        try {
            while ((thisLine = fileReaderInterface.nextRecord()) != null) {
               // System.out.println(thisLine);
                RwRecord rwRecord =  rawRecordParserInterface.parseRecord(thisLine);
                Set<RwField> rwFields = rwRecord.getRwFields();
                //System.out.println(rwRecord.toString());
                rwFields.stream().forEach(r->System.out.println(r.getFieldValue()));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
