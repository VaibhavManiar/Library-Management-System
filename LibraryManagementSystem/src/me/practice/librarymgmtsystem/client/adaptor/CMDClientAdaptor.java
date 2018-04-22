package me.practice.librarymgmtsystem.client.adaptor;

import java.util.List;
import java.util.Scanner;

import me.practice.librarymgmtsystem.model.Book;
import me.practice.librarymgmtsystem.model.User;
import me.practice.librarymgmtsystem.repository.service.RepositoryService;
import me.practice.librarymgmtsystem.service.ILibraryManagementService;
import me.practice.librarymgmtsystem.service.LibraryManagementService;
import me.practice.librarymgmtsystem.user.service.IUserService;
import me.practice.librarymgmtsystem.user.service.UserService;

public class CMDClientAdaptor implements IClientAdaptor {

	private final IUserService userService = new UserService();
	private final ILibraryManagementService libraryManagementService = new LibraryManagementService();
	private final RepositoryService repositoryService = RepositoryService.getInstance();
	private Scanner sc = null;

	public CMDClientAdaptor(Scanner sc) {
		this.sc = sc;
		repositoryService.resumeState();
	}

	@Override
	public User registerUser(String name, String username, String password) {
		User user = new User(name, username, password);
		userService.addUser(user);
		return user;
	}
	
	@Override
	public User login(String username, String password) {
		User user = userService.searchUserByUserName(username);
		if(user ==  null)
			throw new RuntimeException("No user found with username : " + username);
		
		if(!user.getPassword().equals(password))
			throw new RuntimeException("Invalid username or password.");
		return user;
	}

	@Override
	public Book lendBookByTitle(String title, String username) {
		Book lendedbook = null;
		User user = userService.searchUserByUserName(username);
		List<Book> books = this.searchBookByTitle(title);
		if(books == null || books.isEmpty())
			throw new RuntimeException("No book found with title : " + title);
		lendedbook = this.getSelectedBook(books);
		return libraryManagementService.lendBook(lendedbook, user);
	}

	@Override
	public Book lendBookByAuthorName(String authorName, String username) {
		Book lendedbook = null;
		User user = userService.searchUserByUserName(username);
		List<Book> books = this.searchBookByAuthorName(authorName);
		if(books == null || books.isEmpty())
			throw new RuntimeException("No book found with author name : " + authorName);
		lendedbook = this.getSelectedBook(books);
		return libraryManagementService.lendBook(lendedbook, user);
	}

	@Override
	public void returnBook(String title, String authorName, String username) {
		User user = userService.searchUserByUserName(username);
		Book book = new Book(title, authorName);
		libraryManagementService.returnBook(book, user);
	}

	@Override
	public List<Book> searchBookByTitle(String title) {
		return libraryManagementService.searchBooksByTitle(title);
	}

	@Override
	public List<Book> searchBookByAuthorName(String authorName) {
		return libraryManagementService.searchBooksByAuthor(authorName);
	}

	private Book getSelectedBook(List<Book> books) {
		System.out.println("Please select book from the below list : ");
		int index = 1;
		Book lendedbook = null;
		for (Book book : books) {
			System.out.println(index++ + ". " + book.toString() + "\n");
		}
		int bookNumber = -1;
		do {
			System.out.println("Please enter book number : ");
			
			try {
				bookNumber = Integer.parseInt(sc.next());
			} catch(Exception e) {
				System.out.println("Invalid input, please try again.");
				continue;
			}
			
			if (bookNumber > index && bookNumber <= 0)
				throw new RuntimeException("Incorrect book number entered.");
			else {
				lendedbook = books.get(bookNumber - 1);
				break;
			}
		} while (true);
		return lendedbook;
	}

}
