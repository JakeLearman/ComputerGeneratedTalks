package main.postprocessor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formatter {

    public static String format(String string) {
        string = string.replaceAll("[\\s][.]", "."); // " . " -> ". "
        string = string.replaceAll("[\\s][,]", ","); // " , " -> ", "
        string = string.replaceAll("[\\s][!]", "!"); // " ! " -> "! "
        string = string.replaceAll("[\\s][;]", ";"); // " ; " -> "; "
        string = string.replaceAll("[\\s]['][\\s]", "'"); // " ' " -> "'"
        string = string.replaceAll("[(][\\s]", "("); // "( " -> "("
        string = string.replaceAll("[\\s][)]", ")"); // " )" -> ")"
        string = string.replaceAll("[.][\\s](?=\\d)", "."); // "1. 67" -> "1.67"
        string = string.replaceAll("(?<=[0-9])+(,)[\\s](?=[0-9])+", ","); // "100, 000" -> "100,000"
        string = string.replaceAll("(?<=[0-9])+[\\s](?=%)", ""); // // "100 %" -> "100%"
        string = string.replaceAll("[\\s]-[\\s]", "-");

        string = Formatter.fixQuotes(string);

        return string;
    }

    private static final Pattern quotes = Pattern.compile("\"(.*?)\"");

    private static String fixQuotes(String string) {
        Matcher match = quotes.matcher(string);

        while(match.find()) {
            String group = match.group();

            String temp = group.replaceFirst("[\\s]", ""); // Match first whitespace
            temp = temp.replaceFirst("[\\s](?=[^ ]*$)", ""); // Match last whitespace

            string = string.replace(group, temp);
        }

        return string;
    }

}
