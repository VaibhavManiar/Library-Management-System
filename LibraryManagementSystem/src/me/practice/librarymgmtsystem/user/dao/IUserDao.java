package me.practice.librarymgmtsystem.user.dao;

import me.practice.librarymgmtsystem.model.User;

public interface IUserDao {
	/**
	 * Adds the user to the list of user if not present else throws exception 'User
	 * already exists'
	 * 
	 * @param user
	 * @author Vaibhav Maniar
	 */
	public void addUser(User user);

	/**
	 * Returns the user as per the username.
	 * 
	 * @param username
	 * @return
	 */
	public User searchUserByUserName(String username);
}
