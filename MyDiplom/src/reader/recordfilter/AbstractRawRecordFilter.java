package reader.recordfilter;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class AbstractRawRecordFilter<T extends Enum<T>>
        implements ParametrizedRawRecordFilter<T> {
    private List<T> parametersList;

    public Collection<T> getParametersNames() {
        if (parametersList == null) {
            parametersList = Arrays.asList(getParametersNamesArray());
        }

        return parametersList;
    }

    public void reset() {
    }

    protected abstract T[] getParametersNamesArray();
}
