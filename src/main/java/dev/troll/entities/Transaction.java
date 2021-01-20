package dev.troll.entities;

public class Transaction {
	private int transactionId;
	private int accountId;
	private double fundchange;
	
	public Transaction()
	{
		super();
	}
	
	public Transaction(int accountId, double fundchange)
	{
		super();
		this.accountId = accountId;
		this.fundchange = fundchange;
	}
	
	public Transaction(int transactionId, int accountId, double fundchange)
	{
		super();
		this.transactionId = transactionId;
		this.accountId = accountId;
		this.fundchange = fundchange;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getFundchange() {
		return fundchange;
	}

	public void setFundchange(double fundchange) {
		this.fundchange = fundchange;
	}



	@Override
	public String toString() {
		return "Transaction: " + transactionId + " | accountId: " + accountId + " | " + fundchange;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (accountId != other.accountId)
			return false;
		if (Double.doubleToLongBits(fundchange) != Double.doubleToLongBits(other.fundchange))
			return false;
		if (transactionId != other.transactionId)
			return false;
		return true;
	}
	
	
}
