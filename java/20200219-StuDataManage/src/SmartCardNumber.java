public class SmartCardNumber {

	private String firstComponent;
	private String secondComponent;
	private String thirdComponent;

	public SmartCardNumber(Name stuName, int year, int count){
		firstComponent = stuName.getFirstName().substring(0, 1) + stuName.getLastName().substring(0, 1);
		secondComponent = String.valueOf(year);
		thirdComponent = String.valueOf(count);
	}

	public String toString() {
		return firstComponent + "-" + secondComponent + "-" + thirdComponent;
	}

}
