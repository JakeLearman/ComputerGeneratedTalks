package test.lexer;

import main.lexer.Lexeme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LexemeTest {

    @Test
    void getContent() {
        String content;
        Lexeme x;

        content = "Apple";
        x = new Lexeme(content);
        Assertions.assertEquals(content, x.getContent());

        content = "?";
        x = new Lexeme(content);
        Assertions.assertEquals(content, x.getContent());

        content = "0987654321";
        x = new Lexeme(content);
        Assertions.assertEquals(content, x.getContent());

        content = "*&^<!";
        x = new Lexeme(content);
        Assertions.assertEquals(content, x.getContent());
    }

    @Test
    void equals() {
        Lexeme a = new Lexeme("Test!");
        Lexeme b = new Lexeme("Test!");
        Assertions.assertTrue(a.equals(b));

        Lexeme x = new Lexeme("Apple");
        Lexeme y = new Lexeme("Banana");
        Assertions.assertFalse(x.equals(y));
    }

    @Test
    void testToString() {
        Lexeme a = new Lexeme("Delta");
        Assertions.assertEquals("[Delta]", a.toString());
        Assertions.assertNotEquals("[delta]", a.toString());

        Lexeme b = new Lexeme("!");
        Assertions.assertEquals("[!]", b.toString());
        Assertions.assertNotEquals("[?]", b.toString());
    }

}