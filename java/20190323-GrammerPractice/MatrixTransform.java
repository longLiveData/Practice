public class MatrixTransform {

	/**
	 * 3
	 * @param inArr inuut
	 * @param n size
	 * @return string after transpose
	 */
	public static String matrixTranspose (String inArr, int n){

		// split string to array
		String[] inArray = inArr.split(",");

		// Correspond two-dimensional position relation to one-dimensional index
		// Exchange two elements of the corresponding index
		for (int i=1; i<n; i++){
			for (int j=0; j<i; j++){
				int a = n*i+j;
				int b = n*j+i;
				String temp = inArray[a];
				inArray[a] = inArray[b];
				inArray[b] = temp;
			}
		}

		// Recombine the array as a string and return
		String res = inArray[0];
		for (int i=1; i<inArray.length; i++){
			res += ",";
			res += inArray[i];
		}
		return res;
	}

	public static void main(String[] args){
		String inArr = "1,2,3,4,5,6,7,8,9";
		int n = 3;
		String res = matrixTranspose(inArr, n);
		System.out.println("3:");
		System.out.println(" input is " + inArr);
		System.out.println("output is " + res);
	}
}
