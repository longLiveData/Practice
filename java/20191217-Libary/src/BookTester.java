package haha;

public class BookTester
{
	public static void main (String[] args)
	{
		Book  cookbook = new Book("The China Study Cookbook", 285);
		Book chinaStudy = new Book("The China Study",451);
		Book food = new Book("31-Day Food Revolution", 363);
		Book diet = new Book("Spectrum", 386);

		System.out.println(cookbook.nameInUpperCase());
		System.out.println("Expected: THE CHINA STUDY COOKBOOK");
		System.out.println(diet.nameInUpperCase());
		System.out.println("Expected: SPECTRUM");

		System.out.println(food.nonVowelCount());
		System.out.println("Expected: 14");
		System.out.println(diet.nonVowelCount());
		System.out.println("Expected: 6");

		System.out.println(cookbook.getLetter());
		System.out.println("Expected: k");
		System.out.println(food.getLetter());
		System.out.println("Expected: n");
		System.out.println(diet.getLetter());
		System.out.println("Expected: t");

//		Book[] myBooks = {
//				new Book("Be one with the Computer", 375),
//				new Book("Java Concepts", 325),
//				new Book("Code Complete", 250),
//				new Book("The Mythical Man-Month", 265),
//				new Book("Art of programming", 312)
//		};
//		Arrays.sort(myBooks);
//		for (Book b : myBooks)
//		{
//			System.out.println(b.getInfo());
//		}
//
//		Book[] myBooks = {
//				new Book("Java Concepts", 325),
//				new Book("The Mythical Man-Month", 265),
//				new Book("Code Complete", 250),
//				new Book("Art of programming", 325)
//		};
//		Arrays.sort(myBooks);
//		for (Book b : myBooks)
//		{
//			System.out.println(b.getInfo());
//		}
	}
}