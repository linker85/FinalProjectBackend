package pushAWS;

import java.util.Scanner;

public class DiagonalDifference {

	/*
5
-10 3  0  5 -4
 2 -1  0  2 -8
 9 -2 -5  6  0
 9 -7  4  8 -2
 3  7  8 -5  0
 
 
 -10 -1 -5 +8 + 0 = -8
 -4  2  -5 -7 + 3 = -11
 
 R(*-1) - L(*-1)
 
 -8 - -11 = -19
 
	 R = 3
	 
	 
	 
	 
	 * */
	
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[][] = new int[n][n];
        for(int a_i=0; a_i < n; a_i++){
            for(int a_j=0; a_j < n; a_j++){
                a[a_i][a_j] = in.nextInt();
            }
        }
        
       int total = leftToRight(a) - rightLeft(a);
        if (total < 0) {
        	total *= -1;
        }
        System.out.println(total);
        
	}

	public static int rightLeft(int a[][]) {
		int total   = 0;
		int xBefore = -1;
		int j = 0;
        for(int xCurrent = a.length - 1; xCurrent >= 0; xCurrent--) {       	
            for(int yCurrent = 0; yCurrent < a[xCurrent].length; yCurrent++) {
            	
            	// The others
            	if (xCurrent < xBefore) {
            		j++;
            		//System.out.println("xCurrent=" + xCurrent + ", j=" + j);
            		total += a[xCurrent][j];
            	}
            	// Beggining
            	if (xCurrent == a.length - 1 && yCurrent == 0) {
            		//System.out.println("total=" + total + " + arr=" + a[x][y]);
            		total += a[xCurrent][yCurrent];
            	}
            	xBefore = xCurrent;
            }
            
        }
        //System.out.println("F=" + total);
        return total;
	}
	
	public static int leftToRight(int a[][]) {
		int total = 0;
        for(int x = 0; x < a.length; x++){
            for(int y = 0; y < a[x].length; y++) {
            	if (y == x) {
            		total += a[x][y];
            	}
            }
        }
        //System.out.println("F=" + total);
        return total;
	}

}