import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ModuleTest {

	@Test
	public void muduleTest() {
		Module m = new Module("CSC8002", "Advanced Programming", 20);
		assertEquals("CSC8002", m.getID());
		assertEquals("Advanced Programming", m.getModuleName());
		assertEquals(20, m.getCredit());
		assertEquals("CSC8002, Advanced Programming, 20", m.toString());
	}

}
