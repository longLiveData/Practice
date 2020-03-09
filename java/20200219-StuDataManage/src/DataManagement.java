import java.util.ArrayList;
public class DataManagement {

	// students list to record all students
	public static ArrayList<Student> studentList = new ArrayList<>();
	public static ArrayList<String> studentIDList = new ArrayList<>();
	public static ArrayList<String> smartCardNumberList = new ArrayList<>();
	public static ArrayList<SmartCard> smartCardList = new ArrayList<>();

	// get student number by type
	public int noOfStudents(Common.studentType typeOfStudent){
		int num = 0;
		for(Student s: studentList) {
			if (s.getStudentType().equals(typeOfStudent)) {
				num += 1;
			}
		}
		return num;
	}

	//register a student
	public void registerStudent(Student student){
		// add student ID
		StudentID newID = new StudentID();
		newID.setLetterPart();
		newID.setNumberPart();
		while(studentIDList.indexOf(newID.toString()) >= 0) {
			newID.setLetterPart();
			newID.setNumberPart();
		}
		studentIDList.add(newID.toString());
		student.setID(newID);
		studentList.add(student);
	}

	//amend a studentData
	public void amendStudentData(StudentID studentID, StudentData studentData) {
		for (Student stu: studentList) {
			if (stu.getID().equals(studentID)) {
				stu.setData(studentData);
				return;
			}
		}
	}

	//terminate a student
	public void terminateStudent (StudentID studentID) {
		for (Student stu: studentList) {
			if ( stu.getID().equals(studentID)) {
				studentList.remove(stu);
				return;
			}
		}

	}

	public boolean issueSmartCard(Student stu) {
		// if already exist
		for (SmartCard sc: smartCardList) {
			if (sc.getStudentName().toString().equals(stu.getStudentName().toString())) {
				return false;
			}
		}

		// judge age
		if (stu.getStudentType().equals(Common.studentType.UG)) {
			if (stu.getStudentData().getAge() < 17) {
				return false;
			}
		} else {
			if (stu.getStudentData().getAge() < 20) {
				return false;
			}
		}

		SmartCard sc = new SmartCard(stu);

		// create a smart card number
		int count = 1;
		SmartCardNumber scn = sc.createCardNumber(2020, count);
		while (smartCardNumberList.indexOf(scn.toString()) >= 0) {
			count += 1;
			scn = sc.createCardNumber(2020, count);
		}

		// issue card
		sc.setCardNumber(scn);

		// record card
		smartCardNumberList.add(scn.toString());
		smartCardList.add(sc);
		return true;
	}
}
