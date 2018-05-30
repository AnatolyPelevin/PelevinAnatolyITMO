package utils;

public class Arguments {
    public static <T> T checkNotNull(T arg, String message) {
        if (arg != null) {
            return arg;
        }
        throw new NullArgumentException(message);
    }
}
