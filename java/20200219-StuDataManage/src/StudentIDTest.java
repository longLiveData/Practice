import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StudentIDTest {

	@Test
	public void studentIDTest() {
		StudentID sID = new StudentID();
		sID.setLetterPart();
		sID.setNumberPart();
		String letterPart = sID.getLetterPart();
		String numberPart = sID.getNumberPart();
		assertEquals(1, letterPart.length());
		assertEquals(4, numberPart.length());
	}

}
