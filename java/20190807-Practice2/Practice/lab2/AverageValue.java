public class AverageValue {
    public static void main(String[] args) {
        // write your solution here
        double sum = 0.0;
        int count = 0;
        int value = In.nextInt("Value: ");
        while (value != -1) {
            sum += value;
            count += 1;
            value = In.nextInt("Value: ");
        }
        if (count > 1) {
            double average = sum / count;
            System.out.println("Average = " + average);
        }
    }
}
