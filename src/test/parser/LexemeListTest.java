package test.parser;

import main.lexer.Lexeme;
import main.parser.LexemeList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LexemeListTest {

    @Test
    void add() {
        Lexeme a = new Lexeme("A");

        LexemeList list = new LexemeList();
        list.add(a);

        ArrayList<Lexeme> compare = new ArrayList<>();
        compare.add(a);

        Assertions.assertEquals(compare, list.getLexemes());
    }

    @Test
    void clear() {
        LexemeList list = new LexemeList();
        list.add(new Lexeme("A"));
        Assertions.assertEquals(1, list.size());
        list.clear();
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void size() {
        LexemeList list = new LexemeList();
        int len = 8;

        for(int i = 0; i < len; i++) {
            Assertions.assertEquals(i, list.size());
            list.add(new Lexeme("A"));
        }
        Assertions.assertEquals(len, list.size());
    }

    @Test
    void remove() {
        LexemeList list = new LexemeList();
        Lexeme a = new Lexeme("A");
        list.add(a);
        list.remove(0);
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void getLexemes() {
        Lexeme a = new Lexeme("A");
        Lexeme b = new Lexeme("B");

        LexemeList list = new LexemeList();
        list.add(a);
        list.add(b);

        ArrayList<Lexeme> compare = new ArrayList<>();
        compare.add(a);
        compare.add(b);

        Assertions.assertEquals(compare, list.getLexemes());
    }
}