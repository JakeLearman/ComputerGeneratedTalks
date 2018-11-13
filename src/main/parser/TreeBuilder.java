package main.parser;

import main.lexer.Lexeme;

import java.util.ArrayList;
import java.util.HashMap;

public class TreeBuilder {

    private ArrayList<Lexeme> lexemes;

    public TreeBuilder(ArrayList<Lexeme> lexemes) {
        this.lexemes = lexemes;
    }

    // Produce a lexeme map from the given set of lexemes.
    public HashMap<LexemeList, TreeRoot> toTrees(int len) {
        HashMap<LexemeList, TreeRoot> roots = new HashMap<>();
        LexemeList key;

        for(int i = 0; i < lexemes.size() - len; i++) {
            key = new LexemeList();

            for(int k = 0; k < len; k++) {
                Lexeme next = lexemes.get(i + k);
                key.add(next);
            }

            TreeRoot root = roots.get(key);
            if(root == null) {
                root = new TreeRoot(key);
                roots.put(key, root);
            }
            Lexeme next = lexemes.get(i + len);
            root.add(new TreeLeaf(next));
        }

        return roots;
    }
}
