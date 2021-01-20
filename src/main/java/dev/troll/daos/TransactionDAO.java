package dev.troll.daos;

import java.util.ArrayList;
import dev.troll.entities.Transaction;

public interface TransactionDAO 
{
	Transaction getTransactionById(int transactionId);
	ArrayList<Transaction> getTransactionByAccount(int accountId);
	ArrayList<Transaction> getAllTransactions();
	boolean addTransaction(Transaction transaction);
	boolean updateTransaction(Transaction change);
	boolean deleteTransaction(int transactionId);
}
