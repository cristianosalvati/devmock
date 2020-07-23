package it.cristiano.web.controller.rest;

import java.security.InvalidParameterException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import it.cristiano.web.model.InputI;
import it.cristiano.web.model.OutputI;



public abstract class MockAbstract {
	public static final String SERVICE_NAME_PREFIX ="call";
	protected final static HttpHeaders httpHeaders= new HttpHeaders();
	protected static final Logger logger = LoggerFactory.getLogger(MockAbstract.class);
	abstract protected OutputI prepareOutput(HttpServletRequest request) throws Exception;
	abstract protected InputI prepareInput(HttpServletRequest request) throws Exception;
	
	static{
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON);  
	    //TODO: definire qui ulteriori settagli per l'header di risposta dei mock
	}

	protected String getMockJsonFileName() {
		String clazzName = (this.getClass().getName().lastIndexOf(".") > 0) ? this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".")+1, this.getClass().getName().length()) : null;
		String methodName = (new Exception() .getStackTrace().length > 0) ? new Exception() .getStackTrace()[1] .getMethodName() : null;
		if (clazzName != null && methodName != null) 
			return (clazzName+"_"+methodName+"_response.json").toLowerCase();
		else 
			throw new InvalidParameterException("Invalid method name for json file");
	}
	
}
