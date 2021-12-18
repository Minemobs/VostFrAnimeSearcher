package fr.minemobs.animes.utils;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StringUtils {

    private StringUtils() {}

    private static final Pattern numberPattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    /**
     * @return {@code true} if the string a contains the string b, ignoring case.
     */
    public static boolean containsIgnoreCase(String str, String searchStr) {
        if(str == null || searchStr == null) return false;

        final int length = searchStr.length();
        if (length == 0) return true;

        for (int i = str.length() - length; i >= 0; i--) {
            if (str.regionMatches(true, i, searchStr, 0, length)) return true;
        }
        return false;
    }

    public static boolean contains(String a, String... b) {
        return Stream.of(b).filter(Objects::nonNull).anyMatch(a::contains);
    }

    public static boolean containsIgnoreCase(String a, String... b) {
        return Stream.of(b).filter(Objects::nonNull).anyMatch(s -> containsIgnoreCase(s, a));
    }

    public static boolean equalsIgnoreCase(String a, String... b) {
        return Stream.of(b).filter(Objects::nonNull).anyMatch(a::equalsIgnoreCase);
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return numberPattern.matcher(strNum).matches();
    }
}
