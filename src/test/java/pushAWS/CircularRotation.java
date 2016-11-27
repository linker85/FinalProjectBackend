package pushAWS;

import java.util.Scanner;

public class CircularRotation {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int arraySize = in.nextInt();
        int nRotations = in.nextInt();
        int nQueries = in.nextInt();
        int[] a = new int[arraySize];
        for(int a_i=0; a_i < arraySize; a_i++){
            a[a_i] = in.nextInt();
        }
        int[] rotationsResult = new int[a.length];
        
        for (int i = 0; i < nRotations; i++) {
        	if (i == 0) {
        		rotationsResult = rotateArray(a);
        	} else {
        		rotationsResult = rotateArray(rotationsResult);
        	}
        }
        
        for(int a0 = 0; a0 < nQueries; a0++){
            int m = in.nextInt();
            System.out.println(rotationsResult[m]);
        }
    }
    
    public static int[] rotateArray(int[] a) {
    	int[] result = new int[a.length];
    	for (int i = 0; i < a.length; i++) {
    		if (i < a.length - 1) { // Not last
    			result[i + 1] = a[i];
    		} else { // Last
    			result[0] = a[a.length - 1];
    		}
    		
    	}
    	return result;
    }
    
}