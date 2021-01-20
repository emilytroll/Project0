package dev.troll.daos;

import java.util.ArrayList;
import dev.troll.entities.Account;

public interface AccountDAO 
{
	Account getAccountById(int accountId);
	ArrayList<Account> getAccountByUserId(int userId);
	ArrayList<Account> getAllAccounts();
	boolean addAccount(Account account);
	boolean updateAccount(Account change);
	boolean deleteAccount(int accountId);
	ArrayList<Account> deletableAccounts(int userId);
}
