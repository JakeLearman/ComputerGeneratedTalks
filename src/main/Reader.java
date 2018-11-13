package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Reader {

	// Read each file
    public static String read(String...filenames) throws Exception {
        File[] files = new File[filenames.length];

        for(int i = 0; i < filenames.length; i++) {
            files[i] = new File(filenames[i]);
        }

        return Reader.read(files);
    }

    // Create a string with the content of each file
    private static String read(File...files) throws Exception {
        StringBuilder builder = new StringBuilder();

        for(File file : files) {
            String temp = Reader.read(file);
            builder.append(temp);
        }

        return builder.toString();
    }

    // Read each file into the string builder
    private static String read(File file) throws Exception {
        StringBuilder builder = new StringBuilder();

        BufferedReader reader = new BufferedReader(new FileReader(file));

        while(true) {
            String line = reader.readLine();
            if(line == null) break;
            builder.append(line);
            builder.append('\n');
        }

        reader.close();

        return builder.toString();
    }

}
