package me.practice.librarymgmtsystem.client.adaptor;

import java.util.List;

import me.practice.librarymgmtsystem.model.Book;
import me.practice.librarymgmtsystem.model.User;

public interface IClientAdaptor {
	/**
	 * Registers the new user to the system.
	 * @param name
	 * @param username
	 * @param password
	 * @author Vaibhav Maniar
	 */
	public User registerUser(String name, String username, String password);
	
	/**
	 * Allows user to login.
	 * Returns the user object.
	 * @param username
	 * @param password
	 * @return
	 */
	public User login(String username, String password);
	
	/**
	 * Returns (or lend) the book to the user.
	 * If book media type is SoftCopy then multiple users can access it else only one user at a time.
	 * @param title
	 * @param username
	 * @return
	 * @author Vaibhav Maniar
	 */
	public Book lendBookByTitle(String title, String username);
	
	/**
	 * Returns (or lend) the book to the user.
	 * If book media type is SoftCopy then multiple users can access it else only one user at a time.
	 * @param authorName
	 * @param username
	 * @return
	 * @author Vaibhav Maniar
	 */
	public Book lendBookByAuthorName(String authorName, String username);
	
	/**
	 * Submits the book back again to the system.
	 * It is an assumption that only one book will be there of a title by an author.
	 * @param title
	 * @param authorName
	 * @param username
	 * @author Vaibhav Maniar
	 */
	public void returnBook(String title, String authorName, String username);
	
	/**
	 * Returns the list of books of the same title.
	 * @param title
	 * @return
	 * @author Vaibhav Maniar
	 */
	public List<Book> searchBookByTitle(String title);
	
	/**
	 * Returns the list of books by the same author.
	 * @param authorName
	 * @return
	 * @author Vaibhav Maniar
	 */
	public List<Book> searchBookByAuthorName(String authorName);
}
