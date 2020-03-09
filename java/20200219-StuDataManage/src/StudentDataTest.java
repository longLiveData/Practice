import org.junit.Test;

import java.text.DateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class StudentDataTest {

	@Test
	public void studentTest() {
		StudentData sd = new StudentData(18, new Date(2019-1900, 2-1, 20));
		assertEquals(18, sd.getAge());
		DateFormat df1 = DateFormat.getDateInstance();
		assertEquals("2019-2-20", df1.format(sd.getBirthDate()));
	}

}
