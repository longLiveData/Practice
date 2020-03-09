import java.util.Date;

public class StudentData {

	private int age;
	private Date birthDate;

	public StudentData(int cAge, Date bDate) {
		age = cAge;
		birthDate = bDate;
	}

	public int getAge() {
		return age;
	}

	public Date getBirthDate() {
		return birthDate;
	}

}
