import java.util.Arrays;

public class ArraySort {

	/**
	 * with api
	 * @param arr array
	 */
	public static void sortWithAPI(int arr[]){
		Arrays.sort(arr);
		System.out.print("with api. The result is: ");
		for(int i=arr.length-1; i>=0; i--){
			System.out.print(arr[i]+" ");
		}
		System.out.print("\n");
	}

	/**
	 * without api
	 * @param arr array
	 */
	public static void sortWithoutAPI(int arr[]){
		System.out.print("without api. The result is: ");
		for (int i=0; i<arr.length; i++){
			for (int j=i+1; j<arr.length; j++){
				if(arr[i] < arr[j]){
					int k = arr[i];
					arr[i] = arr[j];
					arr[j] = k;
				}
			}
			System.out.print(arr[i]+" ");
		}
	}

	public static void main(String[] args){
		// change arr here
		System.out.println("2:");
		int arr[] = {1,2,3,4,5,6,7,8,9};
		sortWithAPI(arr);
		sortWithoutAPI(arr);
	}
}
