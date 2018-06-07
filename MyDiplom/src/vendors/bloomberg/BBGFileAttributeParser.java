package vendors.bloomberg;

import reader.fileattribute.FileAttribute;
import reader.fileattribute.FileAttributeParser;
import reader.RecordReaderException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BBGFileAttributeParser implements FileAttributeParser {
    private static final String DATE_FORMAT = "EEE MMM d HH:mm:ss z yyyy";
    private static final String TIME_STARTED_PREFIX = "TIMESTARTED=";

    public enum Attribute {
        TIME_STARTED("TIMESTARTED");

        private String name;
        private static Map<String, Attribute> valuesMap;

        Attribute(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static Attribute getByName(String name) {
            if (valuesMap == null) {
                valuesMap = new HashMap<String, Attribute>();

                for (Attribute attribute : values()) {
                    valuesMap.put(attribute.getName(), attribute);
                }
            }

            return valuesMap.get(name);
        }
    }

    public FileAttribute parseAttribute(String record) throws RecordReaderException {
        if (record == null) {
            throw new IllegalArgumentException("Record is null");
        }

        record = record.trim();

        FileAttribute attribute = null;

        if (record.startsWith(TIME_STARTED_PREFIX)) {
            record = record.substring(TIME_STARTED_PREFIX.length());

            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            Date date;

            try {
                date = dateFormat.parse(record);
            }
            catch (ParseException e) {
                throw new RecordReaderException(e);
            }

            attribute = new FileAttribute(Attribute.TIME_STARTED.getName(), date);
        }

        return attribute;
    }
}