package com.winwebapps.utils;

import com.mifmif.common.regex.Generex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

import static com.winwebapps.utils.ConfigFileReader.properties;

public class Helper {

    //public static String currentSite;
    static String absPath = new File("").getAbsolutePath().toString();
    static String filePath = absPath + properties.getProperty("text_file_dir_path");

    // Save data in a text file - Cache
    public static void saveDataInATextFile(String id, String fileName) throws Throwable {
        FileOutputStream fop = null;
        File file;
        file = new File(filePath.concat(fileName));
        fop = new FileOutputStream(file);
//        file.delete();
        if (!file.exists()) {
            file.createNewFile();
        }
        byte[] contentInBytes = id.getBytes();
        fop.write(contentInBytes);
        fop.flush();
        fop.close();
    }

    // Fetch saved data from the text file
    public static String getDataFromSavedTextFile(String fileName) throws Throwable {
        BufferedReader br = new BufferedReader(new FileReader(filePath.concat(fileName)));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

    public static String getInsertScript(String fileName) throws Throwable {
        String filePath = absPath + "\\src\\test\\java\\com\\winwebapps\\...\\...\\...\\configurations\\";
        BufferedReader br = new BufferedReader(new FileReader(filePath.concat(fileName)));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

    // Generate unique number
    public String generateUniqueNumber() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("ddMMyyHHmms");
        //System.out.println("Unique id: " + ft.format(dNow));
        return ft.format(dNow);
    }

    public String generate9DigitUniqueNumber() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dMMHHmms");
        return ft.format(dNow);
    }

    // Get today's date in YYYY-MM-DD format
    public String getTodaysDate() {
        Date todaysDate = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        return ft.format(todaysDate);
    }

    // Get date in a specific Format
    public String getDateInSpecificFormat(String dateFormat) {
        Date dateInSpecificFormat = new Date();
        SimpleDateFormat ft = new SimpleDateFormat(dateFormat);
        return ft.format(dateInSpecificFormat);
    }

    public static LocalDate getRandomDOB() {
        Random random = new Random();
        int minDay = (int) LocalDate.of(1969, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2005, 12, 31).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);

        LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);

        return randomBirthDate;

//        LocalDate start = LocalDate.of(1989, Month.OCTOBER, 14);
//        LocalDate end = LocalDate.now();
//        LocalDate random = RandomDates.between(start, end);
    }

    public static String getRandomNameSting() {
        Generex generex = new Generex("TEST [A-Z]{8}");
        String randomStr = generex.random();
        return randomStr;
    }

    public static String getRandomAddressString() {
        Generex generex = new Generex("ADD [A-Z]{8}");
        String randomStr = generex.random();
        return randomStr;
    }

    public static String getRandomPostCode() {
        Generex generex = new Generex("[A-Z]{2}[1-9]{2}[A-Z]{2}");
        String randomStr = generex.random();
        return randomStr;
    }

    public static String getRandomNINO() {
        Generex generex = new Generex("[A-CEGHJ-PR-TW-Z]{1}[A-CEGHJ-NPR-TW-Z]{1}[0-9]{6}[A-DFM]{0,1}");
        String randomNino = generex.random();
        return randomNino;
    }

    // Below only used for testing the various methods in above code and can be referred as example
    // FIXME Remove this code once ready for implementation in final framework
    public static void main(String[] args) throws Throwable {
        Helper helper = new Helper();
        System.out.println(helper.getRandomNameSting());
        System.out.println(helper.getRandomPostCode());
        System.out.println(helper.getRandomAddressString());
        System.out.println(helper.getRandomNINO());
        saveDataInATextFile("test", "testfile.txt");
        saveDataInATextFile("testnew", "testfile.txt");
        saveDataInATextFile("newtest", "testfile.txt");
        saveDataInATextFile("calcresult", "calcresult.txt");
        System.out.println("Data saved in text file is: " + getDataFromSavedTextFile("testfile.txt"));
        System.out.println(helper.getDateInSpecificFormat("yyyyMMdd-HHmmss"));

    }

}