package main.preprocessor;

import java.util.HashMap;

public class Filter {

    private static final HashMap<Character, Character> swaps = new HashMap<>();

    static {
        swaps.put('—', '-'); // Wide dash to ASCII dash
    }

    public static String remove(String string) {
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);

            if(swaps.containsKey(ch)) ch = swaps.get(ch);
            if(Filter.isPermitted(ch)) builder.append(ch);
        }

        return builder.toString();
    }

    private static boolean isPermitted(char ch) {
        return Filter.isASCII(ch) || Filter.isCurrency(ch);
    }

    private static boolean isASCII(char ch) {
        return (ch == 13 || ch == 10) || (ch > 31) && (ch < 128);
    }

    private static boolean isCurrency(char ch) {
        return (ch == '£') || (ch == '$') || (ch == '€');
    }

}
