package it.cristiano.web.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileUtil {

    private static Object loadJsonFromFile(String fileName, Class clazz) 
    {
        Object result = null;
        try{     
            JsonFactory f = new JsonFactory();
            URL url = FileUtil.class.getResource(fileName);
            InputStream is = url.openStream();
            InputStreamReader ir = new InputStreamReader(is, Charset.forName("UTF-8"));
            BufferedReader rd = new BufferedReader(ir);
            String jsonText = readAll(rd); 
            ObjectMapper mapper = new ObjectMapper();
            
            result = mapper.readValue(jsonText, clazz);
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result; 
    }
    
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
          sb.append((char) cp);
        }
        return sb.toString();
      }
    
}
