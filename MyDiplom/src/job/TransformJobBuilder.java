package job;

import reader.FileReaderInterface;
import reader.recordparser.RawRecordParserInterface;
import writer.FileWriterInterface;

public class TransformJobBuilder {
    private FileReaderInterface fileReaderInterface;
    private FileWriterInterface fileWriterInterface;
    private RawRecordParserInterface rawRecordParserInterface;

    public RawRecordParserInterface getRawRecordParserInterface() {
        return rawRecordParserInterface;
    }

    public TransformJobBuilder setRawRecordParserInterface(RawRecordParserInterface rawRecordParserInterface) {
        this.rawRecordParserInterface = rawRecordParserInterface;
        return this;
    }


    public FileReaderInterface getFileReaderInterface() {
        return fileReaderInterface;
    }

    public TransformJobBuilder setFileReaderInterface(FileReaderInterface fileReaderInterface) {
        this.fileReaderInterface = fileReaderInterface;
        return this;
    }

    public FileWriterInterface getFileWriterIntreface() {
        return fileWriterInterface;
    }

    public TransformJobBuilder setFileWriterInterface(FileWriterInterface fileWriterInterface) {
        this.fileWriterInterface = fileWriterInterface;
        return this;
    }

    public TransformJob build() {
        return new TransformJob(this);
    }
}
