package me.practice.librarymgmtsystem.repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import me.practice.librarymgmtsystem.model.User;

public class UserRepository implements Serializable {
	public final Map<String, User> usersByUserName = new HashMap<>();
	private static final long serialVersionUID = 5420662208148332650L;

}
