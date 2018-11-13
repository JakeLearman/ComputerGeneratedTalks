package test.parser;

import main.lexer.Lexeme;
import main.parser.LexemeList;
import main.parser.TreeLeaf;
import main.parser.TreeRoot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

class TreeRootTest {

    @Test
    void getLeaves() {
        // Assert Equals.
        TreeRoot root = new TreeRoot(new LexemeList(new Lexeme("x")));
        ArrayList<TreeLeaf> compare = new ArrayList<>();
        Assertions.assertEquals(compare, root.getLeaves());

        TreeLeaf a = new TreeLeaf(new Lexeme("a"));
        root.add(a);
        compare.add(a);
        Assertions.assertEquals(compare, root.getLeaves());

        TreeLeaf b = new TreeLeaf(new Lexeme("b"));
        root.add(b);
        compare.add(b);
        Assertions.assertEquals(compare, root.getLeaves());

        // Assert Not Equals.
        TreeLeaf c = new TreeLeaf(new Lexeme("c"));
        root.add(a);
        compare.add(c);
        Assertions.assertNotEquals(compare, root.getLeaves());
    }

    @Test
    void getLexemeList() {
        LexemeList list = new LexemeList(new Lexeme("A"));
        TreeRoot root = new TreeRoot(list);
        Assertions.assertEquals(list, root.getLexemeList());
    }

}