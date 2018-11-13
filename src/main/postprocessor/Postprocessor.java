package main.postprocessor;

import java.util.Random;

public class Postprocessor {

    public static String process(String string) {
        string = Formatter.format(string);

        Random random = new Random();

        // Make paragraphs.
        StringBuilder builder = new StringBuilder();

        int n = 0, m = 10;
        for(int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);

            builder.append(ch);
            if(isTerminator(ch)) {
                if(n > m) {
                    builder.append("\n\n");
                    n = 0;
                    m = 10 + random.nextInt(5);
                } else {
                    n++;
                }
            }
        }

        return builder.toString();
    }

    public static boolean isTerminator(char ch) {
        return (ch == '!' || ch == '.' || ch == '?' || ch == ':');
    }

}
