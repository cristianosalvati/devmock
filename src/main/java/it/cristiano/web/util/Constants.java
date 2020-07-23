package it.cristiano.web.util;

import java.io.File;


public class Constants {
    public static final String APPLICATION_DOMAIN = "target";
    
    public static final String SERVICE_PACKAGE = File.separatorChar + "support" + File.separatorChar + "app" + File.separatorChar + "web" + File.separatorChar + "rest" + File.separatorChar + "bet";
    public static final String JSON_FOLDER = "json/";
    private static String externalJsonPath = null;
    
    public static String getJsonFolder() {
        if (externalJsonPath!= null)
            return externalJsonPath;
        else return JSON_FOLDER;
    }
    
    public static void setJsonFolder(String path) {
        if (externalJsonPath== null && path!=null)
            externalJsonPath = path;
    }
    
    
}
