package dev.troll.services;

import java.util.ArrayList;
import dev.troll.daos.UserDAO;
import dev.troll.daosimp.UserDAOImp;
import dev.troll.entities.User;

public class UserServices 
{
	private UserDAO ud = new UserDAOImp();
	
	public User getUserById(int userId)
	{
		return this.ud.getUserById(userId);
	}
	
	public User getUserByUsername(String username)
	{
		return this.ud.getUserByUsername(username);
	}

	public ArrayList<User> getAllUsers()
	{
		return this.ud.getAllUsers();
	}
	
	public boolean addUser(User user)
	{
		return this.ud.addUser(user);
	}

	public boolean updateUser(User change)
	{
		return this.ud.updateUser(change);
	}
	
	public boolean deleteUser(int userId)
	{
		return this.ud.deleteUser(userId);
	}
	
	public boolean userLogin(User user)
	{
		return this.ud.userLogin(user);
	}
	
	public String stringCleaner(String str)
	{
		String cleanedStr = "";
		for (int i = 0; i < str.length(); i++)
		{
			if (Character.isLetterOrDigit(str.charAt(i)))
			{
				//that's ok. we're ok with that
				cleanedStr += str.charAt(i);
				if (i == 24)
					return cleanedStr;
			}
			else
			{
				System.err.println("[USERNAME/PASSWORD]: Numbers and letters only. No funny business.");
				break;
			}
		}
		return cleanedStr;
	}
}
