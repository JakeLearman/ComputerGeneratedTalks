package main.lexer;

import java.util.ArrayList;

public class Lexer {

    private Scanner scanner; // The Scanner

    public Lexer(String content) {
        this.scanner = new Scanner(content);
    }

    // Convert the content in the Scanner into a series of Lexemes.
    public ArrayList<Lexeme> toLexemes() {
        ArrayList<Lexeme> output = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        String content;
        Lexeme next;

        while(scanner.hasNext()) { // While we can read characters...
            char ch = scanner.next(); // ...read next character.

            if(LexerUtil.isSeparator(ch)) {
                if(builder.length() > 0) { // If the StringBuilder already contains characters for a Lexeme...
                    content = builder.toString();
                    next = new Lexeme(content); // ...make the Lexeme...
                    builder.setLength(0);
                    output.add(next); // ...add it to the output.
                }

                if(LexerUtil.isPunctuation(ch)) {
                    builder.append(ch); // Add the punctuation character to a new Lexeme.
                    content = builder.toString();
                    next = new Lexeme(content);
                    builder.setLength(0);
                    output.add(next);
                }
            } else {
                builder.append(ch);
            }
        }

        if(builder.length() > 0) { // If the StringBuilder contains any left over characters...
            content = builder.toString();
            next = new Lexeme(content); // ...add the characters to a new Lexeme.
            output.add(next);
        }

        return output;
    }

}
