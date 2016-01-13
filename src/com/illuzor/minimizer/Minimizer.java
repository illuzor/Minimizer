package com.illuzor.minimizer;

import org.json.JSONException;
import org.json.JSONObject;
import prophecy.common.JSONMinify;

import java.io.*;

public class Minimizer {
    public Minimizer(FileType type, String path) {
        File file = new File(path);

        if (file.exists()) {
            if (file.isFile()) {
                if (type == FileType.OTHER) {
                    writeFile(readFile(file), file.getAbsolutePath());
                }
                if (type == FileType.JSON) {
                    String minimizedJsonString = minimizeJSON(readFile(file), file.getAbsolutePath());
                    writeFile(minimizedJsonString, file.getAbsolutePath());
                }
            } else {
                System.out.println(file.getAbsolutePath() + " is not a file");
            }
        } else {
            System.out.println("File " + file.getAbsolutePath() + " is not exist");
        }
    }

    private String readFile(File file) {
        String fileData = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath()), "UTF8"));
            String line;
            while ((line = reader.readLine()) != null)
                fileData += line;

            reader.close();
        } catch (IOException e) {
            System.out.println("Unable to read file " + file.getAbsolutePath());
            System.exit(1);
        }
        return fileData;
    }

    private String minimizeJSON(String jsonString, String filePath) {
        if (isJson(jsonString)) {
            return JSONMinify.minify(jsonString);
        } else {
            System.out.println("File " + filePath + " is not json");
            System.exit(1);
            return null;
        }
    }

    private boolean isJson(String jsonString) {
        try {
            new JSONObject(jsonString);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    private void writeFile(String value, String filePath) {
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));
            out.write(JSONMinify.minify(value));
            out.close();
        } catch (IOException e) {
            System.out.println("Unable to write file " + filePath);
            System.exit(1);
        }
    }

}
