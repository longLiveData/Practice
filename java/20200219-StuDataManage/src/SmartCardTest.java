import org.junit.Test;

import java.text.DateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class SmartCardTest {

	@Test
	public void smartCardTest() {

		UGStudent stu = new UGStudent(new Name("John", "Smith"));
		StudentData stuData = new StudentData(18, new Date(2002 - 1900, 3 - 1, 28));
		stu.setData(stuData);

		SmartCard sc = new SmartCard(stu);
		SmartCardNumber scn = sc.createCardNumber(2018, 22);
		sc.setCardNumber(scn);

		DateFormat df1 = DateFormat.getDateInstance();
		assertEquals("John Smith", sc.getStudentName());
		assertEquals(4, sc.getExpiryDate().getYear() - new Date().getYear());

	}
}
