//package haha;
//
//import java.util.ArrayList;
//
//public class Bookshelf {
//
//	public ArrayList<Book> books;
//
//	Bookshelf() {
//		books = new ArrayList<>();
//	}
//
//	public void add(Book e){
//		books.add(e);
//	}
//
//	public String longest() {
//		int size = 0;
//		String name = "";
//		if (books.size() != 0) {
//			for (Book book: books) {
//				if (book.getName().length() > size) {
//					name = book.getName();
//					size = name.length();
//				}
//			}
//		}
//		return name;
//	}
//
//	public int bookCount() {
//		return books.size();
//	}
//
//}
