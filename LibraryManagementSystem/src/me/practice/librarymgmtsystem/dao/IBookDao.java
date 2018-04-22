package me.practice.librarymgmtsystem.dao;

import java.util.List;

import me.practice.librarymgmtsystem.model.Book;
import me.practice.librarymgmtsystem.model.User;

public interface IBookDao {
	/**
	 * Adds the book to the book list
	 * @param book
	 * @author Vaibhav Maniar
	 */
	public void addBook(Book book);
	
	/**
	 * Returns the {@link List} of {@link Book} as per the title.
	 * @param title
	 * @return
	 */
	public List<Book> searchBookByTitle(String title);
	
	/**
	 * Returns the {@link List} of {@link Book} as per the author name.
	 * @param author
	 * @return
	 */
	public List<Book> searchBookByAuthor(String authorName);
	
	/**
	 * Returns the assigns the book to the user.
	 * @param bookName
	 * @return
	 */
	public Book lendBook(Book book, User user);
	
	/**
	 * Submits the book back to the library.
	 * @param book
	 */
	public void returnBook(Book book, User user);
}
