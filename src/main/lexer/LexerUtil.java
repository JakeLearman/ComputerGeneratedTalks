package main.lexer;

public class LexerUtil {

    public static boolean isSeparator(char ch) {
        return Character.isWhitespace(ch) || isPunctuation(ch);
    }

    public static boolean isPunctuation(char ch) {
        return !Character.isAlphabetic(ch) && !Character.isDigit(ch) && !Character.isWhitespace(ch);
    }

}
