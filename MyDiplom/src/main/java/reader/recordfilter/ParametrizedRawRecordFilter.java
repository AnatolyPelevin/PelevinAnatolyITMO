package reader.recordfilter;

import java.util.Collection;
import java.util.Map;

public interface ParametrizedRawRecordFilter <T extends Enum<T>> extends RawRecordFilter {
    void initialize(Map<T, String> parameters);
    Collection<T> getParametersNames();
    Class<T> getEnumClass();
}
