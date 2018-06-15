package reader.recordfilter;

import java.util.Map;

public class KeywordRecordFilter extends AbstractRawRecordFilter<KeywordRecordFilter.InitParameter> {
    private String startKeyword;
    private String endKeyword;
    private String skipPrefix;
    private boolean processRecord;

    public static enum InitParameter {
        START_KEY_WORD,
        END_KEY_WORD,
        SKIP_PREFIX
    }

    @Override
    protected InitParameter[] getParametersNamesArray() {
        return InitParameter.values();
    }

    public boolean accepts(String record) {
        record = record.trim();

        if (startKeyword != null && record.startsWith(startKeyword)) {
            processRecord = true;
            return false;
        }

        if (endKeyword != null && record.startsWith(endKeyword)) {
            processRecord = false;
        }

        if (skipPrefix != null && processRecord) {
           return !record.startsWith(skipPrefix);
        }

        return processRecord;
    }

    public void initialize(Map<InitParameter, String> parameters) {
        for (Map.Entry<InitParameter, String> entry : parameters.entrySet()) {
            switch (entry.getKey()) {
                case START_KEY_WORD:
                    startKeyword = entry.getValue();
                    break;

                case END_KEY_WORD:
                    endKeyword = entry.getValue();
                    break;

                case SKIP_PREFIX:
                    skipPrefix = entry.getValue();
                    break;
            }
        }

        processRecord = (startKeyword == null);
    }

    public Class<InitParameter> getEnumClass() {
        return InitParameter.class;
    }

    @Override
    public void reset() {
        processRecord = (startKeyword == null);
    }
}
