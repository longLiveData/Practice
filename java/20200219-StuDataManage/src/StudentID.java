public class StudentID {

	private String letterPart = "";
	private String numberPart = "";

	StudentID() {
		setLetterPart();
		setNumberPart();
	}

	public void setLetterPart() {
		String chars = "abcdefghijklmnopqrstuvwxyz";
		letterPart = String.valueOf(chars.charAt((int)(Math.random() * 26)));
	}

	public void setNumberPart() {
		numberPart = String .valueOf(Math.round((Math.random()+1) * 1000));
	}

	public String getLetterPart() {
		return letterPart;
	}

	public String getNumberPart() {
		return numberPart;
	}

	public String toString() {
		return letterPart + numberPart;
	}

}
