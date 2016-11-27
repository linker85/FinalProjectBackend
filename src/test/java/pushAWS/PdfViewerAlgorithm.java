package pushAWS;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class PdfViewerAlgorithm {

	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = 26;
        int h[] = new int[n];
        
        for(int h_i = 0; h_i < n; h_i++) {
            h[h_i] = in.nextInt();            
        }
        
        String word = in.next();
        
        int[] wordH = new int[word.length()];
        
        // Map character with height
        for (int i = 0; i < word.length(); i++) {
        	for (int j = 0; j < h.length; j++) {
        		if (word.charAt(i) == 'a' && j == 0) {
        			wordH[i] = h[j];
        		} else if (word.charAt(i) == 'b' && j == 1) {
        			wordH[i] = h[j];
        		} else if (word.charAt(i) == 'c' && j == 2) {
        			wordH[i] = h[j];
        		} else if (word.charAt(i) == 'd' && j == 3) {
        			wordH[i] = h[j];
        		} else if (word.charAt(i) == 'e' && j == 4) {
        			wordH[i] = h[j];
        		} else if (word.charAt(i) == 'f' && j == 5) {
        			wordH[i] = h[j];
        		} else if (word.charAt(i) == 'g' && j == 6) {
        			wordH[i] = h[j];
        		} else if (word.charAt(i) == 'h' && j == 7) {
        			wordH[i] = h[j];
        		} else if (word.charAt(i) == 'i' && j == 8) {
        			wordH[i] = h[j];
        		} else if (word.charAt(i) == 'j' && j == 9) {
        			wordH[i] = h[j];
        		} else if (word.charAt(i) == 'k' && j == 10) {
        			wordH[i] = h[j];
        		} else if (word.charAt(i) == 'l' && j == 11) {
        			wordH[i] = h[j];
        		} else if (word.charAt(i) == 'm' && j == 12) {
        			wordH[i] = h[j];
        		} else if (word.charAt(i) == 'n' && j == 13) {
        			wordH[i] = h[j];
        		} else if (word.charAt(i) == 'o' && j == 14) {
        			wordH[i] = h[j];
        		} else if (word.charAt(i) == 'p' && j == 15) {
        			wordH[i] = h[j];
        		} else if (word.charAt(i) == 'q' && j == 16) {
        			wordH[i] = h[j];
        		} else if (word.charAt(i) == 'r' && j == 17) {
        			wordH[i] = h[j];
        		} else if (word.charAt(i) == 's' && j == 18) {
        			wordH[i] = h[j];
        		} else if (word.charAt(i) == 't' && j == 19) {
        			wordH[i] = h[j];
        		} else if (word.charAt(i) == 'u' && j == 20) {
        			wordH[i] = h[j];
        		} else if (word.charAt(i) == 'v' && j == 21) {
        			wordH[i] = h[j];
        		} else if (word.charAt(i) == 'w' && j == 22) {
        			wordH[i] = h[j];
        		} else if (word.charAt(i) == 'x' && j == 23) {
        			wordH[i] = h[j];
        		} else if (word.charAt(i) == 'y' && j == 24) {
        			wordH[i] = h[j];
        		} else if (word.charAt(i) == 'z' && j == 25) {
        			wordH[i] = h[j];
        		}
        	}
        }
        
        // Sort height
        wordH = bubbleSort(wordH);
        
        // area = word.length * letterWidth * maxWordHeight
        int area = word.length() * 1 * wordH[wordH.length - 1];
        System.out.println(area);
        
	}
	
	public static int[] bubbleSort(int[] numArray) {
		int n    = numArray.length;
		int temp = 0;
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