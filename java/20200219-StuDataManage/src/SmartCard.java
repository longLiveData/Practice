import java.util.Calendar;
import java.util.Date;

public class SmartCard {

	private Student student;
	private Date birthDate;
	private SmartCardNumber smartCardNumber;
	private Date issueDate;
	private Date expiryDate;

	SmartCard(Student stu) {
		student = stu;
		birthDate = stu.getStudentData().getBirthDate();
	}

	public SmartCardNumber createCardNumber(int year, int count) {
		return new SmartCardNumber(student.getStudentName(), year, count);
	}

	public void setCardNumber(SmartCardNumber scn) {
		smartCardNumber = scn;
		issueDate = new Date();
		setExpiryDate();
	}

	public String getStudentName() {
		return student.getStudentName().toString();
	}

	private void setExpiryDate() {
		int addYear;
		if (student.getStudentType().equals(Common.studentType.UG)) {
			addYear = 4;
		} else if (student.getStudentType().equals(Common.studentType.PGT)) {
			addYear = 2;
		} else {
			addYear = 5;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(issueDate);
		cal.add(Calendar.YEAR, addYear); // add years
		expiryDate = cal.getTime();
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

}
