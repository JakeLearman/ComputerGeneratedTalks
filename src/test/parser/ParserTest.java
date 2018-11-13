package test.parser;

import main.lexer.Lexeme;
import main.parser.LexemeList;
import main.parser.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ParserTest {

    @Test
    void parse() {
        Lexeme a = new Lexeme("A");
        Lexeme b = new Lexeme("B");
        Lexeme c = new Lexeme("C");

        ArrayList<Lexeme> xs = new ArrayList<>();
        xs.add(a);
        xs.add(b);
        xs.add(c);

        LexemeList list = new LexemeList();
        list.add(a);
        list.add(b);
        list.add(c);

        Parser parser = new Parser(xs);
        String compare = parser.parse(list, 3);
        Assertions.assertEquals("A B C ", compare);
    }

}