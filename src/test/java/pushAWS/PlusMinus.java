package pushAWS;

import java.text.DecimalFormat;
import java.util.Scanner;

public class PlusMinus {

	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int arr[] = new int[n];
        for(int arr_i=0; arr_i < n; arr_i++){
            arr[arr_i] = in.nextInt();
        }
		
		float neg = totalNegatives(arr);
		float pos = totalPositives(arr);
		float zer = totalZeros(arr);
		float b   = arr.length;
		
		DecimalFormat df = new DecimalFormat("#0.000000");
		System.out.println(df.format(pos / b));
		System.out.println(df.format(neg / b));
		System.out.println(df.format(zer / b));
	}
	
	public static int totalNegatives(int a[]) {
		int total = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] < 0) {
				total++;
			}
		}
		return total;
	}
	public static int totalZeros(int a[]) {
		int total = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == 0) {
				total++;
			}
		}
		return total;
	}
	public static int totalPositives(int a[]) {
		int total = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] > 0) {
				total++;
			}
		}
		return total;
	}

}