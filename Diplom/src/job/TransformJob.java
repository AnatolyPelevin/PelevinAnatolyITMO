package job;

import reader.FileReaderInterface;
import transformer.RecordTransformerInterface;
import writer.FileWriterInterface;
import static utils.Arguments.checkNotNull;

public class TransformJob {
    private FileReaderInterface fileReaderInterface;
    private FileWriterInterface fileWriterInterface;
    private RecordTransformerInterface recordTransformerInterface;

    //private static final Log LOG = LogFactory.getLog(TransformJob.class);



    TransformJob(TransformJobBuilder builder) {
        this.fileReaderInterface = checkNotNull(builder.getFileReaderInterface(), "fileReaderInterface");
        this.fileWriterInterface = checkNotNull(builder.getFileWriterIntreface(), "fileWriterInterface");
       // this.recordTransformerInterface = checkNotNull(builder.getFileWriterIntreface(), "recordTransformerInterface");
    }
}
