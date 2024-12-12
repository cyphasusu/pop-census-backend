package com.ecl.popcensus.util;


import java.io.*;
import java.util.Properties;

public class Settings {

    private static Settings uniqueInstance = null;
    public Properties props;
    private static final String configFileName = "application.properties";

    private Settings(String s) {
        props = null;
        // logger = //logger.getLogger(getClass().getName());
        props = new Properties();
        myLoad(s);
    }

    public static String ReadFileContent(String resultFile) {
        StringBuilder response = new StringBuilder();
        try {
            File ff = new File(resultFile);
            BufferedReader in = null;
            if (ff.isFile()) {
                in = new BufferedReader(new FileReader(ff));
                String tmp = "";
                while ((tmp = in.readLine()) != null) {
                    response.append(tmp);
                }
                in.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response.toString();
    }

    public static String filename(String fullPath) { // gets filename without extension
        int dot = fullPath.lastIndexOf(".");
        int sep = fullPath.lastIndexOf("/");
        return fullPath.substring(sep + 1, dot);
    }

    private synchronized void myLoad(String s) {
        try {
            Thread currentThread = Thread.currentThread();
            ClassLoader contextClassLoader = currentThread.getContextClassLoader();
            InputStream propertiesStream = contextClassLoader.getResourceAsStream(configFileName);
            if (propertiesStream != null) {
                props.load(propertiesStream);
                propertiesStream.close();
            } else {
            }
        } catch (IOException ioexception) {
            System.out.println(ioexception.getMessage());
        }
    }

    public static synchronized Settings getInstance(String s) {
        if (uniqueInstance == null) {
            uniqueInstance = new Settings(s);
        }
        return uniqueInstance;
    }

    public Properties getPropertyFile() {
        return props; // .getProperty(s);
    }

    public synchronized String getProperty(String s) {
        return props.getProperty(s);
    }

    public synchronized void setProperty(String s, String s1) {
        try {
            if (s1 == null) {
                // logger.debug("Key=" + s + ", Value=" + s1);
                s1 = "Empty";
            }
            props.setProperty(s, s1);
            FileOutputStream fileoutputstream = new FileOutputStream(configFileName);
            props.store(fileoutputstream, "Settings");
        } catch (IOException ioexception) {
            // logger.debug("failed load properties due to : " + ioexception);
        }
    }

}

