package com.winwebapps.utils;

import java.io.*;

public class FileCleanser {

    /**
     * This utility will take following three parameters and remove unwanted characters/words from the file and write changes pack to the same file.
     *
     * @param filePath        Input file path
     * @param matcherRegex    Regular expression of matching chars / text to be replaced
     * @param replacementText Replacement text (fixed string or char) for all the matching chars / text as per regex provided
     */
    public void removeUnwantedChars(String filePath, String matcherRegex, String replacementText) {
        try {
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", oldtext = "";
            while ((line = reader.readLine()) != null) {
                oldtext += line + "\r\n";
            }
            reader.close();
            String newtext = oldtext.replaceAll(matcherRegex, replacementText);
            FileWriter writer = new FileWriter(filePath);
            writer.write(newtext);
            writer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
