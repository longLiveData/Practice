import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NameTest {

	@Test
	public void nameTest() {
		Name name = new Name("John", "Smith");
		assertEquals("John", name.getFirstName());
		assertEquals("Smith", name.getLastName());
		assertEquals("John Smith", name.toString());
	}
}