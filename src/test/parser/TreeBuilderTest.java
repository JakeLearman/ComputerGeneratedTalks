package test.parser;

import main.lexer.Lexeme;
import main.parser.LexemeList;
import main.parser.TreeBuilder;
import main.parser.TreeLeaf;
import main.parser.TreeRoot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

class TreeBuilderTest {

    @Test
    void toTrees() {
        Lexeme a = new Lexeme("A");
        Lexeme b = new Lexeme("B");
        Lexeme c = new Lexeme("C");

        ArrayList<Lexeme> xs = new ArrayList<>();
        xs.add(a);
        xs.add(b);
        xs.add(c);

        TreeBuilder builder = new TreeBuilder(xs);

        HashMap<LexemeList, TreeRoot> roots = builder.toTrees(1);

        HashMap<LexemeList, TreeRoot> compare = new HashMap<>();

        LexemeList aList = new LexemeList(a);
        TreeLeaf bLeaf = new TreeLeaf(b);
        TreeRoot aRoot = new TreeRoot(aList);
        aRoot.add(bLeaf);

        LexemeList bList = new LexemeList(b);
        TreeLeaf cLeaf = new TreeLeaf(c);
        TreeRoot bRoot = new TreeRoot(bList);
        bRoot.add(cLeaf);

        compare.put(aList, aRoot);
        compare.put(bList, bRoot);

        Assertions.assertEquals(compare, roots);
    }

}