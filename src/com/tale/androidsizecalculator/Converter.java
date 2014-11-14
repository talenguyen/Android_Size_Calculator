package com.tale.androidsizecalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Converter {

    public static final float L_DENSITY = 0.75f; // for low-density
    public static final float M_DENSITY = 1.0f; //baseline) for medium-density
    public static final float H_DENSITY = 1.5f; // for high-density
    public static final float XH_DENSITY = 2.0f; // for extra-high-density
    public static final float XXH_DENSITY = 3.0f; // for extra-extra-high-density
    public static final float XXXH_DENSITY = 4.0f; // for extra-extra-extra-high-density (launcher icon only; see note above)
    public static final float TABLE_7_600x1024 = (float) 600 / 320;
    public static final float TABLE_10_720x1280 = (float) 720 / 320;
    public static final float TABLE_10_800x1280 = (float) 800 / 320;
    private static BufferedReader bufferedReader;

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        int inputOption = readInputOption();
        if (inputOption < 1 || inputOption > 8) {
            System.out.println("Invalid option!");
            return;
        }
        System.out.print("Enter value: ");
        int value = readInputInt();
        int input = convertToMdpiValue(inputOption, value);
        System.out.println(String.format("MDPI value: %d", input));

        calculateAndPrintResult(input);
        System.out.println("Do you want to continues (Y/N): ");
        String answer = readInputString();
        if ("y".equalsIgnoreCase(answer) || "yes".equalsIgnoreCase(answer)) {
            clearScreen();
            run();
        } else {
            System.exit(0);
        }
    }

    private static void clearScreen() {
        for (int i=0; i<25; i++)
            System.out.println();
    }

    private static void calculateAndPrintResult(int input) {
        int ldpi = (int) (input * L_DENSITY);
        int hdpi = (int) (input * H_DENSITY);
        int xhdpi = (int) (input * XH_DENSITY);
        int xxhdpi = (int) (input * XXH_DENSITY);
        int xxxhdpi = (int) (input * XXXH_DENSITY);

        System.out.println("\n================ RESULT ================");
        System.out.println("\n================ PHONE ================");
        System.out.println(String.format("LDPI (phone -> 240x320): %d", ldpi));
        System.out.println(String.format("MDPI (phone -> 320x480): %d", input));
        System.out.println(String.format("HDPI (phone -> 480x800): %d", hdpi));
        System.out.println(String.format("XHDPI (phone -> 720x1280): %d", xhdpi));
        System.out.println(String.format("XXHDPI (phone -> 1080x1920): %d", xxhdpi));
        System.out.println("\n================ TABLE ================");
        int table_7_600x1024 = (int) (input * TABLE_7_600x1024);
        int table_10_720x1280 = (int) (input * TABLE_10_720x1280);
        int table_10_800x1280 = (int) (input * TABLE_10_800x1280);
        System.out.println(String.format("MDPI (7\" tablet -> 600x1024): %d", table_7_600x1024));
        System.out.println(String.format("MDPI (10\" tablet -> 720x1280): %d", table_10_720x1280));
        System.out.println(String.format("MDPI (10\" tablet -> 800x1280): %d", table_10_800x1280));
        System.out.println("\n================ NOTE ================");
        System.out.println(String.format("XXXHDPI (phone -> 480x800): %d => NOTE: You should not use the xxxhdpi qualifier for UI elements other than the launcher icon.", xxxhdpi));
    }

    private static int convertToMdpiValue(int option, int value) {
        switch (option) {
            case 1: return (int) (value / L_DENSITY);
            case 2: return (int) (value / M_DENSITY);
            case 3: return (int) (value / H_DENSITY);
            case 4: return (int) (value / XH_DENSITY);
            case 5: return (int) (value / XXH_DENSITY);
            case 6: return (int) (value / TABLE_7_600x1024);
            case 7: return (int) (value / TABLE_10_720x1280);
            case 8: return (int) (value / TABLE_10_800x1280);
        }
        return 0;
    }

    private static int readInputOption() {
        System.out.println("================ OPTIONS ================");
        System.out.println("1 => phone -> 240x320");
        System.out.println("2 => phone -> 320x480");
        System.out.println("3 => phone -> 480x800");
        System.out.println("4 => phone -> 720x1280");
        System.out.println("5 => phone -> 1080x1920");
        System.out.println("6 => 7\" tablet -> 600x1024");
        System.out.println("7 => 10\" tablet -> 720x1280");
        System.out.println("8 => 10\" tablet -> 800x1280");
        System.out.print("Enter you input for:");
        return readInputInt();
    }

    private static int readInputInt() {
        String s = readInputString();
        if (s != null && s.length() > 0) {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException nfe) {
                System.err.println("Invalid Format!");
            }
        }

        return 0;
    }

    private static String readInputString() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
