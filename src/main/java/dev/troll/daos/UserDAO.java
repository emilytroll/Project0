package dev.troll.daos;

import java.util.ArrayList;
import dev.troll.entities.User;

public interface UserDAO 
{
	User getUserById(int userId);
	User getUserByUsername(String username);
	ArrayList<User> getAllUsers();
	boolean addUser(User user);
	boolean updateUser(User change);
	boolean deleteUser(int userId);
	boolean userLogin(User user);
}
