package haha;

import java.util.Scanner;

public class StudentInfo {

	public static void main(String[] args) {
		System.out.print("What is your educational goal? ");
		Scanner in = new Scanner(System.in);
		String educationalGoal = in.nextLine();
		System.out.println(educationalGoal);
		System.out.println(educationalGoal.length());
		System.out.print("How many years have you been at SJSU? ");
		if(in.hasNextInt()){
			int years = Integer.parseInt(in.next());
			System.out.print("How much student debt do you have? ");
			if(in.hasNextDouble()) {
				double debt = Double.parseDouble(in.next());
				if (years != 0) {
					double average = debt / years;
					System.out.println(average + " per year");
				}
			} else {
				System.out.println("Not a double");
			}
		}else {
			System.out.println("Not an int");
		}

	}
}
