package me.practice.librarymgmtsystem.client;

import java.util.List;
import java.util.Scanner;

import me.practice.librarymgmtsystem.client.adaptor.CMDClientAdaptor;
import me.practice.librarymgmtsystem.client.adaptor.IClientAdaptor;
import me.practice.librarymgmtsystem.model.Book;
import me.practice.librarymgmtsystem.model.User;

public class CMDClient {
	private User currentLogedInUser = null;

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			IClientAdaptor clientAdaptor = new CMDClientAdaptor(sc);
			CMDClient client = new CMDClient();
			do {
				try {
					System.out.println("Please enter 1 to register.");
					System.out.println("Please enter 2 to login.");
					
					int i = -1;
					try {
						i = Integer.parseInt(sc.next());
					} catch(Exception e) {
						System.out.println("Invalid input, please try again.");
						continue;
					}
					
					if (i == 1) {
						client.currentLogedInUser = client.registerUser(sc, clientAdaptor);
						break;
					} else if (i == 2) {
						client.currentLogedInUser = client.login(sc, clientAdaptor);
						break;
					} else {
						System.out.println("Invalid input, please try again.");
						continue;
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
			} while (true);

			do {
				try {
					Book book = null;
					String title = null;
					String authorName = null;
					int i = -1;
					List<Book> books = null;
					System.out.println("Please enter 1 for listing books by author name.");
					System.out.println("Please enter 2 for listing books by title.");
					System.out.println("Please enter 3 to lend book by author name.");
					System.out.println("Please enter 4 to lend book by title.");
					System.out.println("Please enter 5 to return book.");
					
					try {
						i = Integer.parseInt(sc.next());
					} catch(Exception e) {
						System.out.println("Invalid input, please try again.");
						continue;
					}
					switch (i) {
					case 1:
						books = client.getBooksByAuthorName(sc, clientAdaptor);
						if (books == null || books.isEmpty())
							throw new RuntimeException("No books found with author name.");
						client.printAllBooks(books);
						break;

					case 2:
						books = client.getBooksByTitle(sc, clientAdaptor);
						if (books == null || books.isEmpty())
							throw new RuntimeException("No books found with title.");
						client.printAllBooks(books);
						break;
					case 3:
						System.out.println("Please enter author name: ");
						authorName = sc.next();
						book = clientAdaptor.lendBookByAuthorName(authorName, client.currentLogedInUser.getUsername());
						System.out.println(String.format("Book %s assigned to user %s", book,
								client.currentLogedInUser.getUsername()));
						break;
					case 4:
						System.out.println("Please enter title: ");
						title = sc.next();
						book = clientAdaptor.lendBookByTitle(title, client.currentLogedInUser.getUsername());
						System.out.println(String.format("Book %s assigned to user %s", book,
								client.currentLogedInUser.getUsername()));
						break;
					case 5:
						System.out.println("Please enter author name: ");
						authorName = sc.next();

						System.out.println("Please enter title: ");
						title = sc.next();
						clientAdaptor.returnBook(title, authorName, client.currentLogedInUser.getUsername());
						break;
					default:
						System.out.println("Invalid input, please try again.");
						continue;
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
			} while (true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private List<Book> getBooksByAuthorName(Scanner sc, IClientAdaptor clientAdaptor) {
		System.out.println("Please enter author name: ");
		String authorName = sc.next();
		return clientAdaptor.searchBookByAuthorName(authorName);
	}

	private List<Book> getBooksByTitle(Scanner sc, IClientAdaptor clientAdaptor) {
		System.out.println("Please enter title: ");
		String title = sc.next();
		return clientAdaptor.searchBookByTitle(title);
	}

	private void printAllBooks(List<Book> books) {
		for (Book book : books) {
			System.out.println(book + "\n");
		}
	}

	private User login(Scanner sc, IClientAdaptor clientAdaptor) {
		System.out.println("Please enter user name without any space: ");
		String username = sc.next();
		System.out.println("Please enter password without any space: ");
		String password = sc.next();
		return clientAdaptor.login(username, password);
	}

	private User registerUser(Scanner sc, IClientAdaptor clientAdaptor) {
		System.out.println("Please enter first name: ");
		String name = sc.next();
		System.out.println("Please enter last name: ");
		name = name + sc.next();
		System.out.println("Please enter user name without any space: ");
		String username = sc.next();
		System.out.println("Please enter password without any space: ");
		String password = sc.next();
		return clientAdaptor.registerUser(name, username, password);
	}
}
