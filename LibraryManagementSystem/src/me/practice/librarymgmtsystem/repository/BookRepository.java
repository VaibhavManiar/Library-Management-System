package me.practice.librarymgmtsystem.repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import me.practice.librarymgmtsystem.model.Book;

public class BookRepository implements Serializable {
	public final Set<Book> books = new HashSet<>();
	public final Map<String, Set<Book>> bookMapByTitle = new HashMap<>();
	public final Map<String, Set<Book>> bookMapByAuthor = new HashMap<>();
	private static final long serialVersionUID = -5164919529584550095L;

}
