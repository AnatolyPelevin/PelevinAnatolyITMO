package showWork;

import dictionaries.DataType;
import dictionaries.RecordTerminator;
import dictionaries.RecordType;
import job.TransformJob;
import job.TransformJobBuilder;
import job.transform.types.RwRecord;
import objectmodel.RwField;
import objectmodel.RwFile;
import reader.FileReaderInterface;
import reader.RecordReaderException;
import reader.readerfactory.FileReaderFactoryImpl;
import reader.recordparser.RawRecordParserFactoryImpl;
import reader.recordparser.RawRecordParserInterface;
import vendors.bloomberg.BBGFileAttributeParser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;

public class ShowWork {
    public static void showWork() throws RecordReaderException, IOException {
        //Prepare metafile
        RwFile metaFile  = new RwFile();
        metaFile.setFilename("File");
        metaFile.setRecordTypeString(RecordType.DELIMITED.getName());
        metaFile.setAttributeParserClass(BBGFileAttributeParser.class.getName());

        //Prepare Record for metafile
        RwRecord rwRecord = new RwRecord();
        rwRecord.setDelimiter("|");
        rwRecord.setRecordDescription("Record for BBG FI");
        rwRecord.setRecordName("BBG FI out file Euro");
        rwRecord.setRecordTerminator(RecordTerminator.DELIMITER);
        rwRecord.setUniqueid("12345");
        rwRecord.setRwRecordId(1);
        rwRecord.setRecordTypeString("Delimit");

        //Prepare Fields for record
        Set<RwField> rwFields = new HashSet<>();

        RwField rwFieldTICKER = new RwField();
        rwFieldTICKER.setDataType(DataType.STRING);
        rwFieldTICKER.setFieldName("TICKER");
        rwFieldTICKER.setPosition(0);
        rwFields.add(rwFieldTICKER);

        //Add fields to the record
        rwRecord.setRwFields(rwFields);

        //Add Record to metafile
        metaFile.setRwRecord(rwRecord);

        //Connect record with file
        rwRecord.setRwFile(metaFile);

        Path wapPath = prepareFile();

        File file = wapPath.toFile();
        FileReaderFactoryImpl fileReaderFactory = new FileReaderFactoryImpl(metaFile);
        FileReaderInterface fileReaderInterface =  fileReaderFactory.createFileParser(file);

        RawRecordParserFactoryImpl rawRecordParserFactoryImpl = new RawRecordParserFactoryImpl();
        RawRecordParserInterface rawRecordParserInterface  = rawRecordParserFactoryImpl.createRecordParser(metaFile);

        TransformJobBuilder builder = new TransformJobBuilder();
        TransformJob transformJob = builder
                .setFileReaderInterface(fileReaderInterface)
                .setRawRecordParserInterface(rawRecordParserInterface)
             //   .setFileWriterInterface()
                .build();

        transformJob.runTransformation();
    }
    private static Path prepareFile() throws IOException {
        InputStream in = ShowWork.class.getClassLoader().getResourceAsStream("HelpFile/fixedincome_bo_euro.out");
        File tmp = File.createTempFile("fixedincome_bo_euro", "out");
        tmp.deleteOnExit();
        Path wapPath = tmp.toPath();
        Files.copy(in, wapPath, StandardCopyOption.REPLACE_EXISTING);
        return wapPath;
    }



}
