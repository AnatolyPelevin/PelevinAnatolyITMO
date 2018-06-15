package reader.recordfilter;


import utils.Filter;

public interface RawRecordFilter extends Filter<String> {
    void reset();
}
