package me.practice.librarymgmtsystem.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.practice.librarymgmtsystem.model.Book;
import me.practice.librarymgmtsystem.model.BookMediaType;
import me.practice.librarymgmtsystem.model.User;
import me.practice.librarymgmtsystem.repository.service.RepositoryService;

public class BookDao implements IBookDao {

	private RepositoryService repositoryService = RepositoryService.getInstance();

	@Override
	public void addBook(Book book) {
		if (repositoryService.bookRepository.books.add(book)) {
			Set<Book> books1 = repositoryService.bookRepository.bookMapByTitle.getOrDefault(book.getTitle(),
					new HashSet<Book>());
			books1.add(book);
			repositoryService.bookRepository.bookMapByTitle.put(book.getTitle(), books1);

			Set<Book> books2 = repositoryService.bookRepository.bookMapByAuthor.getOrDefault(book.getAuthor(),
					new HashSet<Book>());
			books2.add(book);
			repositoryService.bookRepository.bookMapByAuthor.put(book.getAuthor(), books2);
			repositoryService.saveState();
		} else
			throw new RuntimeException("Book already exists.");
	}

	@Override
	public Book lendBook(Book book, User user) {
		if (!book.isAvailable() && BookMediaType.HardCopy.equals(book.getMediaType()))
			throw new RuntimeException(String.format("Book %s is not available.", book.getTitle()));

		if (repositoryService.userBookMapping.userBookMapping.getOrDefault(user, new ArrayList<Book>()).size() >= 10)
			throw new RuntimeException(String.format(
					"Already 10 books are assigned to user %s, no more books can be assigned.", user.getName()));

		book.setAvailable(false);

		Set<Book> booksByTitle = repositoryService.bookRepository.bookMapByTitle.getOrDefault(book.getAuthor(),
				new HashSet<>());
		booksByTitle.remove(book);
		booksByTitle.add(book);

		Set<Book> booksByAuthor = repositoryService.bookRepository.bookMapByAuthor.getOrDefault(book.getAuthor(),
				new HashSet<>());
		booksByAuthor.remove(book);
		booksByAuthor.add(book);

		repositoryService.bookRepository.books.remove(book);
		repositoryService.bookRepository.books.add(book);

		List<Book> assignedBooks = new ArrayList<>();
		assignedBooks.add(book);
		repositoryService.userBookMapping.userBookMapping.put(user, assignedBooks);
		repositoryService.saveState();
		return book;
	}

	@Override
	public List<Book> searchBookByTitle(String title) {
		repositoryService.resumeState();
		return new ArrayList<>(repositoryService.bookRepository.bookMapByTitle.getOrDefault(title, new HashSet<>()));
	}

	@Override
	public List<Book> searchBookByAuthor(String authorName) {
		repositoryService.resumeState();
		return new ArrayList<>(
				repositoryService.bookRepository.bookMapByAuthor.getOrDefault(authorName, new HashSet<>()));
	}

	@Override
	public void returnBook(Book book, User user) {
		book.setAvailable(true);

		Set<Book> booksByTitle = repositoryService.bookRepository.bookMapByTitle.getOrDefault(book.getTitle(),
				new HashSet<>());
		booksByTitle.remove(book);
		booksByTitle.add(book);
		repositoryService.bookRepository.bookMapByTitle.put(book.getTitle(), booksByTitle);

		Set<Book> booksByAuthor = repositoryService.bookRepository.bookMapByAuthor.getOrDefault(book.getAuthor(),
				new HashSet<>());
		booksByAuthor.remove(book);
		booksByAuthor.add(book);
		repositoryService.bookRepository.bookMapByTitle.put(book.getAuthor(), booksByAuthor);

		repositoryService.bookRepository.books.remove(book);
		repositoryService.bookRepository.books.add(book);

		repositoryService.userBookMapping.userBookMapping.getOrDefault(user, new ArrayList<>()).remove(book);
		repositoryService.saveState();
	}
}
