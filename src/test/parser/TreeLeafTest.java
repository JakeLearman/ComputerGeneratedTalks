package test.parser;

import main.lexer.Lexeme;
import main.parser.TreeLeaf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TreeLeafTest {

    @Test
    void getLexeme() {
        Lexeme x;
        TreeLeaf t;

        x = new Lexeme("x");
        t = new TreeLeaf(x);
        Assertions.assertEquals(x, t.getLexeme());

        x = new Lexeme("a");
        Assertions.assertNotEquals(x, t.getLexeme());
    }

    @Test
    void getFrequency() {
        TreeLeaf leaf = new TreeLeaf(new Lexeme("test"));
        Assertions.assertEquals(1, leaf.getFrequency());
        leaf.increment();
        Assertions.assertEquals(2, leaf.getFrequency());
    }

    @Test
    void equals() {
        Lexeme x = new Lexeme("Lexeme");
        TreeLeaf a = new TreeLeaf(x);
        TreeLeaf b = new TreeLeaf(x);
        Assertions.assertEquals(a, b);
    }

}