public class CountVowels {
	public static void main(String[] args) {
            // write your solution here
            int vowel = 0;
            char c = In.nextChar("Character: ");
            while (c != '.') {
                if (c=='a' || c=='e' || c=='i' || c=='o' || c=='u'){
                    vowel += 1;
                }
                c = In.nextChar("Character: ");
            }
            System.out.println("Vowel count = " + vowel);
	}
}
