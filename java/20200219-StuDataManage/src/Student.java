interface Student{

	public void setID(StudentID sID);
	public void setData(StudentData sData);
	public Name getStudentName();
	public StudentData getStudentData();

	public StudentID getID();
	public Common.studentType getStudentType();
	public String listModules();
	public boolean enoughCredits();

}