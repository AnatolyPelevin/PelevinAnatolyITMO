package reader;

import utils.StringUtils;

import java.util.List;

public class DefaultDelimitedRecordSplitter implements DelimitedRecordSplitter {
    public String[] split(String record, String delimiter) {

        List<String> strings = StringUtils.split(record, delimiter);
        return strings.toArray(new String[strings.size()]);

    }
}
