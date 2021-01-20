package dev.troll.entities;

public class Account 
{
	private int accountId; //primary key
	private int userId;
	private double currentFunds;
	
	public Account() 
	{
		super();
	}
	public Account (int userId, double currentFunds)
	{
		super();
		this.userId = userId;
		this.currentFunds = currentFunds;
	}
	
	public Account(int accountId, int userId, double currentFunds)
	{
		super();
		this.accountId = accountId;
		this.userId = userId;
		this.currentFunds = currentFunds;
	}

	public int getAccountId() 
	{
		return accountId;
	}

	public void setAccountId(int accountId) 
	{
		this.accountId = accountId;
	}

	public int getUserId() 
	{
		return userId;
	}

	public void setUserId(int userId) 
	{
		this.userId = userId;
	}

	public double getCurrentFunds() 
	{
		return currentFunds;
	}

	public void setCurrentFunds(double currentFunds) 
	{
		this.currentFunds = currentFunds;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountId != other.accountId)
			return false;
		if (currentFunds != other.currentFunds)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	@Override
	public String toString() 
	{
		return "Account Number: " + accountId + " | Funds: $" + currentFunds;
	}
	
	
	

}
