package dev.troll.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dev.troll.entities.*;
import dev.troll.services.*;

public class App 
{
	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args)
	{	 
		System.out.println("Loading...");
		
		UserServices us = new UserServices();
		AccountServices as = new AccountServices();
		TransactionServices ts = new TransactionServices();
		String currUsername = "";
		String currPassword = "";
		String response = "";
		int userId = 0;
		boolean flag = false;
		
		System.out.println("Welcome to... Online Banking!");	
		System.out.println("Do you have an account already? (\"y\" for yes, \"n\" for no)");	
				
		//DO YOU HAVE AN ACCOUNT
		while (!(response.equals("y") || response.equals("n")))
		{
			response = scan.next();
			response = response.toLowerCase();
			if (response.equals("y"))
			{
				flag = true;
				System.out.println("Please input your username (CaSe SeNsItIvE)!");
				break;
			}
			else if (response.equals("n"))
			{
				flag = false;
				System.out.println("Please pick a username (CaSe SeNsItIvE, digits and letters only, up to 25 characters)!");
				break;
			}	
			System.err.println("Don't be a joker, just give a \"y\" or \"n\"!");
		}

		// GIVE ME YOUR USERNAME
		while (true)
		{
			System.out.println("USERNAME: ");
			response = scan.next();
			currUsername = us.stringCleaner(response);
			User user = us.getUserByUsername(currUsername);
			if (user == null)
			{
				if (flag) //user has username but we can't find it
				{
					System.err.println("Can't find your username, try again!");
					continue;
				}
				else if (!flag) //user is making new account, username available
				{
					System.out.println("Username is now yours!");
					break;
				}
			}
			else if (!(user == null))
			{
				if (flag) //user has username, we found it
				{
					System.out.println("Found you!");
					break;
				}
				else if (!flag) //user making new account, name taken
				{
					System.err.println("Username is taken!");
					continue;
				}
			}
		}
		
		//GIVE ME YOUR PASSWORD
		while (true)
		{
			System.out.println("PASSWORD: ");
			response = scan.next();
			currPassword = us.stringCleaner(response);
			User user = new User();
			user.setUsername(currUsername);
			user.setPassword(currPassword);
			
			if (flag) //user has acc
			{
				boolean loginSuccess = us.userLogin(user);
				if (loginSuccess)
				{
					System.out.println("Welcome back, " + currUsername + "!");
					break;
				}
				else if (!loginSuccess)
				{
					System.err.println("Incorrect password!");
					continue;
				}
			}
			else if (!flag) //user is making account
			{
				//we can double up on passwords. doesn't matter
				boolean addSuccess = us.addUser(user);
				if (!addSuccess)
				{
					System.err.println("Something went wrong");
					return;
				}
				boolean loginSuccess = us.userLogin(user);
				if (loginSuccess)
				{
					System.out.println("Welcome!");
					break;
				}
				else if (!loginSuccess)
				{
					System.err.println("Unwelcome?");
					continue;
				}
			}
		}
		User temp = us.getUserByUsername(currUsername);
		userId = temp.getUserId();
		
		//WELCOME TO THE JUNGLE
		System.out.println("Good to see you, " + currUsername + "!\nHere are your options:");
		int currAcc;
		while (true)
		{
			System.out.println("\t1) Manage/view accounts");
			System.out.println("\t2) Make/view transaction(s)");
			System.out.println("\t3) Logout");
			System.out.println("\t4) Quit");
			System.out.println("\t5) I am a superuser, give me those options");
			System.out.println("What do you want to do?: ");
			
			response = scan.next();
			response = us.stringCleaner(response);
			if (response.equals("1"))
			{
				while (true)
				{
					ArrayList<Account> usersAccounts = as.getAccountByUserId(userId);
					for (int i = 0; i < usersAccounts.size(); i++)
						System.out.println(usersAccounts.get(i).toString());
					System.out.println("Manage/view accounts:");
					System.out.println("\ta) Create new account");
					System.out.println("\tb) Delete EMPTY account");
					System.out.println("\tc) View accounts");
					System.out.println("\td) Go back");
					response = scan.next();
					response = us.stringCleaner(response);
					
					if (response.equals("a"))
					{
						Account newAcc = new Account();
						newAcc.setUserId(userId);
						newAcc.setCurrentFunds(0);
						as.addAccount(newAcc);
						System.out.println("New account created!");
					}
					
					else if (response.equals("b"))
					{
						System.out.println("Pick an account to delete: ");
						usersAccounts = as.deletableAccounts(userId);
						for (int i = 0; i < usersAccounts.size(); i++)
							System.out.println(usersAccounts.get(i).toString());
						response = scan.next();
						currAcc = as.stringCleaner(response); 
						as.deleteAccount(currAcc);
						System.out.println("Account " + currAcc + "deleted.");
					}
					
					else if (response.equals("c"))
					{
						for (int i = 0; i < usersAccounts.size(); i++)
							System.out.println(usersAccounts.get(i).toString());
					}
					
					else if (response.equals("d"))
					{
						break;
					}
				}
			}
			
			else if (response.equals("2"))
			{
				System.out.println("Manage/view transactions:");
				System.out.println("\ta) Make a transaction");
				System.out.println("\tb) View transactions");
				System.out.println("\tc) Go back");
				response = scan.next();
				response = us.stringCleaner(response);
				
				if (response.equals("a"))
				{
					System.out.println("Pick an account to withdraw/deposit from/to:");
					
					ArrayList<Account> usersAccounts = as.getAccountByUserId(userId);
					for (int i = 0; i < usersAccounts.size(); i++)
						System.out.println(usersAccounts.get(i).toString());
					response = scan.next();
					currAcc = as.stringCleaner(response);
					while (!(response.equals("w") || response.equals("d")))
					{
						System.out.println("Withdrawl (w) or deposit (d)?");
						response = scan.next();
						response = us.stringCleaner(response);
					}
					System.out.print("How much would you like to ");
					if (response.equals("w"))
					{
						System.out.println("withdraw?");
						flag = true; //use a minus sign
					}
					else if (response.equals("d"))
					{
						System.out.println("deposit?");
						flag = false; // no need!
					}
					System.out.println("(Feel free to use $ and , e.g. $1,000.00!)");
					
					response = scan.next();
					double changeInFunds = ts.stringCleaner(response);
					if (flag)
						changeInFunds *= -1;
					
					Transaction trans = new Transaction();
					trans.setAccountId(currAcc);
					trans.setFundchange(changeInFunds);
					ts.addTransaction(trans);
					
					Account accUpdate = as.getAccountById(currAcc);
					accUpdate.setCurrentFunds(accUpdate.getCurrentFunds() + changeInFunds);
					as.updateAccount(accUpdate);
					System.out.println("Transaction complete!");
				}
				
				else if (response.equals("b"))
				{
					while (true) 
					{
						System.out.println("View Transactions:");
						System.out.println("\tb1) View for specific account");
						System.out.println("\tb2) View for all accounts");
						System.out.println("\tb3) Go back");
						
						response = scan.next();
						response = us.stringCleaner(response);
						
						if (response.equals("b1"))
						{
							System.out.println("Pick an account:");
							ArrayList<Account> usersAccounts = as.getAccountByUserId(userId);
							for (int i = 0; i < usersAccounts.size(); i++)
								System.out.println(usersAccounts.get(i).toString());
							response = scan.next();
							currAcc = as.stringCleaner(response);
							
							ArrayList<Transaction> accountsTransactions = ts.getTransactionByAccount(currAcc);
							for (int i = 0; i < accountsTransactions.size(); i++)
								System.out.println(accountsTransactions.get(i).toString());
						}
						
						else if (response.equals("b2"))
						{
							ArrayList<Account> usersAccounts = as.getAccountByUserId(userId);
							for (int i = 0; i < usersAccounts.size(); i++)
							{
								ArrayList<Transaction> accountsTransactions = ts.getTransactionByAccount(usersAccounts.get(i).getAccountId());
								for (int j = 0; j < accountsTransactions.size(); j++)
									System.out.println(accountsTransactions.get(j).toString());
							}
						}
						else if (response.equals("b3"))
						{
							break;
						}
					}		
				}
			}
			else if (response.equals("3"))
			{
				System.out.println("Username " + currUsername + " logged out.");
				main(args);
			}
			else if (response.equals("4"))
			{
				System.out.println("Bye!");
				break;
			}
			
			else if (response.equals("5"))
			{
				User checkIfSuper = us.getUserById(userId);
				if (checkIfSuper.isSuperuser().equals("N"))
					continue;
				System.out.println("You're pretty super!");
				
				while (true)
				{
					System.out.println("\ta) Create new user");
					System.out.println("\tb) View users");
					System.out.println("\tc) Update users");
					System.out.println("\td) Delete user");
					System.out.println("\te) Go back");
					response = scan.next();
					
					if (response.equals("a"))
					{
						System.out.println("Username: ");
						String newUsername = scan.next();
						System.out.println("Password: ");
						String newPassword = scan.next();
						System.out.println("Is Superuser: (\"Y\" or \"N\")");
						String newSuperuser = scan.next();
						
						User newUser = new User();
						newUser.setUsername(newUsername);
						newUser.setPassword(newPassword);
						newUser.setSuperuser(newSuperuser);
						us.addUser(newUser);
						System.out.println("User " + newUsername + " added.");
					}
					
					else if (response.equals("b"))
					{
						ArrayList<User> allUsers = us.getAllUsers();
						for (int i = 0; i < allUsers.size(); i++)
							System.out.println(allUsers.get(i));
					}
					
					else if (response.equals("c"))
					{
						System.out.println("Pick a user (userid) to update");
						ArrayList<User> allUsers = us.getAllUsers();
						for (int i = 0; i < allUsers.size(); i++)
							System.out.println(allUsers.get(i));
						response = scan.next();
						int userBeingUpdatedId = as.stringCleaner(response);
						User userBeingUpdated = us.getUserById(userBeingUpdatedId);
						
						System.out.println("Alright, hotshot, what do you want to do?");
						System.out.println("c11) Change username");
						System.out.println("c12) Change password");
						System.out.println("c13) Change if superuser");
						System.out.println("c14) Go back");
						response = scan.next();
						
						if (response.equals("c11"))
						{
							System.out.println("Change username from " + userBeingUpdated.getUsername() + " to what?: ");
							response = scan.next();
							response = us.stringCleaner(response);
							userBeingUpdated.setUsername(response);
							us.updateUser(userBeingUpdated);
							System.out.println("Username changed.");
						}
						
						else if (response.equals("c12"))
						{
							System.out.println("Change password from " + userBeingUpdated.getPassword() + " to what?: ");
							response = scan.next();
							response = us.stringCleaner(response);
							userBeingUpdated.setPassword(response);
							us.updateUser(userBeingUpdated);
							System.out.println("Password changed.");
						}
						
						else if (response.equals("c13"))
						{
							System.out.println("Change account being superuser from " + userBeingUpdated.isSuperuser() + " to what?: ");
							response = scan.next();
							response = us.stringCleaner(response);
							userBeingUpdated.setSuperuser(response);
							us.updateUser(userBeingUpdated);
							System.out.println("isSuperuser changed.");
						}
						
						else if (response.equals("c14"))
						{
							break;
						}
						
					}
					else if (response.equals("d"))
					{
						System.out.println("Pick a user (userid) to delete");
						ArrayList<User> allUsers = us.getAllUsers();
						for (int i = 0; i < allUsers.size(); i++)
							System.out.println(allUsers.get(i));
						response = scan.next();
						int userToDelete = as.stringCleaner(response);
						us.deleteUser(userToDelete);
						System.out.println("User has been deleted!");
					}
					
					else if (response.equals("e"))
						break;
				}	
			}
		}
	}
}
