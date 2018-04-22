package me.practice.librarymgmtsystem.service;

import java.util.List;

import me.practice.librarymgmtsystem.model.Book;
import me.practice.librarymgmtsystem.model.BookMediaType;
import me.practice.librarymgmtsystem.model.BookType;
import me.practice.librarymgmtsystem.model.User;

public interface ILibraryManagementService {
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
	public List<Book> searchBooksByTitle(String title);
	
	/**
	 * Returns the {@link List} of {@link Book} as per the author name.
	 * @param author
	 * @return
	 */
	public List<Book> searchBooksByAuthor(String author);
	
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
	
	/**
	 * Returns all the book types.
	 * @return
	 */
	public List<BookType> getBookTypes();
	
	/**
	 * Returns the book type as per the book type string.
	 * @param bookTypeStr
	 * @return
	 */
	public BookType getBookType(String bookTypeStr);
	
	/**
	 * Returns the book media types.
	 * @return
	 */
	public List<BookMediaType> getBookMediaTypes();
	
	/**
	 * Returns the book media type as per the book media string.
	 * @param bookMediaType
	 * @return
	 */
	public BookMediaType getBookMediaType(String bookMediaType);
}
