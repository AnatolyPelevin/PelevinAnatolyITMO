package job.transform.types;


import objectmodel.RwField;

public class ETLFieldFactory {
    public static ETLField<?> createETLField(RwField field) {
        ETLField<?> etlField = null;

        switch (field.getDataType()) {
//            case DECIMAL:
//                etlField = new ETLDecimalField(field);
//                break;
//
//            case BOOLEAN:
//                etlField = new ETLBooleanField(field);
//                break;
//
//            case DATE:
//                etlField = new ETLDateField(field);
//                break;
//
//            case DOUBLE:
//                etlField = new ETLDoubleField(field);
//                break;
//
//            case INTEGER:
//                etlField = new ETLIntegerField(field);
//                break;
//
//            case STRING:
//                etlField = new ETLStringField(field);
//                break;

            default:
                throw new IllegalArgumentException("ETL type not registered for a field type: "
                        + field.getDataType() + ".");
        }

       // return etlField;
    }
}
