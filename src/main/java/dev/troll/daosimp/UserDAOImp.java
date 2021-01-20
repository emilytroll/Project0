package dev.troll.daosimp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dev.troll.daos.UserDAO;
import dev.troll.entities.User;
import dev.troll.util.JDBCConnection;

public class UserDAOImp implements UserDAO {	
	public static Connection conn = JDBCConnection.getConnection();

	public User getUserById(int userId)
	{
		try
		{
			String sql = "SELECT * FROM users WHERE userid = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(userId));

			ResultSet rs = ps.executeQuery();

			if (rs.next())
			{

				User usr = new User();
				usr.setUserId(rs.getInt("USERID"));
				usr.setUsername(rs.getString("USERNAME"));
				usr.setPassword(rs.getString("PASSWORD"));
				usr.setSuperuser(rs.getString("ISSUPERUSER"));

				return usr;
			}
		}

		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	public User getUserByUsername(String username)
	{
		try
		{
			String sql = "SELECT * FROM users WHERE username = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			if (rs.next())
			{
				User usr = new User();
				usr.setUserId(rs.getInt("USERID"));
				usr.setUsername(rs.getString("USERNAME"));
				usr.setPassword(rs.getString("PASSWORD"));
				usr.setSuperuser(rs.getString("ISSUPERUSER"));
				return usr;
			}
		}

		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<User> getAllUsers() {
		try
		{
			ArrayList<User> allUsers = new ArrayList<User>();
			String sql = "SELECT * FROM users";

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next())
			{

				User usr = new User();
				usr.setUserId(rs.getInt("USERID"));
				usr.setUsername(rs.getString("USERNAME"));
				usr.setPassword(rs.getString("PASSWORD"));
				usr.setSuperuser(rs.getString("ISSUPERUSER"));
				allUsers.add(usr);
			}
			return allUsers;

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public boolean addUser(User user)
	{
		try 
		{				//userid, currentfunds
			String sql = "CALL add_user(?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, user.getUsername());
			cs.setString(2, user.getPassword());
			cs.setString(3, user.isSuperuser());
			cs.execute();
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateUser(User change) {
		try
		{
			String sql = "UPDATE users SET username = ?, password = ?, issuperuser = ? WHERE userid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, change.getUsername());
			ps.setString(2, change.getPassword());
			ps.setString(3, change.isSuperuser());
			ps.setString(4, Integer.toString(change.getUserId()));
			
			ps.executeQuery();
			return true;
		}

		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean deleteUser(int userId) {
		try {
			
			String sql = "DELETE users WHERE userid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(userId));
			
			ps.executeQuery();
			return true;			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;

	}
	
	public boolean userLogin(User user)
	{
		try
		{			
			String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2,  user.getPassword());
			
			ResultSet rs = ps.executeQuery();
			
			if (!rs.next())
			{
				return false;
			}
			return true;
		}
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}

}
