package job;

import reader.FileReaderInterface;
import transformer.RecordTransformerInterface;
import writer.FileWriterInterface;

public class TransformJobBuilder {
    private FileReaderInterface fileReaderInterface;
    private FileWriterInterface fileWriterInterface;
    private RecordTransformerInterface recordTransformerInterface;

    public RecordTransformerInterface getRecordTransformerInterface() {
        return recordTransformerInterface;
    }

    public TransformJobBuilder setRecordTransformerInterface(RecordTransformerInterface recordTransformerInterface) {
        this.recordTransformerInterface = recordTransformerInterface;
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
