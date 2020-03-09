package haha;

public class WeatherChart {

	public double[][] temps;

	public static double ABSOLUTE_ZERO = -273.15;

	WeatherChart(double[][] values) {
		temps = values;
	}

	public double getLowestForMonth(int columnIndex) {
		if (columnIndex >= 12 || columnIndex < 0) {
			return ABSOLUTE_ZERO;
		}
		double lowest = ABSOLUTE_ZERO * -1;
		for (int i=0; i<temps.length; i++) {
			if (temps[i][columnIndex] < lowest) {
				lowest = temps[i][columnIndex];
			}
		}
		return lowest;
	}

	public int mildCount() {
		int count = 0;
		int x = temps.length;
		int y = temps[0].length;
		for (int i=0; i<x; i++){
			for (int j=0; j<y; j++) {
				if (temps[i][j] <= 28.2 && temps[i][j] >= 18.5) {
					count += 1;
				}
			}
		}
		return count;

	}
}