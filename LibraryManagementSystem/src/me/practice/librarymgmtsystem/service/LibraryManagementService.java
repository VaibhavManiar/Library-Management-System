package me.practice.librarymgmtsystem.service;

import java.util.Arrays;
import java.util.List;

import me.practice.librarymgmtsystem.dao.BookDao;
import me.practice.librarymgmtsystem.dao.IBookDao;
import me.practice.librarymgmtsystem.model.Book;
import me.practice.librarymgmtsystem.model.BookMediaType;
import me.practice.librarymgmtsystem.model.BookType;
import me.practice.librarymgmtsystem.model.User;

public class LibraryManagementService implements ILibraryManagementService {
	private IBookDao bookDao = new BookDao();
	private static int bookId = 1;

	@Override
	public void addBook(Book book) {
		book.setId(bookId++);
		bookDao.addBook(book);
	}

	@Override
	public List<Book> searchBooksByTitle(String title) {
		return bookDao.searchBookByTitle(title);
	}

	@Override
	public List<Book> searchBooksByAuthor(String author) {
		return bookDao.searchBookByAuthor(author);
	}

	@Override
	public Book lendBook(Book book, User user) {
		synchronized (book) {
			return bookDao.lendBook(book, user);
		}
	}

	@Override
	public void returnBook(Book book, User user) {
		synchronized (book) {
			bookDao.returnBook(book, user);
		}
	}
	
	@Override
	public List<BookType> getBookTypes() {
		return Arrays.asList(BookType.values());
	}
	
	@Override
	public BookType getBookType(String bookTypeStr) {
		return BookType.valueOf(bookTypeStr);
	}
	
	@Override
	public List<BookMediaType> getBookMediaTypes() {
		return Arrays.asList(BookMediaType.values());
	}
	
	@Override
	public BookMediaType getBookMediaType(String bookMediaType) {
		return BookMediaType.valueOf(bookMediaType);
	}

}
