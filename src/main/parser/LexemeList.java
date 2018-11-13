package main.parser;

import main.lexer.Lexeme;

import java.util.ArrayList;
import java.util.Arrays;

public class LexemeList {
	
	// This class is mainly a wrapper for the ArrayList class

    private ArrayList<Lexeme> lexemes;

    public LexemeList() {
        this.lexemes = new ArrayList<>();
    }

    public LexemeList(Lexeme...lexemes) {
        this.lexemes = new ArrayList<>();
        this.lexemes.addAll(Arrays.asList(lexemes));
    }

    public void add(Lexeme lexeme) {
        lexemes.add(lexeme);
    }

    public void clear() {
        lexemes.clear();
    }

    public int size() {
        return lexemes.size();
    }

    public void remove(int index) {
        lexemes.remove(index);
    }

    public boolean equals(Object o) {
        if(o instanceof LexemeList) {
            LexemeList compare = (LexemeList) o;
            return lexemes.equals(compare.lexemes);
        }
        return false;
    }

    public int hashCode() {
        return lexemes.hashCode();
    }

    public ArrayList<Lexeme> getLexemes() {
        return lexemes;
    }

    public String toString() {
        return lexemes.toString();
    }
}
