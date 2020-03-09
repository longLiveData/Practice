import java.util.Scanner;

public class StringPractice {

	/***
	 * 1.1
	 * @param str input string
	 */
	public static void severalCharsCount (String str){
		// define
		int spaceNum = 0;
		int letterNum = 0;
		int chineseNum = 0;
		int tabNum = 0;
		int newlineNum = 0;

		// judge
		if (null == str || str.equals("")) {
			System.out.println("empty string!");
			return;
		}
		for (int i = 0; i < str.length(); i++) {
			char tmp = str.charAt(i);
			if(Character.isSpaceChar(tmp)){
				spaceNum ++;
			} else if (String.valueOf(tmp).matches("[\u4e00-\u9fa5]")){
				chineseNum ++;
			} else if (Character.isLetter(tmp)){
				letterNum ++;
			} else if (tmp == '\t'){
				tabNum ++;
			} else if (tmp == '\n'){
				newlineNum ++;
			}
		}

		// print
		System.out.println("space:" + spaceNum);
		System.out.println("letter:" + letterNum);
		System.out.println("chinese:" + chineseNum);
		System.out.println("tab:" + tabNum);
		System.out.println("newline:" + newlineNum);
	}

	/**
	 * 1.2
	 * @param str string
	 */
	public static void isSymmetricString (String str){
		char strs[] = str.toCharArray();
		if (isSymmetric(strs, 0, strs.length -1,strs.length) == true) {
			System.out.println(str + " Yes!");
		} else {
			System.out.println(str + " No!");
		}
	}

	/**
	 * 1.2
	 * @param a char list
	 * @param low
	 * @param high
	 * @param length length of char list
	 * @return true/false
	 */
	public static boolean isSymmetric(char a[], int low,int high,int length){
		if(length == 1 || length == 0)
			return true;
		if (a[low] != a[high] || low >= high) {
			return false;
		}
		return isSymmetric(a, low + 1, high -1,length -2);
	}


	/**
	 * 1.3
	 * @param str input string
	 * @param n  offset
	 * @return  encryption string
	 */
	public static String strEncryption (String str, int n){
		String newStr = "";

		for (int i=0; i<str.length(); i++){
			char ch = str.charAt(i);
			int newChIndex = ch + n;
			// deal with cross-border situations 90 is 'Z'
			char newCh = (char)(newChIndex>90 ? newChIndex-26 : newChIndex);
			newStr += newCh;
		}

		return newStr;
	}

	/**
	 * 1.4
	 * @param  str input String
	 */
	public static void strCompress(String str){
		String comStr = "";
		str += " ";
		char ori = str.charAt(0);
		int count = 1;
		for (int i=1; i<str.length(); i++){
			char ch = str.charAt(i);
			if(ch == ori){
				count ++;
			} else {
				comStr += ori;
				ori = ch;
				if (count > 1) {
					comStr += String.valueOf(count);
					count = 1;
				}
			}
		}
		System.out.println(comStr);
	}

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		// 1.1
		System.out.println("1.1 please input string:");
		String str1 = sc.nextLine();
		severalCharsCount(str1);

		// 1.2
		System.out.println("1.2 please input string:");
		String str2 = sc.nextLine();
		isSymmetricString(str2);

		// 1.3
		System.out.println("1.3 please input string:");
		String str3 = sc.nextLine();
		System.out.println("please input offset:");
		int pos = sc.nextInt();
		String res = strEncryption(str3, pos);
		System.out.println("result is: " + res);

		// 1.4
		System.out.println("1.4 please input string:");
		String str4 = sc.nextLine();
		strCompress(str4);
	}
}







