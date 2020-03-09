import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DataManagementTest {

	@Test
	public void dataManagementTest() {

		DataManagement dm = new DataManagement();

		// add students, add student data
		UGStudent ugStu1 = new UGStudent(new Name("John", "Smith"));
		dm.registerStudent(ugStu1);
		StudentData stuData1 = new StudentData(16, new Date(2004-1900, 3-1, 28));
		dm.amendStudentData(ugStu1.getID(), stuData1);
		UGStudent ugStu2 = new UGStudent(new Name("David", "Tom"));
		dm.registerStudent(ugStu2);
		StudentData stuData2 = new StudentData(18, new Date(2002-1900, 7-1, 14));
		dm.amendStudentData(ugStu2.getID(), stuData2);

		PGRStudent pgrStu = new PGRStudent(new Name("Green", "Jerry"));
		pgrStu.setSupervisorName(new Name("Super", "Teacher"));
		StudentData stuData3 = new StudentData(22, new Date(1998-1900, 5-1, 2));
		dm.registerStudent(pgrStu);
		dm.amendStudentData(pgrStu.getID(), stuData3);

		PGTStudent pgtStu = new PGTStudent(new Name("James", "Bang"));
		StudentData stuData4 = new StudentData(24, new Date(1996-1900, 12-1, 18));
		dm.registerStudent(pgtStu);
		dm.amendStudentData(pgtStu.getID(), stuData4);

		assertEquals(2, dm.noOfStudents(Common.studentType.UG));
		assertEquals(1, dm.noOfStudents(Common.studentType.PGR));
		assertEquals(1, dm.noOfStudents(Common.studentType.PGT));

		// terminate student
		dm.terminateStudent(pgrStu.getID());
		assertEquals(0, dm.noOfStudents(Common.studentType.PGR));

		assertEquals(false, dm.issueSmartCard(ugStu1)); // false, age < 17
		assertEquals(true, dm.issueSmartCard(ugStu2));
		assertEquals(true, dm.issueSmartCard(pgrStu));
		assertEquals(false, dm.issueSmartCard(pgrStu)); // false, already exist


	}
}
