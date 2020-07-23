package it.cristiano.web.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.security.InvalidParameterException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.cristiano.web.model.InputI;
import it.cristiano.web.model.OutputI;

/**
 * Classe di supporto per il parsing degli oggetti presenti delle request
 */

public class ParseUtil {

	private static final Logger logger = LoggerFactory.getLogger(ParseUtil.class);
	  
	public static <T> InputI writeStreamToClass(Reader reqReader, Class<T> clazz) throws JsonParseException, IOException {
		 ObjectMapper objectMapper = new ObjectMapper();
		 JsonFactory factory = new JsonFactory();
		 JsonParser  jsonData  = factory.createParser(reqReader);
		 Object result = objectMapper.readValue(jsonData, clazz);
		 return (InputI) result;
	}
	
	  public static <T> OutputI readJsonFromFile(String fileName, Class<T> clazz) 
	    {
		  OutputI result = null;
	        try{
	            JsonFactory f = new JsonFactory();
	            InputStream is = getResourceInputStream(fileName);
		        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	            String jsonText = readAll(rd); 
	            ObjectMapper mapper = new ObjectMapper();
	            
	            result = (OutputI) mapper.readValue(jsonText, clazz);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return result; 
	    }
	  
	  public static String jsonFromFile2String(String fileName) 
	    {
		  String result = null;
	        try{
	            JsonFactory f = new JsonFactory();
		        InputStream is = getResourceInputStream(fileName);
		        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		        result = readAll(rd); 
	            
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return result; 
	    }
	  
	  private static InputStream getResourceInputStream(String resourceName) throws IOException {
//          /*IL ClassPathResource non sembra funzionare*/
//	        Resource resource = new ClassPathResource("classpath:"+fileName);
//	        InputStream is = resource.getInputStream();
//          /*------------------------------------------*/
//			URL url = ParseUtil.class.getResource(fileName); 
			URL url = null;
			try {
			  url =  ParseUtil.class.getClassLoader().getResource(resourceName);
			}catch(Throwable t) {
			    if (url == null )
                    url= Paths.get(resourceName).toFile().toURL();
			    if (url == null) {
			        File file = new File(resourceName);
			        url = file.toURL();
			    }
			}

			try {
			    InputStream is= null;
			    if (url!=null) {
    				 is = url.openStream();	
			    }else {
			        File initialFile = new File(resourceName);
			         is = new FileInputStream(initialFile);
			    }
			    logger.debug("load response in file = {}, inputStream = {}",resourceName, is);
				return is;
			} catch (IOException e) {
				throw e;
			}
	       
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
