//package haha;
//
//import java.util.ArrayList;
//
//public class Library {
//
//	public ArrayList<Book> books;
//
//	Library(Book[] Booka){
//		books = new ArrayList<>();
//		for (int i=0; i<Booka.length; i++) {
//			books.add(Booka[i]);
//		}
//	}
//
//	public double getAverageLength() {
//		double sum = 0;
//		double average = 0.0;
//		if (books.size() != 0) {
//			for (Book book: books) {
//				sum += book.getPages();
//			}
//			average = sum / books.size();
//		}
//		return average;
//	}
//
//	public ArrayList<String> nameList() {
//		ArrayList<String> result = new ArrayList<>();
//		for (Book book: books) {
//			char start = book.getName().charAt(0);
//			if (start == 'A' || start == 'B' || start == 'C') {
//				result.add(book.getName());
//			}
//		}
//		return result;
//	}
//}
