package test.postprocessor;

import main.postprocessor.Formatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FormatterTest {

    @Test
    void format() {
        String string = "It will , cost £ 1 . 5 ( money )";
        Assertions.assertEquals("It will, cost £ 1.5 (money)", Formatter.format(string));

        String quote = "Comment: \" this quote \"";
        Assertions.assertEquals("Comment: \"this quote\"", Formatter.format(quote));
    }

}
