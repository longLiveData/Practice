public class Name {

	private String firstName = "";
	private String lastName = "";

	public Name(String fname, String lname) {
		firstName = fname;
		lastName = lname;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
}
