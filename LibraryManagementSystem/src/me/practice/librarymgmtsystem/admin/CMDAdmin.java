package me.practice.librarymgmtsystem.admin;

import java.util.List;
import java.util.Scanner;

import me.practice.librarymgmtsystem.admin.adaptor.CMDAdminAdaptor;
import me.practice.librarymgmtsystem.admin.adaptor.IAdminAdaptor;
import me.practice.librarymgmtsystem.model.Book;

public class CMDAdmin {

	private final IAdminAdaptor adminAdaptor = new CMDAdminAdaptor();
	public static void main(String[] args) {
		CMDAdmin admin = new CMDAdmin();

		try (Scanner sc = new Scanner(System.in)) {
			do {
				try {
					System.out.println("Please enter 1 to add book.");
					System.out.println("Please enter 2 to exit.");
					int i = -1;
					try {
						i = Integer.parseInt(sc.next());
					} catch(Exception e) {
						System.out.println("Invalid input, please try again.");
						continue;
					}
					switch (i) {
					case 1:
						admin.addBook(sc);
						break;
					case 2:
						System.exit(0);
					default:
						System.out.println("Invalid input, please try again");
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

	private final Book addBook(Scanner sc) {
		System.out.println("Please enter title: ");
		String title = sc.next();
		String bookType = getBookType(sc);
		String mediaType = getBookMediaType(sc);
		System.out.println("Please enter author name: ");
		String authorName = sc.next();
		return this.adminAdaptor.addBook(title, bookType, mediaType, authorName);
	}

	private final String getBookType(Scanner sc) {
		System.out.println("Please select book type: ");
		List<String> bookTypes = adminAdaptor.bookTypes();
		int index = 1;
		String bookTypeStr = "";
		for (String type : bookTypes) {
			System.out.println(index++ + ". " + type);
		}
		do {
			
			try {
				index = Integer.parseInt(sc.next());
			} catch(Exception e) {
				System.out.println("Invalid input, please try again.");
				continue;
			}
			
			if (index < 0 || index > bookTypes.size()) {
				System.out.println("Invalid input, please enter correct index.");
				continue;
			} else {
				bookTypeStr = bookTypes.get(index-1);
				break;
			}
		} while (true);
		return bookTypeStr;
	}

	private final String getBookMediaType(Scanner sc) {
		System.out.println("Please select book type: ");
		List<String> bookMediaTypes = adminAdaptor.bookMediaType();
		int index = 1;
		String bookMediaTypeStr = "";
		for (String type : bookMediaTypes) {
			System.out.println(index++ + ". " + type);
		}
		do {
			try {
				index = Integer.parseInt(sc.next());
			} catch(Exception e) {
				System.out.println("Invalid input, please try again.");
				continue;
			}
			if (index < 0 || index > bookMediaTypes.size()) {
				System.out.println("Invalid input, please enter correct index.");
				continue;
			} else {
				bookMediaTypeStr = bookMediaTypes.get(index-1);
				break;
			}
		} while (true);
		return bookMediaTypeStr;
	}
}
