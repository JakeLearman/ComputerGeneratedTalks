package test.preprocessor;

import main.preprocessor.Preprocessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PreprocessorTest {

    @Test
    void process() {
        String string = "Apple ⤢⌔⠧Banana";
        Assertions.assertEquals("Apple Banana", Preprocessor.process(string));
    }
    @Test
    void testReference() {
    	String string = "theory [1,2].";
        Assertions.assertEquals("theory .", Preprocessor.process(string));
    }
    @Test
    void testName() {
    	String string = "name X. qi.";
        Assertions.assertEquals("name .", Preprocessor.process(string));
    }
    @Test
    void testHyperlinks() {
    	String string = "https://projects.cs.nott.ac.uk, and www.google.com";
        Assertions.assertEquals(", and ", Preprocessor.process(string));
    }
    @Test
    void testCitation() {
    	String string = "The idea (George, 1997).";
        Assertions.assertEquals("The idea .", Preprocessor.process(string));
    }
    @Test
    void testCitation1() {
    	String string = "Adapted after(Singh & Sutton, 1996; Naeeni, 2004).";
        Assertions.assertEquals("Adapted after .", Preprocessor.process(string));
    }
}
