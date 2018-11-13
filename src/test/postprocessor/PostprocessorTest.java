package test.postprocessor;

import main.postprocessor.Postprocessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PostprocessorTest {

    @Test
    void process() {
        String string;

        string = "Padding ( String Shrink ) 123";
        Assertions.assertEquals("Padding (String Shrink) 123", Postprocessor.process(string));

        string = "... . . 12 . 2014";
        Assertions.assertEquals(".....12.2014", Postprocessor.process(string));
    }

}
