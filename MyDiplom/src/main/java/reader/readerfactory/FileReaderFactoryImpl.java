package reader.readerfactory;

import dictionaries.RecordType;
import objectmodel.RecordFilterParameter;
import objectmodel.RwFile;
import reader.FileReaderInterface;
import reader.FilteredFileParser;
import reader.FlatFileReader;
import reader.RecordReaderException;
import reader.fileattribute.*;
import reader.recordfilter.ParametrizedRawRecordFilter;
import utils.CollectionUtils;
import utils.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileReaderFactoryImpl implements FileReaderFactory {
    private RwFile metaFile;
    private ParametrizedRawRecordFilter recordFilter;

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


        if (!StringUtils.isEmpty(metaFile.getRecordFilterClass())) {
            if (recordFilter == null) {
                try {
                    recordFilter = createRecordFilter(metaFile);
                }
                catch (Exception e) {
                    throw new RecordReaderException("Error while creating a record " +
                            "filter for file: " + metaFile.getFilename(), e, file.getName());
                }
            }
            else {
                recordFilter.reset();
            }

            fileReader = new FilteredFileParser(attributeAwareFileParser, recordFilter);
        }

        return new AttributeAwareFileParserWrapper(attributeAwareFileParser, fileReader);
    }

    private ParametrizedRawRecordFilter createRecordFilter(RwFile metaFile) throws Exception {
        Class<?> filterClass = Class.forName(metaFile.getRecordFilterClass());

        ParametrizedRawRecordFilter filter = (ParametrizedRawRecordFilter) filterClass.newInstance();
        Map<Enum<?>, String> paramsMap = new HashMap<Enum<?>, String>();

        for (RecordFilterParameter param : CollectionUtils.safeCollection(
                metaFile.getRecordFilterParameters())) {
            if (param.getValue() != null) {
                paramsMap.put(Enum.valueOf(filter.getEnumClass(), param.getName()), param.getValue());
            }
        }

        filter.initialize(paramsMap);

        return filter;
    }

    private FileAttributeParser createFileAttributeParser(String className) throws Exception {
        Class<?> cls = Class.forName(className);

        return (FileAttributeParser) cls.newInstance();
    }
}

