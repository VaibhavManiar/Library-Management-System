package me.practice.librarymgmtsystem.user.dao;

import me.practice.librarymgmtsystem.model.User;
import me.practice.librarymgmtsystem.repository.service.RepositoryService;

public class UserDao implements IUserDao {

	RepositoryService repositoryService = RepositoryService.getInstance();

	@Override
	public void addUser(User user) {
		if (repositoryService.userRepository.usersByUserName.containsKey(user.getUsername())) {
			throw new RuntimeException("User name already exists, please try some other username");
		}
		repositoryService.userRepository.usersByUserName.put(user.getUsername(), user);
		repositoryService.saveState();
	}

	@Override
	public User searchUserByUserName(String username) {
		return repositoryService.userRepository.usersByUserName.get(username);
	}

}
