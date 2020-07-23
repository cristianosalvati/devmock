package it.cristiano.web.controller.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import it.cristiano.web.model.SimpleModelResponse;
import it.cristiano.web.model.InputI;
import it.cristiano.web.model.OutputI;
import it.cristiano.web.util.Constants;
import it.cristiano.web.util.ParseUtil;



@Controller
@RequestMapping(value="/wallet-manager")
public class TestAccountManager extends MockAbstract{

	@RequestMapping(value="/account/login", method=RequestMethod.POST)
	@ResponseBody
	public  ResponseEntity<String> callLogin(HttpServletRequest request) {

		try {
			String requestParams = request.getReader().lines().collect(java.util.stream.Collectors.joining());
			logger.info("requestParams = {}", requestParams);
		} catch (IOException e) {
			logger.error(e.getMessage());
//			e.printStackTrace();
		}
		
		String jsonResponseFileName = Constants.getJsonFolder()+getMockJsonFileName();
        logger.debug("jsonResponseFileName = {}", jsonResponseFileName);
        
        String jsonString = ParseUtil.jsonFromFile2String(jsonResponseFileName);
        logger.debug("jsonString = {}", jsonString);
        
        ResponseEntity resp= ResponseEntity.ok()
                .headers(httpHeaders)
                .body(jsonString);
        
        logger.debug("ResponseEntity = {}", resp);
        return resp;
	}
	
	@RequestMapping(value="/account/{accountId}/logout", method=RequestMethod.POST)
	@ResponseBody
	public SimpleModelResponse callLogout(HttpServletRequest request) {

		SimpleModelResponse o = new SimpleModelResponse();
    	o.setResult("OK");
    	return o;
	}
	
	@RequestMapping(value="/account/{accountId}/getPlayerInfo", method=RequestMethod.POST)
	@ResponseBody
	public SimpleModelResponse callPlayerInfo(HttpServletRequest request) {

		SimpleModelResponse o = new SimpleModelResponse();
    	o.setResult("OK");
    	return o;
	}
	

	@Override
	protected OutputI prepareOutput(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected InputI prepareInput(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
