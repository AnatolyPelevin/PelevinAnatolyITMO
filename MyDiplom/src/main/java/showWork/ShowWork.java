package showWork;

import dictionaries.DataType;
import dictionaries.RecordTerminator;
import dictionaries.RecordType;
import dictionaries.ViewType;
import job.TransformJob;
import job.TransformJobBuilder;
import job.transform.types.RwRecord;
import objectmodel.*;
import reader.FileReaderInterface;
import reader.RecordReaderException;
import reader.readerfactory.FileReaderFactoryImpl;
import reader.recordfilter.KeywordRecordFilter;
import reader.recordparser.RawRecordParserFactoryImpl;
import reader.recordparser.RawRecordParserInterface;
import vendors.bloomberg.BBGFileAttributeParser;
import writer.AbstractFileWriter;
import writer.statementbuild.DatabaseException;
import writer.statementbuild.StatementBuilder;
import writer.statementbuild.StatementBuilderImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;

public class ShowWork {
    private static RwFile metaFile = new RwFile();
    private static Set<RecordFilterParameter> recordFilterParameterSet = new HashSet<>();
    private static RwRecord rwRecord = new RwRecord();
    private static Set<RwField> rwFields = new HashSet<>();
    private static RwView rwView = new RwView();
    private static RwDatabase rwDatabase = new RwDatabase();

    public static void showWork() throws RecordReaderException, IOException, DatabaseException {
        //Prepare metafile
         prepareMetaFile();

         //prepare recordfilter for metafile
         prepareRecordFilterForMetaFile();

        //add frecorfdfilter to metafile
        metaFile.setRecordFilterParameters(recordFilterParameterSet);

        //Prepare Record for metafile
        prepareRwRecordForMetaFile();

        //Prepare Fields for record
        prepareRwFieldsForRwRecord();

        //Add fields to the record
        rwRecord.setRwFields(rwFields);

        //Add Record to metafile
        metaFile.setRwRecord(rwRecord);

        //Connect record with file
        rwRecord.setRwFile(metaFile);

        //prepare database for view
        prepareRwDatabaseForRwView();

        //prepare view
        prepareRwViewForRwecord();

        //connect view with record
        rwRecord.setRwView(rwView);

        Path wapPath = prepareFile();
        File file = wapPath.toFile();



        //File reader
        FileReaderFactoryImpl fileReaderFactory = new FileReaderFactoryImpl(metaFile);
        FileReaderInterface fileReaderInterface =  fileReaderFactory.createFileParser(file);

        //Record parser
        RawRecordParserFactoryImpl rawRecordParserFactoryImpl = new RawRecordParserFactoryImpl();
        RawRecordParserInterface rawRecordParserInterface  = rawRecordParserFactoryImpl.createRecordParser(metaFile);

        //statment builder
        StatementBuilder statementBuilder = new StatementBuilderImpl();
        statementBuilder.buildInsertUpdateStatement(rwRecord);

        //Record writer
        AbstractFileWriter abstractFileWriter = new AbstractFileWriter(statementBuilder);

        TransformJobBuilder builder = new TransformJobBuilder();
        TransformJob transformJob = builder
                .setFileReaderInterface(fileReaderInterface)
                .setRawRecordParserInterface(rawRecordParserInterface)
                .setFileWriterInterface(abstractFileWriter)
                .build();

        transformJob.runTransformation();
    }
    private static Path prepareFile() throws IOException {
        InputStream in = ShowWork.class.getClassLoader().getResourceAsStream("HelpFile/fixedincome_bo_euro_test.out");
        File tmp = File.createTempFile("fixedincome_bo_euro_test", "out");
        tmp.deleteOnExit();
        Path wapPath = tmp.toPath();
        Files.copy(in, wapPath, StandardCopyOption.REPLACE_EXISTING);
        return wapPath;
    }


    private static RwFile prepareMetaFile (){
        //Prepare metafile
        metaFile.setFilename("File");
        metaFile.setRwFileId(1);
        metaFile.setRecordTypeString(RecordType.DELIMITED.getName());
        metaFile.setAttributeParserClass(BBGFileAttributeParser.class.getName());
        metaFile.setRecordFilterClass(KeywordRecordFilter.class.getName());
        return metaFile;
    }


    private static void prepareRecordFilterForMetaFile (){
        //prepare recordfilter for metafile
        RecordFilterParameter recordFilterParameter  = new RecordFilterParameter();
        recordFilterParameter.setName("START_KEY_WORD");
        recordFilterParameter.setValue("START-OF-DATA");
        recordFilterParameterSet.add(recordFilterParameter);

        RecordFilterParameter recordFilterParameter1  = new RecordFilterParameter();
        recordFilterParameter1.setName("SKIP_PREFIX");
        recordFilterParameter1.setValue("#");
        recordFilterParameterSet.add(recordFilterParameter1);

        RecordFilterParameter recordFilterParameter2  = new RecordFilterParameter();
        recordFilterParameter2.setName("END_KEY_WORD");
        recordFilterParameter2.setValue("END-OF-DATA");
        recordFilterParameterSet.add(recordFilterParameter2);
    }


