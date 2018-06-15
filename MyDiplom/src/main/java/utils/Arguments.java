package utils;

public class Arguments {
    public static <T> T checkNotNull(T arg, String message) {
        if (arg != null) {
            return arg;
        }
        throw new NullArgumentException(message);
    }

    public static <T> T checkArgument(T arg, boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
        return arg;
    }
}
