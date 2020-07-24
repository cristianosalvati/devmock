package it.cristiano.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserAccount {

	@JsonProperty("accountId")
	private String accountId = null;

	@JsonProperty("password")
	private String password = null;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserAccount [accountId=" + accountId + ", password=" + password + "]";
	}

	
}