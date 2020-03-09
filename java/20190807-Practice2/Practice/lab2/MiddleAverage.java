public class MiddleAverage {
    public static void main(String[] args) {
        // write your solution here
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        double sum = 0.0;
            int count = 0;
            
            int value = In.nextInt("Value: ");
            while (value != -1) {
                sum += value;
                count += 1;
                if (value < min) {
                    min = value;
                }
                if (value > max) {
                    max = value;
                }
                value = In.nextInt("Value: ");
            }
            if (count > 3) {
                double average = (sum-min-max) / (count-2);
                System.out.println("Middle Average = " + average);
            }
            
    }
}
