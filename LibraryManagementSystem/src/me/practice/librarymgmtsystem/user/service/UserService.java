package me.practice.librarymgmtsystem.user.service;

import me.practice.librarymgmtsystem.model.User;
import me.practice.librarymgmtsystem.user.dao.IUserDao;
import me.practice.librarymgmtsystem.user.dao.UserDao;

public class UserService implements IUserService {
	IUserDao userDao = new UserDao();
	private static int userId = 1;

	@Override
	public void addUser(User user) {
		synchronized (user) {
			user.setId(userId++);
			userDao.addUser(user);
		}
	}

	@Override
	public User searchUserByUserName(String username) {
		return userDao.searchUserByUserName(username);
	}
}
