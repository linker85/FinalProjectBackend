package pushAWS;

import java.util.Scanner;

public class MinMax {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long a = in.nextLong();
        long b = in.nextLong();
        long c = in.nextLong();
        long d = in.nextLong();
        long e = in.nextLong();
        
        long nA = b + c + d + e;
        long nB = a + c + d + e;
        long nC = a + b + d + e;
        long nD = a + b + c + e;
        long nE = a + b + c + d;
        
        long [] arr1 = {nA, nB, nC, nD, nE};
        long [] arr2 = bubbleSort(arr1);
        System.out.println(arr2[0]);
        System.out.println(arr2[arr2.length - 1]);        
    }
    
    public static long [] bubbleSort(long[] numArray) {
        int n = numArray.length;
        long temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (numArray[j - 1] > numArray[j]) {
                    temp = numArray[j - 1];
                    numArray[j - 1] = numArray[j];
                    numArray[j] = temp;
                }
            }
        }
        return numArray;
    }

}