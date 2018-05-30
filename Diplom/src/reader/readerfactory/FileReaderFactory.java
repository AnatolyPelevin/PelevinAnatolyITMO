package reader.readerfactory;

import reader.FileReaderInterface;
import reader.RecordReaderException;

import java.io.File;

public interface FileReaderFactory {
    FileReaderInterface createFileParser(File file) throws RecordReaderException;
}
