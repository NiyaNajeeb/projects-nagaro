package com.nagaro.code.vo;

public class AccountStatementVO {
   private int id;
   private AccountVO accountVO;
   private String  date;
   private String amount;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public AccountVO getAccountVO() {
	return accountVO;
}
public void setAccountVO(AccountVO accountVO) {
	this.accountVO = accountVO;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getAmount() {
	return amount;
}
public void setAmount(String amount) {
	this.amount = amount;
}
   
   
}
