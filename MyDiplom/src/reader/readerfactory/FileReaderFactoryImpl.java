package reader.readerfactory;

import dictionaries.RecordType;
import objectmodel.RwFile;
import reader.FileReaderInterface;
import reader.FlatFileReader;
import reader.RecordReaderException;
import reader.fileattribute.*;
import utils.StringUtils;

import java.io.File;

public class FileReaderFactoryImpl implements FileReaderFactory {
    private RwFile metaFile;

    public FileReaderFactoryImpl(RwFile metaFile) {
        if (metaFile == null) {
            throw new IllegalArgumentException("Metafile is null.");
        }

        RecordType recordType = metaFile.getRecordType();

        if (recordType == null) {
            throw new IllegalArgumentException("File has no record type defined: "
                    + metaFile.getFilename());
        }

        this.metaFile = metaFile;
    }


    @Override
    public FileReaderInterface createFileParser(File file) throws RecordReaderException {
        if (file == null) {
            throw new IllegalArgumentException("File is null");
        }

        FileReaderInterface fileReader;
        RecordType recordType = metaFile.getRecordType();

        switch (recordType) {
            case DELIMITED:
            case FIXED:
                fileReader = new FlatFileReader(file);
                break;
            default:
                throw new IllegalArgumentException("File reader is not supported for record type: " + recordType);
        }

        FileAttributeParser attributeParser;

        if (!StringUtils.isEmpty(metaFile.getAttributeParserClass())) {
            try {
                attributeParser = createFileAttributeParser(metaFile.getAttributeParserClass());
            }
            catch (Exception e) {
                throw new RecordReaderException("Error while creating file attribute parser for file: "
                        + metaFile.getFilename(), e, file.getName());
            }
        }
        else {
            attributeParser = new DummyFileAttributeParser();
        }

        AttributeAwareFileParser attributeAwareFileParser = new AttributeAwareFileParserImpl(
                fileReader,
                attributeParser);


        return new AttributeAwareFileParserWrapper(attributeAwareFileParser, fileReader);
    }

    private FileAttributeParser createFileAttributeParser(String className) throws Exception {
        Class<?> cls = Class.forName(className);

        return (FileAttributeParser) cls.newInstance();
    }
}

