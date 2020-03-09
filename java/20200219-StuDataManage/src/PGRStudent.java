import java.util.ArrayList;

public class PGRStudent implements Student{

	private StudentID studentID;
	private Common.studentType studentType = Common.studentType.PGR;
	private Name studentName;
	private Name supervisorName;
	private StudentData studentData;
	private ArrayList<Module> moduleList = new ArrayList<>();

	PGRStudent(Name name) {
		studentName = name;
	}

	public void setSupervisorName(Name sname) {
		supervisorName = sname;
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

	@Override
	public Name getStudentName() {
		return studentName;
	}

	@Override
	public StudentData getStudentData() {
		return studentData;
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
		return true;
	}

	public Name getSupervisor() {
		return supervisorName;
	}

}
