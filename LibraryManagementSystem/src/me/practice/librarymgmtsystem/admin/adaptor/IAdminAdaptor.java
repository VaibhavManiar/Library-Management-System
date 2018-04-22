package me.practice.librarymgmtsystem.admin.adaptor;

import java.util.List;

import me.practice.librarymgmtsystem.model.Book;

public interface IAdminAdaptor {

	/**
	 * Add the book to the Library Management System.
	 * 
	 * @param title
	 * @param bookType
	 * @param mediaType
	 * @param author
	 * @return
	 */
	public Book addBook(String title, String bookType, String mediaType, String author);
	
	public List<String> bookTypes();
	
	public List<String> bookMediaType();
}
