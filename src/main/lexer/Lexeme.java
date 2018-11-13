package main.lexer;

public class Lexeme{

    private String content; // The string this Lexeme represents, can be words, numbers, symbols etc.

    public Lexeme(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public boolean equals(Object o) { // Compare the content inside the Lexemes to assert equality.
        if(o instanceof Lexeme) {
            Lexeme compare = (Lexeme) o;
            return content.equals(compare.content);
        }
        return false;
    }

    public int hashCode() {
        return content.hashCode();
    }

    public String toString() {
        return "[" + content + "]";
    }

}
