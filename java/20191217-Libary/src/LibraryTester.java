//package haha;
//
//public class LibraryTester
//{
//	public static void main(String[] args)
//	{
//		Book[] myBooks = {
//				new Book("Java Concepts", 325),
//				new Book("Code Complete", 250),
//				new Book("The Mythical Man-Month", 265),
//				new Book("Art of programming", 312)
//		};
//		Library lib = new Library(myBooks);
//
//		System.out.println(lib.getAverageLength());
//		System.out.println("Expected: 288.0");
//		System.out.println(lib.nameList());
//		System.out.println("Expected: [Code Complete, Art of programming]");
//
//		Book[] myBooks2 = {
//				new Book("Be one with the Computer", 375),
//				new Book("Java Concepts", 325),
//				new Book("Code Complete", 250),
//				new Book("The Mythical Man-Month", 265),
//				new Book("Art of programming", 312)
//		};
//		Library lib2 = new Library(myBooks2);
//
//		System.out.println(lib2.getAverageLength());
//		System.out.println("Expected: 305.4");
//		System.out.println(lib2.nameList());
//		System.out.println("Expected: [Be one with the Computer, Code Complete, Art of programming]");
//
//		Library empty = new Library(new Book[0]);
//		System.out.println("Empty: " + empty.getAverageLength());
//		System.out.println("Expected: 0.0");
//	}
//}