//package haha;
//
//public class BookshelfTester
//{
//	public static void main(String[] args)
//	{
//		Bookshelf myBooks = new Bookshelf();
//		myBooks.add	(new Book("Java Concepts", 325));
//		myBooks.add(new Book("Code Complete", 250));
//		myBooks.add(new Book("The Mythical Man-Month", 265));
//		myBooks.add(new Book("The art of programming", 312));
//
//		System.out.println(myBooks.longest());
//		System.out.println("Expected: Java Concepts");
//
//		System.out.println(myBooks.bookCount());
//		System.out.println("Expected: 4");
//
//		myBooks.add(new Book("Care and Feeding of a Programmer", 375));
//		System.out.println(myBooks.longest());
//		System.out.println("Expected: Care and Feeding of a Programmer");
//
//		System.out.println(myBooks.bookCount());
//		System.out.println("Expected: 5");
//
//		Bookshelf empty = new Bookshelf();
//		System.out.println("Empty: " + empty.longest());
//		System.out.println("Expected: ");
//	}
//}