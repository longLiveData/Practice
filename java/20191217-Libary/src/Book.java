package haha;

import java.util.Random;
/**
 * Models a book with a name and pages
 */
public class Book
{
	private String name;
	private int pages;
	private Random random;

	/**
	 * Constructs a Book with the given information
	 * @param theName the name of this Book
	 */
	public Book(String theName,  int numberOfPages)
	{
		name = theName;
		pages = numberOfPages;
		random = new Random(876543219);
	}

	/**
	 * Gets the name of this Book
	 * @return the name of this Book
	 */
	public String getname()
	{
		return name;
	}

	/**
	 * Gets the number of pages in this Book
	 * @return the number of pages in this Book
	 */
	public int getPages()
	{
		return pages;
	}

	/**
	 * Gets information about this Book
	 * @return information about this Book
	 */
	public String getInfo()
	{
		String s = "name: " + name + " numberOfPages: " + pages;
		return s;
	}

	public String nameInUpperCase()
	{
		return name.toUpperCase();
	}

	public int nonVowelCount()
	{
		int count = 0;
		String vowel = "aeiouAEIOU";
		for (int i=0; i<name.length(); i++) {
			if (! vowel.contains(name.substring(i, i+1))){
				count += 1;
			}
		}
		return count;
	}

	public String getLetter()
	{
		int value = random.nextInt(name.length());
		return name.substring(value, value+1);
	}
}