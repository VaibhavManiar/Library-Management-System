package me.practice.librarymgmtsystem.repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.practice.librarymgmtsystem.model.Book;
import me.practice.librarymgmtsystem.model.User;

public class UserBookMapping implements Serializable {
	private static final long serialVersionUID = -8352610430770671701L;
	public final Map<User,List<Book>> userBookMapping = new HashMap<>();
}
