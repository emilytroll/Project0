package dev.troll.entities;

public class User
{
	private int userId; //pk
	private String username; 
	private String password;
	private String isSuperuser;
	
	public User() {
		super();
		this.isSuperuser = "N";
	}
	
	public User(String username, String password)
	{
		super();
		this.username = username;
		this.password = password;
		this.isSuperuser = "N";
	}

	public User(int userId, String username, String password)
	{
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.isSuperuser = "N";
	}
	
	public User(String username, String password, String isSuperuser)
	{
		super();
		this.username = username;
		this.password = password;
		this.isSuperuser = isSuperuser;
	}
	
	public User(int userId, String username, String password, String isSuperuser)
	{
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.isSuperuser = isSuperuser;
	}


	public int getUserId() 
	{
		return userId;
	}


	public void setUserId(int userId)
	{
		this.userId = userId;
	}


	public String getUsername() 
	{
		return username;
	}


	public void setUsername(String username) 
	{
		this.username = username;
	}


	public String getPassword() 
	{
		return password;
	}


	public void setPassword(String password) 
	{
		this.password = password;
	}

	
	public String isSuperuser() {
		return isSuperuser;
	}

	public void setSuperuser(String string) {
		this.isSuperuser = string;
	}

	@Override
	public String toString() {
		return "User: " + userId + " | username: " + username + " | password: " + password + " | isSuperuser: " + isSuperuser;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (isSuperuser == null) {
			if (other.isSuperuser != null)
				return false;
		} else if (!isSuperuser.equals(other.isSuperuser))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId != other.userId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	


	
}
