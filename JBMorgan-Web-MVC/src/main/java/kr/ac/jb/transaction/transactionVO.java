package kr.ac.jb.transaction;

public class transactionVO {
	
	private int transactionNo;
	private String date;
	private int amount;
	private String accountNo;
	private String counterpartAccountNo;
	private String counterpartName;
	private String holder;
	private String type;
	private String counterpartBank;
	private int balance;
	private int counterBalance;
	private String myBankCode;
	
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
	public String getHolder() {
		return holder;
	}
	public void setHolder(String holder) {
		this.holder = holder;
	}
	public int getCounterBalance() {
		return counterBalance;
	}
	public void setCounterBalance(int counterBalance) {
		this.counterBalance = counterBalance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMyBankCode() {
		return myBankCode;
	}
	public void setMyBankCode(String myBankCode) {
		this.myBankCode = myBankCode;
	}
	
	
	
	
	
}
