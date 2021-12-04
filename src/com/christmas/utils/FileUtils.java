package com.christmas.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

    public static String[] readFileToStringArray(String path)  {
        String data = FileUtils.readFileToString(path);

        return data.split("\n");
    }

    public static String readFileToString(String path)  {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

        } catch(IOException e) {
            System.out.println("Error while reading file " + path);
        }

        return sb.toString();
    }

}
