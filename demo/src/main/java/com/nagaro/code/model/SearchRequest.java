package com.nagaro.code.model;

import java.io.Serializable;

public class SearchRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;

	private int accountID;
	private String startDate;
	private String endDate;
	private String startAmountRange;
	private String endAmountRange;

	// need default constructor for JSON Parsing
	public SearchRequest() {

	}

	public SearchRequest(int accountID, String startDate, String endDate, String startAmountRange,
			String endAmountRange) {
		super();
		this.accountID = accountID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startAmountRange = startAmountRange;
		this.endAmountRange = endAmountRange;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartAmountRange() {
		return startAmountRange;
	}

	public void setStartAmountRange(String startAmountRange) {
		this.startAmountRange = startAmountRange;
	}

	public String getEndAmountRange() {
		return endAmountRange;
	}

	public void setEndAmountRange(String endAmountRange) {
		this.endAmountRange = endAmountRange;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}