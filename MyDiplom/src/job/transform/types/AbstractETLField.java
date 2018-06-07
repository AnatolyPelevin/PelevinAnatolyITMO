package job.transform.types;


import dictionaries.DataType;
import objectmodel.RwField;
import utils.StringUtils;

public abstract class AbstractETLField<T> implements ETLField<T> {
    private RwField field;
    private T value;

    private DataType originalType;

    protected AbstractETLField(RwField field) {
        if (field == null) {
            throw new IllegalArgumentException("Field is null.");
        }

        this.field = field;

        String fieldValue = field.getFieldValue();
        
        if (!StringUtils.isEmpty(fieldValue)) {
            try {
                setStringValue(fieldValue);
            }
            catch (FieldConversionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setStringValue(String value) throws FieldConversionException {
        setStringValue(value, null);
    }

    public String getStringValue() {
        return (value == null) ? null : value.toString();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void clearValue() {
        setValue(null);
    }

    public RwField getMetadata() {
        return field;
    }

    public void convertFrom(ETLField<?> type) throws FieldConversionException {
        throwIncompatibleTypeConversionException(type, this);
    }


    private void throwIncompatibleTypeConversionException(ETLField<?> srcField, ETLField<?> destField)
            throws FieldConversionException {
        RwField srcMeta = srcField.getMetadata();
        RwField destMeta = destField.getMetadata();

        throw new IncompatibleTypeConversionException(srcMeta.getFieldName() + "("
                + srcMeta.getDataType() + ") -> " + destMeta.getFieldName() + "("
                + destMeta.getDataType() + ")");
    }

    protected String getMetaInfo() {
        return field.getFieldName() + "(" + field.getDataType() + ")";
    }

    @Override
    public DataType getOriginalType() {
        return originalType;
    }

    @Override
    public void setOriginalType(DataType dataType) {
        this.originalType = dataType;
    }
}
