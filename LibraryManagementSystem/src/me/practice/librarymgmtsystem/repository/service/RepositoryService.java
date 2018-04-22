package me.practice.librarymgmtsystem.repository.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import me.practice.librarymgmtsystem.repository.BookRepository;
import me.practice.librarymgmtsystem.repository.UserBookMapping;
import me.practice.librarymgmtsystem.repository.UserRepository;

public class RepositoryService implements IRepositoryService {

	private final static String USER_REPO_FILE = "./userRepository.repo";
	private final static String BOOK_REPO_FILE = "./bookRepository.repo";
	private final static String USER_BOOK_MAPPING_REPO_FILE = "./userBookMappingRepository.repo";

	public UserRepository userRepository = null;
	public BookRepository bookRepository = null;
	public UserBookMapping userBookMapping = null;

	private static RepositoryService instance = null;

	private RepositoryService() {
	}

	public static final RepositoryService getInstance() {
		if (instance == null)
			instance = new RepositoryService();

		return instance;
	}

	@Override
	public void saveState() {
		this.saveUserState();
		this.saveBookState();
		this.saveUserBookMappingState();
	}

	@Override
	public void resumeState() {
		bookRepository = this.resumeBookState();
		userRepository = this.resumeUserState();
		userBookMapping = this.resumeUserBookMappingState();
	}

	private void saveUserState() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_REPO_FILE))) {
			oos.writeObject(userRepository);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveBookState() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOK_REPO_FILE))) {
			oos.writeObject(bookRepository);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveUserBookMappingState() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_BOOK_MAPPING_REPO_FILE))) {
			oos.writeObject(userBookMapping);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private UserRepository resumeUserState() {
		UserRepository obj = null;
		File file = new File(USER_REPO_FILE);
		if (file.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
				obj = (UserRepository) ois.readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		} else
			obj = new UserRepository();
		return obj;
	}

	private BookRepository resumeBookState() {
		BookRepository obj = null;
		File file = new File(BOOK_REPO_FILE);
		if (file.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
				obj = (BookRepository) ois.readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		} else
			obj = new BookRepository();
		return obj;
	}

	private UserBookMapping resumeUserBookMappingState() {
		UserBookMapping obj = null;
		File file = new File(USER_BOOK_MAPPING_REPO_FILE);
		if (file.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
				obj = (UserBookMapping) ois.readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		} else
			obj = new UserBookMapping();
		return obj;
	}
}
