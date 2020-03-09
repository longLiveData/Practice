import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SmartCardNumberTest {

	@Test
	public void studentTest() {
		Name stuName = new Name("John", "Smith");
		SmartCardNumber scn  = new SmartCardNumber(stuName, 2018, 10);
		assertEquals("JS-2018-10", scn.toString());
	}

}
