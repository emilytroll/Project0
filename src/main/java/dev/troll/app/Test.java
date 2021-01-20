package dev.troll.app;

import dev.troll.daos.UserDAO;
import dev.troll.daosimp.UserDAOImp;
import dev.troll.entities.User;

public class Test {

	public static void main(String[] args)
	{
		System.out.println("HELLO");
		UserDAO u = new UserDAOImp();
		System.out.println(u.toString());
		System.out.println("GOODBYE");
	}

}
