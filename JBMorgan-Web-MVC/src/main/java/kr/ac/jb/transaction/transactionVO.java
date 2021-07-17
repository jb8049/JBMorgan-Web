package kr.ac.jb.transaction;

public class transactionVO {
	
	private int transactionNo;
	private String date;
	private int amount;
	private String accountNo;
	private String counterpartAccountNo;
	private String counterpartName;
	private String counterpartBank;
	private int balance;

	
	public int getTransactionNo() {
		return transactionNo;
	}
	public void setTransactionNo(int transactionNo) {
		this.transactionNo = transactionNo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getCounterpartAccountNo() {
		return counterpartAccountNo;
	}
	public void setCounterpartAccountNo(String counterpartAccountNo) {
		this.counterpartAccountNo = counterpartAccountNo;
	}
	public String getCounterpartName() {
		return counterpartName;
	}
	public void setCounterpartName(String counterpartName) {
		this.counterpartName = counterpartName;
	}
	public String getCounterpartBank() {
		return counterpartBank;
	}
	public void setCounterpartBank(String counterpartBank) {
		this.counterpartBank = counterpartBank;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	
	
	
}
