package main.parser;

import java.util.ArrayList;

public class TreeRoot {

    private ArrayList<TreeLeaf> leaves;
    private LexemeList lexeme;

    public TreeRoot(LexemeList lexeme) {
        this.lexeme = lexeme;
        leaves = new ArrayList<>();
    }

    public void add(TreeLeaf leaf) {
        int index = leaves.indexOf(leaf);

        // Add a leaf if we don't already contain it.
        if(index == -1) {
            leaves.add(leaf);
        } else {
            leaves.get(index).increment();
        }
    }

    public boolean equals(Object o) {
        if(o instanceof TreeRoot) {
            TreeRoot compare = (TreeRoot) o;
            return lexeme.equals(compare.lexeme);
        }
        return false;
    }

    public int hashCode() {
        return lexeme.hashCode();
    }

    public LexemeList getLexemeList() {
        return lexeme;
    }

    public String toString() {
        return leaves.toString();
    }

    public ArrayList<TreeLeaf> getLeaves() {
        return leaves;
    }
}
