package com.illuzor.minimizer;

public class Main {

    public static void main(String[] args) {
        if (args.length == 1) {
            if (args[0].equals("help") || args[0].equals("h")) {
                showHelp();
            } else {
                showAlert();
            }
        } else if (args.length == 2) {
            switch (args[0]) {
                case "-l":
                    new Minimizer(FileType.OTHER, args[1]);
                    break;
                case "-j":
                    new Minimizer(FileType.JSON, args[1]);
                    break;
                default:
                    showAlert();
            }
        } else {
            showAlert();
        }
    }

    private static void showAlert() {
        System.out.println("Incorrect input. Enter 'help' or 'h'.");
    }

    private static void showHelp() {
        System.out.println("");
        System.out.println("Using sample:");
        System.out.println("java -jar Minimizer.jar -j your.json");
    }
}
