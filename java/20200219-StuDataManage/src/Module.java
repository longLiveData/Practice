public class Module {

	private String moduleID;
	private String moduleName;
	private int moduleCredit;

	public Module(String id, String name, int credit) {
		moduleID = id;
		moduleName = name;
		moduleCredit = credit;
	}

	public String getID() {
		return moduleID;
	}

	public String getModuleName() {
		return moduleName;
	}

	public int getCredit() {
		return moduleCredit;
	}

	@Override
	public String toString() {
		return moduleID + ", " + moduleName + ", " + moduleCredit;
	}


}
