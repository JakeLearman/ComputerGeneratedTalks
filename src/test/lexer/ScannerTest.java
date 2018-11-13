package test.lexer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import main.lexer.Scanner;

class ScannerTest {

    @Test
    void next() {
        String content = "This is a test";
        Scanner scanner = new Scanner(content);

        for(int i = 0; i < content.length(); i++) {
            Assertions.assertEquals(content.codePointAt(i), scanner.next());
        }
    }

    @Test
    void hasNext() {
        String content = "This is a test";
        Scanner scanner = new Scanner(content);

        for(int i = 0; i < content.length(); i++) {
            Assertions.assertTrue(scanner.hasNext());
            scanner.next();
        }
        Assertions.assertFalse(scanner.hasNext());
    }

}