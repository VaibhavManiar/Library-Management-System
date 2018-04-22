package me.practice.librarymgmtsystem.model;

import java.io.Serializable;
import java.util.Set;

public class Book implements Serializable {

	private static final long serialVersionUID = 281300094090230305L;
	private int id;
	private String title;
	private Set<BookType> bookType;
	private BookMediaType mediaType;
	private boolean isAvailable;
	private String author;

	public Book() {
	}

	public Book(String title, String author) {
		this.title = title;
		this.author = author;
	}

	public Book(int id, String title, Set<BookType> bookType, BookMediaType mediaType, String author) {
		super();
		this.id = id;
		this.title = title;
		this.bookType = bookType;
		this.mediaType = mediaType;
		this.isAvailable = true;
		this.author = author;
	}

	public Book(String title, Set<BookType> bookType, BookMediaType mediaType, String author) {
		super();
		this.title = title;
		this.bookType = bookType;
		this.mediaType = mediaType;
		this.isAvailable = true;
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}

	public Set<BookType> getBookType() {
		return bookType;
	}

	public void setBookType(Set<BookType> bookType) {
		this.bookType = bookType;
	}

	public BookMediaType getMediaType() {
		return mediaType;
	}

	public void setMediaType(BookMediaType mediaType) {
		this.mediaType = mediaType;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", bookType=" + bookType + ", mediaType=" + mediaType
				+ ", isAvailable=" + isAvailable + ", author=" + author + "]";
	}

}
