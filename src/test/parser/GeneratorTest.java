package test.parser;

import main.lexer.Lexeme;
import main.parser.Generator;
import main.parser.LexemeList;
import main.parser.TreeLeaf;
import main.parser.TreeRoot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class GeneratorTest {

    @Test
    void generate() {
        Lexeme a = new Lexeme("A");
        Lexeme b = new Lexeme("B");
        Lexeme c = new Lexeme("C");

        HashMap<LexemeList, TreeRoot> roots = new HashMap<>();

        LexemeList ka = new LexemeList();
        ka.add(a);
        TreeRoot ra = new TreeRoot(ka);
        ra.add(new TreeLeaf(b));

        LexemeList kb = new LexemeList();
        kb.add(b);
        TreeRoot rb = new TreeRoot(kb);
        rb.add(new TreeLeaf(c));

        roots.put(ka, ra);
        roots.put(kb, rb);

        Generator generator = new Generator(roots);

        Assertions.assertEquals("A ", generator.generate(ka, 1));
        Assertions.assertEquals("A B ", generator.generate(ka, 2));

        Assertions.assertEquals("B ", generator.generate(kb, 1));
        Assertions.assertEquals("B C ", generator.generate(kb, 2));
    }

}