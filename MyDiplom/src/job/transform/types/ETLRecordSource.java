package job.transform.types;

public class ETLRecordSource<T> {

    private T value;

    public ETLRecordSource(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public String toString() {
        return (value == null) ? null : value.toString();
    }
}
