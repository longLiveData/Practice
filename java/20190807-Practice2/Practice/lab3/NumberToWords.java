public class NumberToWords {
    public static void main(String[] args) {
        
        String[] names = {"zero", "one", "two", "three", "four", "five", 
            "six", "seven", "eight", "nine", "ten", "eleven", "twelve", 
            "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", 
            "eighteen", "nineteen"};
            
        String[] tensNames = {"", "", "twenty", "thirty", "forty", "fifty", 
            "sixty", "seventy", "eighty", "ninety" };

        
        System.out.print("Number: ");
        int number = In.nextInt();
        while (number != -1) {
            if (number < 20){
                System.out.println(names[number]);
            } else if (number < 100) {
                int tensDigit = number / 10;
                int onesDigit = number % 10;
                if (onesDigit == 0) {
                    System.out.println(tensNames[tensDigit]);
                } else {
                    System.out.println(tensNames[tensDigit] + " " + names[onesDigit]);
                }
            } else {
                int hundDigit = number / 100;
                int tensDigit = (number - hundDigit*100) / 10;
                int onesDigit = number % 10;
                
                int tensAndOnes = tensDigit*10 + onesDigit;
                if (tensAndOnes < 20 && tensAndOnes > 0){
                    System.out.println(names[hundDigit] + " hundred and " + names[tensAndOnes]);
                } else if (onesDigit == 0) {
                    if (tensDigit == 0) {
                        System.out.println(names[hundDigit] + " hundred");
                    } else {
                        System.out.println(names[hundDigit] + " hundred and " + tensNames[tensDigit]);
                    }
                    
                } else {
                    if (tensDigit == 0) {
                        System.out.println(names[hundDigit] + " hundred and " + names[onesDigit]);
                    } else {
                        System.out.println(names[hundDigit] + " hundred and " + tensNames[tensDigit] + " " + names[onesDigit]);
                    }
                }
            }
            System.out.print("Number: ");
            number = In.nextInt();
        }
    }
}
