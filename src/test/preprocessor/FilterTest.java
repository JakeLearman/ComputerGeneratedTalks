package test.preprocessor;

import main.preprocessor.Filter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FilterTest {

    @Test
    void remove() {
        String ascii = "This ascii is in ASCII";
        Assertions.assertEquals(ascii, Filter.remove(ascii));

        String unicode = "☢a☀b♔c⛄";
        Assertions.assertEquals("abc", Filter.remove(unicode));
    }

}
