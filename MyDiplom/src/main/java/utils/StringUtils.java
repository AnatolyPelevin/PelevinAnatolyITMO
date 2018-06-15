package utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {
    public static boolean isEmpty(String string) {
        return string == null || string.equals("");
    }

    public static String qouteChar(String string, char c) {
        if (string == null) {
            return null;
        }

        return string.replace(Character.toString(c), "\"" + c + "\"");
    }

    public static void cutTail(StringBuilder string, int tail) {
        if (string == null) {
            throw new NullPointerException("string");
        }

        if (tail < 0) {
            throw new IllegalArgumentException("Tail length must not be negative: " + tail);
        }

        if (string.length() < tail) {
            throw new IllegalArgumentException("Tail length exceeds string length: "
                    + tail + " > " + string.length());
        }

        string.delete(string.length() - tail, string.length());
    }

    public static List<String> split(String string, String delimiter, boolean trimChunks) {
        if (string == null) {
            throw new NullArgumentException("string");
        }

        if (delimiter == null) {
            throw new NullArgumentException("delimiter");
        }

        int start = 0;
        List<String> chunks = new ArrayList<String>();

        do {
            int end = string.indexOf(delimiter, start);

            if (end < 0) {
                end = string.length();
            }

            chunks.add(string.substring(start, end));
            start = end + delimiter.length();
        }
        while (start < string.length());

        if (!trimChunks) {
            return chunks;
        }

        List<String> result = new ArrayList<String>(chunks.size());

        for (String chunk : chunks) {
            result.add(chunk.trim());
        }

        return result;
    }

    public static List<String> split(String string, String delimiter) {
        return split(string, delimiter, false);
    }

    public static List<String> split(String string, char delimiter) {
        return split(string, Character.toString(delimiter), false);
    }

}
