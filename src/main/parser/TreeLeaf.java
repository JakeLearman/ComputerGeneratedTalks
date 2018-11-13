package main.parser;

import main.lexer.Lexeme;

public class TreeLeaf {

    private Lexeme lexeme;
    private int frequency;

    public TreeLeaf(Lexeme lexeme) {
        this.lexeme = lexeme;
        this.frequency = 1;
    }

    public void increment() {
        frequency++;
    }

    public int getFrequency() {
        return frequency;
    }

    public boolean equals(Object o) {
        if(o instanceof TreeLeaf) {
            TreeLeaf compare = (TreeLeaf) o;
            return lexeme.equals(compare.lexeme);
        }
        return false;
    }

    public String toString() {
        return "{" + lexeme + " = " + frequency + "}";
    }

    public Lexeme getLexeme() {
        return lexeme;
    }

}
