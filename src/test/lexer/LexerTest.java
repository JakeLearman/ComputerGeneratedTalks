package test.lexer;

import main.lexer.Lexeme;
import main.lexer.Lexer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class LexerTest {

    @Test
    void simpleTestGetLexemes() {
        String content = "Apple Banana";
        Lexer x = new Lexer(content);

        ArrayList<Lexeme> compare = new ArrayList<>();
        compare.add(new Lexeme("Apple"));
        compare.add(new Lexeme("Banana"));

        Assertions.assertEquals(compare, x.toLexemes());
    }

    @Test
    void simpleGrammarTestGetLexemes() {
        String content = "Cabbage.Delta";
        Lexer x = new Lexer(content);

        ArrayList<Lexeme> compare = new ArrayList<>();
        compare.add(new Lexeme("Cabbage"));
        compare.add(new Lexeme("."));
        compare.add(new Lexeme("Delta"));

        Assertions.assertEquals(compare, x.toLexemes());
    }

    @Test
    void advancedGrammarTestGetLexemes() {
        String content = "Example,, . ! Second:";
        Lexer x = new Lexer(content);

        ArrayList<Lexeme> compare = new ArrayList<>();
        compare.add(new Lexeme("Example"));
        compare.add(new Lexeme(","));
        compare.add(new Lexeme(","));
        compare.add(new Lexeme("."));
        compare.add(new Lexeme("!"));
        compare.add(new Lexeme("Second"));
        compare.add(new Lexeme(":"));

        Assertions.assertEquals(compare, x.toLexemes());
    }

    @Test
    void simpleNumberTestGetLexemes() {
        String content = "123456 7890";
        Lexer x = new Lexer(content);

        ArrayList<Lexeme> compare = new ArrayList<>();
        compare.add(new Lexeme("123456"));
        compare.add(new Lexeme("7890"));

        Assertions.assertEquals(compare, x.toLexemes());
    }

    @Test
    void advancedNumberTestGetLexemes() {
        String content = "3.1415926";
        Lexer x = new Lexer(content);

        ArrayList<Lexeme> compare = new ArrayList<>();
        compare.add(new Lexeme("3"));
        compare.add(new Lexeme("."));
        compare.add(new Lexeme("1415926"));

        Assertions.assertEquals(compare, x.toLexemes());
    }

}