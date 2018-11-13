package test.lexer;

import main.lexer.LexerUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LexerUtilTest {

    @Test
    void isSeparator() {
        // Whitespace characters are all considered separators.
        Assertions.assertTrue(LexerUtil.isSeparator(' '));
        Assertions.assertTrue(LexerUtil.isSeparator('\t'));
        Assertions.assertTrue(LexerUtil.isSeparator('\n'));
        Assertions.assertTrue(LexerUtil.isSeparator('\r'));

        // Punctuation characters are separators (but not visa-versa).
        Assertions.assertTrue(LexerUtil.isSeparator('?'));
        Assertions.assertTrue(LexerUtil.isSeparator('!'));
        Assertions.assertTrue(LexerUtil.isSeparator('.'));
        Assertions.assertTrue(LexerUtil.isSeparator('*'));

        // Alphabetic characters and digits are not separators.
        Assertions.assertFalse(LexerUtil.isSeparator('A')); // Uppercase tests.
        Assertions.assertFalse(LexerUtil.isSeparator('Y'));
        Assertions.assertFalse(LexerUtil.isSeparator('c')); // Lowercase tests.
        Assertions.assertFalse(LexerUtil.isSeparator('v'));
        Assertions.assertFalse(LexerUtil.isSeparator('3')); // Digit tests.
        Assertions.assertFalse(LexerUtil.isSeparator('6'));
    }

    @Test
    void isPunctuation() {
        // Try a range of different punctuation characters.
        Assertions.assertTrue(LexerUtil.isPunctuation('!'));
        Assertions.assertTrue(LexerUtil.isPunctuation('?'));
        Assertions.assertTrue(LexerUtil.isPunctuation('.'));
        Assertions.assertTrue(LexerUtil.isPunctuation(','));
        Assertions.assertTrue(LexerUtil.isPunctuation('#'));
        Assertions.assertTrue(LexerUtil.isPunctuation('@'));
        Assertions.assertTrue(LexerUtil.isPunctuation(':'));
        Assertions.assertTrue(LexerUtil.isPunctuation('&'));

        // Whitespace is not punctuation.
        Assertions.assertFalse(LexerUtil.isPunctuation(' '));
        Assertions.assertFalse(LexerUtil.isPunctuation('\t'));
        Assertions.assertFalse(LexerUtil.isPunctuation('\n'));
        Assertions.assertFalse(LexerUtil.isPunctuation('\r'));

        // Alphabetic characters and digits are not punctuation.
        Assertions.assertFalse(LexerUtil.isPunctuation('A')); // Uppercase tests.
        Assertions.assertFalse(LexerUtil.isPunctuation('J'));
        Assertions.assertFalse(LexerUtil.isPunctuation('s')); // Lowercase tests.
        Assertions.assertFalse(LexerUtil.isPunctuation('z'));
        Assertions.assertFalse(LexerUtil.isPunctuation('0')); // Digit tests.
        Assertions.assertFalse(LexerUtil.isPunctuation('5'));
        Assertions.assertFalse(LexerUtil.isPunctuation('9'));
    }

}