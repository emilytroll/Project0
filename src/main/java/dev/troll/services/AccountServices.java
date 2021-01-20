package dev.troll.services;

import java.util.ArrayList;
import dev.troll.daosimp.AccountDAOImp;
import dev.troll.daos.AccountDAO;
import dev.troll.entities.Account;

public class AccountServices 
{
	private AccountDAO ad = new AccountDAOImp();
	
	public Account getAccountById(int accountId)
	{
		return this.ad.getAccountById(accountId);
	}
	
	public ArrayList<Account> getAccountByUserId(int userId)
	{
		return this.ad.getAccountByUserId(userId);
	}
	
	public ArrayList<Account> getAllAccounts()
	{
		return this.ad.getAllAccounts();
	}

	public boolean addAccount(Account account)
	{
		return this.ad.addAccount(account);
	}

	public boolean updateAccount(Account change)
	{
		return this.ad.updateAccount(change);
	}

	public boolean deleteAccount(int accountId)
	{
		return this.ad.deleteAccount(accountId);
	}
	
	public ArrayList<Account> deletableAccounts(int userId)
	{
		return this.ad.deletableAccounts(userId);
	}
	
	public int stringCleaner(String str)
	{
		String cleanedStr = "";
		for (int i = 0; i < str.length(); i++)
		{
			if (Character.isDigit(str.charAt(i)))
			{
				//that's ok. we're ok with that
				cleanedStr += str.charAt(i);
				if (i == 10)
					return Integer.parseInt(cleanedStr);
			}
			else
			{
				System.err.println("[ACCOUNT]: Numbers only. No funny business.");
				break;
			}
		}
		return Integer.parseInt(cleanedStr);
	}
}
