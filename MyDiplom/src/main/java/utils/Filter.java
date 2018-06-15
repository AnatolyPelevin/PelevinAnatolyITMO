package utils;

public interface Filter<T> {
    boolean accepts(T object);
}