    private static void prepareRwRecordForMetaFile (){
        //Prepare Record for metafile
        rwRecord.setDelimiter("|");
        rwRecord.setRecordDescription("Record for BBG FI");
        rwRecord.setRecordName("BBG FI out file Euro");
        rwRecord.setRecordTerminator(RecordTerminator.DELIMITER);
        rwRecord.setUniqueid("12345");
        rwRecord.setRwRecordId(1);
        rwRecord.setRecordTypeString("Delimit");
    }

    private static void prepareRwFieldsForRwRecord() {
        //Prepare Fields for record
        RwField rwFieldTICKER = new RwField();
        rwFieldTICKER.setDataType(DataType.STRING);
        rwFieldTICKER.setFieldName("TICKER");
        rwFieldTICKER.setPosition(3);
        rwFieldTICKER.setKeyField(1);
        rwFields.add(rwFieldTICKER);


        RwField rwFieldCPN = new RwField();
        rwFieldCPN.setDataType(DataType.DECIMAL);
        rwFieldCPN.setFieldName("CPN");
       // rwFieldCPN.setDecimalPosition(7);
        rwFieldCPN.setPosition(4);
        rwFields.add(rwFieldCPN);

        RwField rwFieldMATURITY = new RwField();
        rwFieldMATURITY.setDataType(DataType.DATE);
        rwFieldMATURITY.setFieldName("MATURITY");
        rwFieldMATURITY.setPosition(5);
        rwFields.add(rwFieldMATURITY);

        RwField rwFieldSERIES = new RwField();
        rwFieldSERIES.setDataType(DataType.STRING);
        rwFieldSERIES.setFieldName("SERIES");
        rwFieldSERIES.setPosition(6);
        rwFields.add(rwFieldSERIES);

        RwField rwFieldNAME = new RwField();
        rwFieldNAME.setDataType(DataType.STRING);
        rwFieldNAME.setFieldName("NAME");
        rwFieldNAME.setPosition(7);
        rwFields.add(rwFieldNAME);

        RwField rwFieldSHORT_NAME = new RwField();
        rwFieldSHORT_NAME.setDataType(DataType.STRING);
        rwFieldSHORT_NAME.setFieldName("SHORT_NAME");
        rwFieldSHORT_NAME.setPosition(8);
        rwFields.add(rwFieldSHORT_NAME);

        RwField rwFieldISSUER_INDUSTRY = new RwField();
        rwFieldISSUER_INDUSTRY.setDataType(DataType.STRING);
        rwFieldISSUER_INDUSTRY.setFieldName("ISSUER_INDUSTRY");
        rwFieldISSUER_INDUSTRY.setPosition(9);
        rwFields.add(rwFieldISSUER_INDUSTRY);

        RwField rwFieldMARKET_SECTOR_DES = new RwField();
        rwFieldMARKET_SECTOR_DES.setDataType(DataType.STRING);
        rwFieldMARKET_SECTOR_DES.setFieldName("MARKET_SECTOR_DES");
        rwFieldMARKET_SECTOR_DES.setPosition(10);
        rwFields.add(rwFieldMARKET_SECTOR_DES);

        RwField rwFieldCPN_FREQ = new RwField();
        rwFieldCPN_FREQ.setDataType(DataType.INTEGER);
        rwFieldCPN_FREQ.setFieldName("CPN_FREQ");
        rwFieldCPN_FREQ.setPosition(11);
        rwFields.add(rwFieldCPN_FREQ);

        RwField rwFieldCPN_TYP = new RwField();
        rwFieldCPN_TYP.setDataType(DataType.STRING);
        rwFieldCPN_TYP.setFieldName("CPN_TYP");
        rwFieldCPN_TYP.setPosition(12);
        rwFields.add(rwFieldCPN_TYP);
    }


    private static void prepareRwDatabaseForRwView(){
        rwDatabase.setDatabaseDescription("test");
        rwDatabase.setRwDatabaseId(1);
    }

    private static void prepareRwViewForRwecord(){
        rwView.setRwViewId(1);
        rwView.setUniqueid("11111111");
        rwView.setViewName("BOND_DESCRIPTIVE");
        rwView.setViewType(ViewType.TABLE);
        rwView.setRwDatabase(rwDatabase);
        Set<RwRecord> records = new HashSet<>();
        records.add(rwRecord);
        rwView.setRwRecords(records);
    }
}
