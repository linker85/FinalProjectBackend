package pushAWS;

import java.util.Scanner;

public class CircularRotation2 {
	public static void main(String[] args) {
	    Scanner in = new Scanner(System.in);
	    int arraySize, nRotations ,nQueries;
	    arraySize = in.nextInt();
	    nRotations = in.nextInt();
	    nQueries = in.nextInt();
	    
	    // circular rotation => (i + nRotations) MOD arr.size

	    int[] arr = new int[arraySize];
	    for (int i = 0; i < arraySize; i++) {
	        arr[(i+nRotations) % arraySize] = in.nextInt();
	    }
	    for (int i=0; i<nQueries; i++) {
	        System.out.println(arr[in.nextInt()]);
	    }
	}
}