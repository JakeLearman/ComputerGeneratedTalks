package main.preprocessor;

public class Preprocessor {

    public static String process(String string) {
        string = Filter.remove(string);
        /*string = string.replaceAll("[\\[]((\\d)+(,)(\\s)*)*(\\d)+[\\]]", ""); // replace reference
        string = string.replaceAll("[\\n](([Page]\\s)*\\d)+\\s", "\n"); // replace page number
        string = string.replaceAll("\\s(([Page]\\s)*\\d)+[\\n]", "\n");// replace page number
        string = string.replaceAll("[A-Z]\\.\\s[A-Za-z]+(\\,\\s[A-Z]\\.\\s[A-Za-z]+)*", ""); // replace the name like X. qi
        string = string.replaceAll("[http]{4}s*\\:\\/\\/"
        		+ "([a-zA-Z]|[0-9])+(\\.([a-zA-Z]|[0-9])*)*(\\/([a-zA-Z]|[0-9])*)*",""); // replace hyperlinks with http(s)
        string = string.replaceAll("[www]{3}(\\.([a-zA-Z]|[0-9])*)*(\\/([a-zA-Z]|[0-9])*)*",""); //replace hyperlinks with www
        string = string.replaceAll("\\s\\((((([A-Z]\\S+)|\\s|\\.|\\&|\\-|[et]|[al]|[and])+"
        		+ "(\\,\\s)*)+([1-2][0-9]{3}[a-z]*)*(\\;\\s)*)+\\)",""); // replace the citation
        string = string.replaceAll("\\s\\s[A-Z][a-z]+\\s[1-3]*[0-9](\\,\\s[1-2][0-9]{3})*\\s\\s", "  "); //replace the date in footer and header*/
        return string;
    }

}
