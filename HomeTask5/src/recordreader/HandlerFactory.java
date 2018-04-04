package recordreader;

import dictionaries.RecordType;

public class HandlerFactory {

    public static HandlerInterface createHandler (RecordType recordType){
        switch(recordType){
            case XML:
                return new XmlHandler();
            case JSON:
                return new XmlHandler();
            default:
                throw new IllegalArgumentException("Record writer is not supported for record type: "
                        + recordType);
        }
    }
}
