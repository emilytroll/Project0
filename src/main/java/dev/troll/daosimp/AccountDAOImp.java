package dev.troll.daosimp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dev.troll.daos.AccountDAO;
import dev.troll.entities.Account;
import dev.troll.util.JDBCConnection;

public class AccountDAOImp implements AccountDAO{

	public static Connection conn = JDBCConnection.getConnection();

	public Account getAccountById(int accountId)
	{
		try
		{
			String sql = "SELECT * FROM accounts WHERE accountid = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(accountId));

			ResultSet rs = ps.executeQuery();

			if (rs.next())
			{
				Account acc = new Account();
				acc.setAccountId(rs.getInt("ACCOUNTID"));
				acc.setUserId(rs.getInt("USERID"));
				acc.setCurrentFunds(rs.getDouble("CURRENTFUNDS"));

				return acc;
			}
		}

		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Account> getAccountByUserId(int userId)
	{
		try
		{
			ArrayList<Account> usersAccounts = new ArrayList<Account>();
			String sql = "SELECT * FROM accounts WHERE userid = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(userId));

			ResultSet rs = ps.executeQuery();

			while (rs.next())
			{

				Account acc = new Account();
				acc.setAccountId(rs.getInt("ACCOUNTID"));
				acc.setUserId(rs.getInt("USERID"));
				acc.setCurrentFunds(rs.getDouble("CURRENTFUNDS"));
				usersAccounts.add(acc);
			}
			return usersAccounts;
		}

		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Account> getAllAccounts() {
		try
		{
			ArrayList<Account> allAccounts = new ArrayList<Account>();
			String sql = "SELECT * FROM accounts";

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next())
			{

				Account acc = new Account();
				acc.setAccountId(rs.getInt("ACCOUNTID"));
				acc.setUserId(rs.getInt("USERID"));
				acc.setCurrentFunds(rs.getDouble("CURRENTFUNDS"));
				allAccounts.add(acc);
			}
			return allAccounts;

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public boolean addAccount(Account account)
	{
		try 
		{				//userid, currentfunds
			String sql = "CALL add_account(?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, Integer.toString(account.getUserId()));
			cs.setString(2, Double.toString(account.getCurrentFunds()));
			cs.execute();
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateAccount(Account change)
	{
		try
		{
			String sql = "UPDATE accounts SET userid = ?, currentfunds = ? WHERE accountid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(change.getUserId()));
			ps.setString(2, Double.toString(change.getCurrentFunds()));
			ps.setString(3, Integer.toString(change.getAccountId()));

			ps.executeQuery();
			return true;
		}

		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return false;
	}

	public boolean deleteAccount(int accountId) {
		try {

			String sql = "DELETE accounts WHERE accountid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(accountId));

			ps.executeQuery();
			return true;			

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}
	
	public ArrayList<Account> deletableAccounts(int userId)
	{
		try
		{
			ArrayList<Account> accounts = new ArrayList<Account>();
			String sql = "SELECT * FROM accounts WHERE userid = ? AND currentfunds = 0";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(userId));
			
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				Account acc = new Account();
				acc.setAccountId(rs.getInt("ACCOUNTID"));
				acc.setUserId(userId);
				acc.setCurrentFunds(0);
				accounts.add(acc);
			}
			return accounts;
		}
		
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
