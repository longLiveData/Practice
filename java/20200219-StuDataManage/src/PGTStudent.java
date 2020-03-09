import java.util.ArrayList;

public class PGTStudent implements Student {

	private StudentID studentID;
	private Common.studentType studentType = Common.studentType.PGT;
	private Name studentName;
	private StudentData studentData;
	private ArrayList<Module> moduleList = new ArrayList<>();

	PGTStudent(Name name) {
		studentName = name;
	}

	public void addModule(Module m) {
		moduleList.add(m);
	}

	@Override
	public void setID(StudentID sID) {
		studentID = sID;
	}

	@Override
	public void setData(StudentData sData) {
		studentData = sData;
	}

	public StudentID getID() {
		return studentID;
	}

	@Override
	public Common.studentType getStudentType() {
		return studentType;
	}

	@Override
	public String listModules() {
		String res = "";
		for (Module m: moduleList) {
			res += m.toString();
		}
		return res;
	}

	@Override
	public boolean enoughCredits() {
		int credit = 0;
		for (Module m: moduleList) {
			credit += m.getCredit();
		}
		if (credit >= Common.PGT_CREDIT) {
			return true;
		}
		return false;
	}

	public Name getStudentName() {
		return studentName;
	}

	@Override
	public StudentData getStudentData() {
		return studentData;
	}

}
