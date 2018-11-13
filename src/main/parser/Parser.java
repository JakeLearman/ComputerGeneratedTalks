package main.parser;

import main.lexer.Lexeme;

import java.util.*;

public class Parser {

    private TreeBuilder builder;
    private HashMap<LexemeList, TreeRoot> roots;
    private long seed;

    public Parser(ArrayList<Lexeme> lexemes) {
        builder = new TreeBuilder(lexemes);
    }

    public Parser(ArrayList<Lexeme> lexemes, long seed) {
        builder = new TreeBuilder(lexemes);
        this.seed = seed;
    }

    public void setLexemes(ArrayList<Lexeme> lexemes) {
        builder = new TreeBuilder(lexemes);
    }

    // Produce a string of a given length from a given lexeme list.
    public String parse(LexemeList init, int length) {
        roots = builder.toTrees(init.size());
        Generator generator = new Generator(roots, seed);
        return generator.generate(init, length);
    }

    public int size() {
        return roots.size();
    }

}
