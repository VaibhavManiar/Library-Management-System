package me.practice.librarymgmtsystem.admin.adaptor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.practice.librarymgmtsystem.model.Book;
import me.practice.librarymgmtsystem.model.BookMediaType;
import me.practice.librarymgmtsystem.model.BookType;
import me.practice.librarymgmtsystem.repository.service.IRepositoryService;
import me.practice.librarymgmtsystem.repository.service.RepositoryService;
import me.practice.librarymgmtsystem.service.ILibraryManagementService;
import me.practice.librarymgmtsystem.service.LibraryManagementService;

public class CMDAdminAdaptor implements IAdminAdaptor {

	private final ILibraryManagementService libraryManagementService = new LibraryManagementService();
	private final IRepositoryService repositoryService = RepositoryService.getInstance();
	public CMDAdminAdaptor() {
		repositoryService.resumeState();
	}

	@Override
	public Book addBook(String title, String bookType, String mediaType, String author) {
		Set<BookType> bookTypes = new HashSet<>();
		bookTypes.add(libraryManagementService.getBookType(bookType));
		BookMediaType bookMediaType = libraryManagementService.getBookMediaType(mediaType);
		Book book = new Book(title, bookTypes, bookMediaType, author);
		libraryManagementService.addBook(book);
		return book;
	}

	public List<String> bookTypes() {
		List<String> bookTypes = new ArrayList<>();

		for (BookType type : libraryManagementService.getBookTypes()) {
			bookTypes.add(type.name());
		}
		return bookTypes;
	}

	public List<String> bookMediaType() {
		List<String> bookMediaTypes = new ArrayList<>();
		for (BookMediaType type : libraryManagementService.getBookMediaTypes()) {
			bookMediaTypes.add(type.name());
		}
		return bookMediaTypes;
	}

}
