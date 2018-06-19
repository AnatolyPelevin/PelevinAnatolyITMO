package writer.command;

import job.transform.types.FieldConversionException;
import objectmodel.RwField;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import utils.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static utils.Arguments.checkNotNull;

public class ConvertValues {
    private static String DEFAULT_DATE_FORMAT = "yyyyMMdd";


    public static BigDecimal setBigDecimalFromString(RwField field) throws FieldConversionException {
        String value = field.getFieldValue();
        try {
            if (value != null ) {
                value = value.trim();

                Integer decimalPosition = (field != null)
                        ? field.getDecimalPosition()
                        : null;

                if (decimalPosition != null) {
                    if (decimalPosition > value.length()) {
                        value = StringUtils.padLeft(value, decimalPosition, '0');
                    }

                    decimalPosition = value.length() - decimalPosition;

                    value = value.substring(0, decimalPosition) + "."
                            + value.substring(decimalPosition);
                }
            }

            if (value == null || "".equals(value.trim()) || "N.A.".equals(value.trim())) {
               return null;
            }
            else {
                return new BigDecimal(value);
            }
        }
        catch (Exception e) {
            throw new FieldConversionException("Error while converting to "
                    + field.getFieldName() + ": " + value, e);
        }
    }




    public static Boolean setBooleanFromString(RwField field) throws FieldConversionException {
        String value = field.getFieldValue();

        try {
            if (value == null) {
                return null;
            }
            else {
                value = value.trim();

                if (value.equalsIgnoreCase("0")
                        || value.equalsIgnoreCase("no")
                        || value.equalsIgnoreCase("n")
                        || value.equalsIgnoreCase("false")) {
                    return false;
                }
                else if (value.equalsIgnoreCase("1")
                        || value.equalsIgnoreCase("yes")
                        || value.equalsIgnoreCase("y")
                        || value.equalsIgnoreCase("true")) {
                    return true;
                }
                else {
                    throw new FieldConversionException("Error while converting to " + value);
                }
            }
        }
        catch (Exception e) {
            throw new FieldConversionException("Error while converting to " + value, e);
        }
    }

    public static Date setDateFromString(RwField field) throws FieldConversionException {
        String value = field.getFieldValue().trim();

        String dateFormat = StringUtils.isEmpty(field.getDateFormat())
                ? DEFAULT_DATE_FORMAT
                : field.getDateFormat();

        SafeDateTimeFormatter dateTimeFormatter = new SafeDateTimeFormatter(dateFormat);

        try {
            if (value == null || "".equals(value)) {
                return null;
            }
            else {
                return dateTimeFormatter.parseDateTime(value, dateFormat);
            }
        }
        catch (Exception e) {
            throw new FieldConversionException("Error while converting to "
                    + field.getFieldName() +  ": " + value, e);
        }
    }

    private static final class SafeDateTimeFormatter {
        private final String defaultDateFormat;
        private final DateTimeFormatter defaultDateFormatter;

        public SafeDateTimeFormatter(String dateFormat) {
            checkNotNull(dateFormat, "dateFormat");

            defaultDateFormat = dateFormat;
            defaultDateFormatter = DateTimeFormat.forPattern(dateFormat);
        }

        public Date parseDateTime(String value, String dateFormat) throws ParseException {
            checkNotNull(value, "value");

            Date result;
            String valueToParse = value.trim();

            try {
                DateTimeFormatter dateTimeFormatter = !StringUtils.isEmpty(dateFormat)
                        ? DateTimeFormat.forPattern(dateFormat)
                        : defaultDateFormatter;
                result = dateTimeFormatter.parseDateTime(valueToParse).toDate();
            }
            catch (IllegalArgumentException e) {
                String format = !StringUtils.isEmpty(dateFormat) ? dateFormat : defaultDateFormat;
                result = new SimpleDateFormat(format).parse(valueToParse);
            }

            return result;
        }

        public String print(Date date) {
            checkNotNull(date, "date");

            String result;

            try {
                result = defaultDateFormatter.print(date.getTime());
            }
            catch (IllegalArgumentException e) {
                result = new SimpleDateFormat(defaultDateFormat).format(date);
            }

            return result;
        }
    }

}
