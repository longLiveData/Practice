import java.util.ArrayList;
import java.util.Scanner;

public class Schools {

	private ArrayList<String> schoolName = new ArrayList<String>();
	private ArrayList<Integer> schoolFee = new ArrayList<Integer>();

	Schools(){
		Scanner sc = new Scanner(System.in);
		System.out.println("input LINE NUMBER you want to input");
		int num = Integer.valueOf(sc.nextLine());
		System.out.println("Enter the school name and fee separated by space. Once one line");
		int i= 0;
		while(i < num){
			String str = sc.nextLine();
			String[] strArr = str.split(" ");
			schoolName.add(strArr[0]);
			schoolFee.add(Integer.valueOf(strArr[1]));
			i++;
		}
	}

	private String getLongestNameSchool(){
		String res = schoolName.get(0);
		for(String school: schoolName){
			if (school.length() > res.length()){
				res = school;
			}
		}
		return res;
	}

	private String getMostExpensiveSchool(){
		int index = 0;
		int cost = schoolFee.get(0);
		for (int i = 0; i< schoolFee.size(); i++){
			if (schoolFee.get(i) > cost){
				index = i;
				cost = schoolFee.get(i);
			}
		}
		return schoolName.get(index);
	}

	private void getFeeBySchoolName(String name){
		// find the school
		for (int i = 0; i< schoolName.size(); i++){
			if (schoolName.get(i).equals(name)){
				int fee = schoolFee.get(i);
				System.out.println("The fee of " + name + " is " + String.valueOf(fee));
				return;
			}
		}
		System.out.println("NO result!");
	}


	public static void main(String[] args){

		// input school information while construct class
		Schools s = new Schools();

		String longName = s.getLongestNameSchool();
		System.out.println("The longest name school is: " + longName);
		String mostExpensive = s.getMostExpensiveSchool();
		System.out.println("The most expensive school is: " + mostExpensive);

		Scanner sc = new Scanner(System.in);
		System.out.println("input the school name you want to search: ");
		String name = sc.nextLine();
		s.getFeeBySchoolName(name);
	}
}