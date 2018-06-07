package reader.recordparser;

import dictionaries.DataType;
import job.transform.types.ETLRecord;
import job.transform.types.ETLRecordFactory;
import job.transform.types.RwRecord;
import objectmodel.RwField;
import reader.DelimitedRecordSplitter;
import utils.CollectionUtils;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static utils.Arguments.checkArgument;
import static utils.Arguments.checkNotNull;

public class DelimitedRecordParser implements RawRecordParserInterface {
    private DelimitedRecordSplitter splitter;
    private String delimiter;
    protected List<RwRecord> records;
    private String[] fields;
    private ETLRecordFactory etlRecordFactory;

    public DelimitedRecordParser(Collection<RwRecord> records, String delimiter, DelimitedRecordSplitter splitter, ETLRecordFactory etlRecordFactory) throws RecordParserException {
        this.splitter = checkNotNull(splitter, "splitter");
        this.etlRecordFactory = checkNotNull(etlRecordFactory, "etlRecordFactory");
        this.delimiter = checkArgument(delimiter, !StringUtils.isEmpty(delimiter), "Delimiter mustn't be empty, " +
                "please, check \"Delimiter\" field");

        if (CollectionUtils.isEmpty(records)) {
            throw new IllegalArgumentException("Records collection is empty");
        }

        this.records = new ArrayList<RwRecord>(records);
    }

    @Override
    public List<ETLRecord> parseRecord(String record) throws RecordParserException {
        fields = splitter.split(record, delimiter);

        //TODO make getRecordMetadata to find metaRecord if there will be few of them for one metafile
        RwRecord metaRecord =  records.get(0);

        if (metaRecord == null) {
            throw new RecordMetadataNotFoundException(record);
        }

        //TODO create presentation for record in ETL
        //ETLRecord etlRecord = etlRecordFactory.createETLRecord(metaRecord);

        try {
         //   populateETLRecord(record, etlRecord);
            populateETLRecord(record,  metaRecord);
        }
        catch (Exception e) {
            throw new RecordParserException(e, metaRecord, record);
        }

        return null;
    }


  //  protected void populateETLRecord(String record,  ETLRecord etlRecord)
  protected void populateETLRecord(String record,  RwRecord metaRecord)
            throws Exception {
        if (fields == null) {
            fields = splitter.split(record, delimiter);
        }

      RwRecord metaRecordCopy = metaRecord.copy();
      Set<RwField> rwFields = metaRecordCopy.getRwFields();

      for(RwField rwField: rwFields) {
          DataType dataType = rwField.getDataType();
          Integer position = rwField.getPosition();

          if (position >= fields.length) {
              break;
          }
          String value = fields[position];
          rwField.setFieldValue(value);
      }
    }
}
