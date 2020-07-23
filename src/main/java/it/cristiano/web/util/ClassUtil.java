package it.cristiano.web.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe con metodi di utilita per la gestione della reflection per il recupero delle info sui servizi
 * esposti dal mock server 
 */
public class ClassUtil {

      private static final String CLASS_EXTENSION =".class"; 
      private static final String ABSTRACT = "Abstract";
      private static final char DOT ='.'; 
  	
      private static final Logger logger = LoggerFactory.getLogger(SystemUtil.class);

	  /**
	   * ritorna l'elenco delle classi contenuti all'interno di un certo package sulla base di un certo path 
	   * TODO: trovare un modo meno spartano
	 * @throws  
	   */
	  public static Object[] getClassesInPackage(String packageName, String resourcesPath) throws ClassNotFoundException{
	      
		   ArrayList<Object> objs = new ArrayList<Object>();
		   try {
				String path = resourcesPath+packageName;
				 
				List<String> classList = listFileInPath(path);
				for(String fullClassPath: classList) {
					if (!fullClassPath.contains(ABSTRACT)) {
				    	String fullPackageName = fullClassPath.substring(fullClassPath.indexOf("it\\"), fullClassPath.length()-CLASS_EXTENSION.length()).replace(File.separatorChar, DOT);
					Class<?> clazz = Class.forName(fullPackageName);
				    Constructor<?> ctor = clazz.getConstructor();
				    Object object = ctor.newInstance();
				    objs.add(object);
					}
		        }
		   }catch( IOException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException |
				   IllegalArgumentException | InvocationTargetException e) {
			   logger.error("Error loading class in package: {}, reason: ", packageName, e.getMessage());
		   		}
	        return objs.toArray(new Object[objs.size()]);
		    }
		  
	  public static List findClasses(File directory, String packageName) throws ClassNotFoundException {
	        List classes = new ArrayList();
	        if (!directory.exists()) {
	            return classes;
	        }
	        File[] files = directory.listFiles();
	        for (File file : files) {
	            if (file.isDirectory()) {
	                assert !file.getName().contains(".");
	                classes.addAll(findClasses(file, packageName + "." + file.getName()));
	            } else if (file.getName().endsWith(".class")) {
	                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
	            }
	        }
	        return classes;
	    }
	  
	  public static List<String> listFileInPath(String path) throws IOException{
		  		Stream<Path> walk = Files.walk(Paths.get(path));

				List<String> result = walk.filter(Files::isRegularFile)
						.map(x -> x.toString()).collect(Collectors.toList());

				return result;
	  }
}
