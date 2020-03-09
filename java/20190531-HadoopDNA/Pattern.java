public class Pattern 
{
	private String start;	// The start tag to look for
	private String end;		// The end tag to search for after you have found the start tag
	
	/**
	 * Create a new Pattern object with the given 'start' and 'end' tags.
	 * 
	 * @param start	The start tag
	 * @param end	The end tag
	 */
	public Pattern(String start, String end)
	{
		this.start = start;
		this.end = end;
	}
	
	/**
	 * Returns a String representation of the two attributes in the form "start...end"
	 */
	public String toString()
	{
		return start + "..." + end;
	}

	/**
	 * 	Returns the start tag
	 */
	public String getStart() {

		return start;
	}

	/**
	 * Returns the end tag
	 */
	public String getEnd() { return end; }
	
}
