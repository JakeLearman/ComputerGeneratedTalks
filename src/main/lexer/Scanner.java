package main.lexer;

public class Scanner {

    private String content; // The string that this Scanner will be stepping over.
    private int index; // The index of the next character to be read.

    public Scanner(String content) {
        this.content = content;
        this.index = 0;
    }

    public char next() {
        return content.charAt(index++); // Returns the next character (codepoint).
    }

    public boolean hasNext() {
        return index < content.length(); // If there are characters still to be read.
    }

}
