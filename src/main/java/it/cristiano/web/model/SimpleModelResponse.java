package it.cristiano.web.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SimpleModelResponse {

	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
