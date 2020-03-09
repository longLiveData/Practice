package haha;

public class WeatherChartTester
{
	public static void main(String[] args)
	{
		//if the constant is defined correctly, this will compile
		System.out.println(WeatherChart.ABSOLUTE_ZERO);
		System.out.println("Expected: -273.15");

		double[][] values = {
				{5.0,  7.0,  9.5, 16.0, 23.0, 26.0, 28.3, 25.0, 22.0, 15.1, 9.5, 4.4},
				{0.6, -15.0, -25, -30, -50.3, -80, -75,   15.2, -30,  15.2, -11, -15},
				{-10.1, 1.4, 4.8, 8.9, 18.3,  17.0, 19.0, 19.0, 14.5, 9.7,   5,   2}
		};

		WeatherChart chart = new WeatherChart(values);
		System.out.println(chart.getLowestForMonth(0));
		System.out.println("Expected: -10.1");
		System.out.println(chart.getLowestForMonth(5));
		System.out.println("Expected: -80.0");
		System.out.println(chart.getLowestForMonth(7));
		System.out.println("Expected: 15.2");
		System.out.println(chart.getLowestForMonth(12));
		System.out.println("Expected: -273.15");
		System.out.println(chart.getLowestForMonth(-1));
		System.out.println("Expected: -273.15");

		System.out.println(chart.mildCount());
		System.out.println("Expected: 6");

		double[][] values2 = {
				{5, 7, 9.5, 16, 23, 26, 28.3, 25, 22, 24, 9.5, 4.4},
				{-10.1, -15, -25, -30, -50.3, -80, -75, -65, -30, -29, -11, -15},
				{0.6, 1.4, 4.8, 8.9, 14.3, 17, 19, 19, 14.5, 9.7, 5, 2},
				{27, 29, 18.5, 20, 19.01, 28.2, 19, 18.49, 23, 36, 26, 30.1}
		};

		chart = new WeatherChart(values2);
		System.out.println(chart.mildCount());
		System.out.println("Expected: 15");
	}
}