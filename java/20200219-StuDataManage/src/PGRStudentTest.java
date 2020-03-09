import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class PGRStudentTest {

	@Test
	public void test() {

		PGRStudent stu = new PGRStudent(new Name("John", "Smith"));

		StudentID sID = new StudentID();
		stu.setID(sID);

		StudentData stuData = new StudentData(18, new Date(2002 - 1900, 3 - 1, 28));
		stu.setData(stuData);

		assertEquals(5, stu.getID().toString().length());
		assertEquals(Common.studentType.PGR, stu.getStudentType());

		Module m = new Module("CSC8002", "Advanced Programming", 20);
		stu.addModule(m);
		assertEquals("CSC8002, Advanced Programming, 20", stu.listModules());

		assertEquals(true, stu.enoughCredits());

		assertEquals("John Smith", stu.getStudentName().toString());

		assertEquals(new Date(2002 - 1900, 3 - 1, 28), stu.getStudentData().getBirthDate());

		stu.setSupervisorName(new Name("Teach", "Master"));
		assertEquals(new Name("Teach", "Master").toString(), stu.getSupervisor().toString());

	}
}
