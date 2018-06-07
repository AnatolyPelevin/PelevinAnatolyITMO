package objectmodel;

import dictionaries.DataType;
import job.transform.types.RwRecord;

import java.util.Collection;


public class RwField  {
        private Integer rwFieldId;
        private String fieldName;
        private Integer keyField;
        private String description;
        private String fieldType = "Data";
        private Integer position;
        private String dateFormat;
        private String uniqueid;
        private Collection<FieldUtilised> fieldUtilisedsByInRwFieldId;
        private Collection<FieldUtilised> fieldUtilisedsByOutRwFieldId;
        private RwRecord rwRecord;
        private DataType dataType;
         private String fieldValue;


        public Integer getRwFieldId() {
            return rwFieldId;
        }

        public void setRwFieldId(Integer rwFieldId) {
            this.rwFieldId = rwFieldId;
        }


        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public Integer getKeyField() {
            return keyField;
        }

        public void setKeyField(Integer keyField) {
            this.keyField = keyField;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getFieldType() {
            return fieldType;
        }

        public void setFieldType(String fieldType) {
            this.fieldType = fieldType;
        }

        public Integer getPosition() {
            return position;
        }

        public void setPosition(Integer position) {
            this.position = position;
        }

        public String getDateFormat() {
            return dateFormat;
        }

        public void setDateFormat(String dateFormat) {
            this.dateFormat = dateFormat;
        }

        public String getUniqueid() {
            return uniqueid;
        }

        public void setUniqueid(String uniqueid) {
            this.uniqueid = uniqueid;
        }


        public Collection<FieldUtilised> getFieldUtilisedsByInRwFieldId() {
            return fieldUtilisedsByInRwFieldId;
        }

        public void setFieldUtilisedsByInRwFieldId(Collection<FieldUtilised> fieldUtilisedsByInRwFieldId) {
            this.fieldUtilisedsByInRwFieldId = fieldUtilisedsByInRwFieldId;
        }

        public Collection<FieldUtilised> getFieldUtilisedsByOutRwFieldId() {
            return fieldUtilisedsByOutRwFieldId;
        }

        public void setFieldUtilisedsByOutRwFieldId(Collection<FieldUtilised> fieldUtilisedsByOutRwFieldId) {
            this.fieldUtilisedsByOutRwFieldId = fieldUtilisedsByOutRwFieldId;
        }


        public RwRecord getRwRecord() {
            return rwRecord;
        }

        public void setRwRecord(RwRecord rwRecord) {
            this.rwRecord = rwRecord;
        }


        public DataType getDataType() {
            return dataType;
        }

        public void setDataType(DataType dataType) {
            this.dataType = dataType;
        }

        public String getFieldValue() {
        return fieldValue;
    }

         public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

        public RwField copy() {
            RwField copy = new RwField();

            copy.setRwFieldId(getRwFieldId());
            copy.setDataType(getDataType());
            copy.setDateFormat(getDateFormat());
            copy.setKeyField(getKeyField());
            copy.setDescription(getDescription());
            copy.setFieldName(getFieldName());
            copy.setFieldType(getFieldType());
            copy.setPosition(getPosition());
            copy.setUniqueid(getUniqueid());
            copy.setRwRecord(getRwRecord());
            copy.setFieldValue(getFieldValue());
            return copy;
        }

        @Override
        public String toString() {
            return "Field [" + rwRecord + ", name = " + fieldName + "]";
        }

}
