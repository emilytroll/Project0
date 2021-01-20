package dev.troll.daosimp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dev.troll.daos.TransactionDAO;
import dev.troll.entities.Transaction;
import dev.troll.util.JDBCConnection;

public class TransactionDAOImp implements TransactionDAO
{
	public static Connection conn = JDBCConnection.getConnection();

	public Transaction getTransactionById(int transactionId)
	{
		try
		{
			String sql = "SELECT * FROM transactions WHERE transactionid = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(transactionId));

			ResultSet rs = ps.executeQuery();

			if (rs.next())
			{
				Transaction trans = new Transaction();
				trans.setTransactionId(rs.getInt("TRANSACTIONID"));
				trans.setAccountId(rs.getInt("ACCOUNTID"));
				trans.setFundchange(rs.getDouble("FUNDCHANGE"));

				return trans;
			}
		}

		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Transaction> getTransactionByAccount(int accountId)
	{
		try
		{
			ArrayList<Transaction> accountsTransactions = new ArrayList<Transaction>();
			String sql = "SELECT * FROM transactions WHERE accountid = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(accountId));

			ResultSet rs = ps.executeQuery();

			while (rs.next())
			{
				Transaction trans = new Transaction();
				trans.setTransactionId(rs.getInt("TRANSACTIONID"));
				trans.setAccountId(rs.getInt("ACCOUNTID"));
				trans.setFundchange(rs.getDouble("FUNDCHANGE"));

				accountsTransactions.add(trans);
			}
			return accountsTransactions;
		}

		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Transaction> getAllTransactions()
	{
		try
		{
			ArrayList<Transaction> allTransactions = new ArrayList<Transaction>();
			String sql = "SELECT * FROM transactions";

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next())
			{

				Transaction trans = new Transaction();
				trans.setTransactionId(rs.getInt("TRANSACTIONID"));
				trans.setAccountId(rs.getInt("ACCOUNTID"));
				trans.setFundchange(rs.getDouble("FUNDCHANGE"));

				allTransactions.add(trans);
			}
			return allTransactions;

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public boolean addTransaction(Transaction transaction)
	{
		try 
		{				
			String sql = "CALL add_transaction(?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, Integer.toString(transaction.getAccountId()));
			cs.setString(2, Double.toString(transaction.getFundchange()));

			return (cs.execute());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateTransaction(Transaction change)
	{
		try
		{
			String sql = "UPDATE transactions SET accountid = ?, fundchange = ? WHERE transactionid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(change.getAccountId()));
			ps.setString(2, Double.toString(change.getFundchange()));
			ps.setString(3, Integer.toString(change.getTransactionId()));
			
			ps.executeQuery();
			return true;
		}

		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean deleteTransaction(int transactionId)
	{
		try
		{
			String sql = "DELETE transactions WHERE transactionid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(transactionId));
			
			ps.executeQuery();
			return true;			
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}

}

