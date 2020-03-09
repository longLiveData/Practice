import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class PGTStudentTest {

	@Test
	public void test() {

		PGTStudent stu = new PGTStudent(new Name("John", "Smith"));

		StudentID sID = new StudentID();
		stu.setID(sID);

		StudentData stuData = new StudentData(18, new Date(2002-1900, 3-1, 28));
		stu.setData(stuData);

		assertEquals(5, stu.getID().toString().length());
		assertEquals(Common.studentType.PGT, stu.getStudentType());

		Module m = new Module("CSC8002", "Advanced Programming", 20);
		stu.addModule(m);
		assertEquals("CSC8002, Advanced Programming, 20", stu.listModules());

		assertEquals(false, stu.enoughCredits());

		assertEquals("John Smith", stu.getStudentName().toString());

		assertEquals(new Date(2002-1900, 3-1, 28), stu.getStudentData().getBirthDate());
	}
}
