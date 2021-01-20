package dev.troll.services;

import java.util.ArrayList;
import java.util.List;

import dev.troll.daos.TransactionDAO;
import dev.troll.daosimp.TransactionDAOImp;
import dev.troll.entities.Transaction;

public class TransactionServices 
{
	public TransactionDAO td = new TransactionDAOImp();
	
	public Transaction getTransactionById(int transactionId)
	{
		return this.td.getTransactionById(transactionId);
	}

	public ArrayList<Transaction> getTransactionByAccount(int accountId)
	{
		return this.td.getTransactionByAccount(accountId);
	}

	public ArrayList<Transaction> getAllTransactions()
	{
		return this.td.getAllTransactions();
	}

	public boolean addTransaction(Transaction transaction)
	{
		return this.td.addTransaction(transaction);
	}

	public boolean updateTransaction(Transaction change)
	{
		return this.td.updateTransaction(change);
	}

	public boolean deleteTransaction(int transactionId)
	{
		return this.deleteTransaction(transactionId);
	}
	
	public double stringCleaner(String str)
	{
		String cleanedStr = "";
		for (int i = 0; i < str.length(); i++)
		{
			if (Character.isDigit(str.charAt(i)) || str.charAt(i) == '.')
			{
				//that's ok. we're ok with that
				cleanedStr += str.charAt(i);
				if (i == 10)
					return Double.parseDouble(cleanedStr);
			}
			else if (str.charAt(i) == '$' || str.charAt(i) == ',' || str.charAt(i) == ' ')
				continue;
			else
			{
				System.err.println("[USERNAME/PASSWORD]: Numbers and letters only. No funny business.");
				break;
			}
		}
		return Double.parseDouble(cleanedStr);
	}
}
