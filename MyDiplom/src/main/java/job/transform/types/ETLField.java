package job.transform.types;


import dictionaries.DataType;
import objectmodel.RwField;

public interface ETLField<T> {
    void setStringValue(String value) throws FieldConversionException;
    void setStringValue(String value, RwField valueMetadata) throws FieldConversionException;
    String getStringValue();
    void setValue(T value);
    T getValue();
    void clearValue();
    RwField getMetadata();
    void convertTo(ETLField<?> type) throws FieldConversionException;
    void convertFrom(ETLField<?> type) throws FieldConversionException;
    DataType getOriginalType();
    void setOriginalType(DataType dataType);
}
