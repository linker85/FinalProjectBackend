package pushAWS;

import java.util.LinkedList;
import java.util.Scanner;

public class ReducedString {

	@SuppressWarnings({ "resource" })
	public static void main(String[] args) {
        Scanner in  = new Scanner(System.in);
        String word = in.next();
        LinkedList<String> wordList = new LinkedList<String>();
        char[] wordArray = new char[word.length()];
        // Fill list
        for (int i = 0; i < wordArray.length; i++) {
        	wordList.add("" + word.charAt(i));
        }
        
        for (int i = 0; i < wordList.size(); i++) {
        	if ((i + 2) < (wordList.size() - 1))
        	System.out.println(wordList.get(i + 1) + " - " + wordList.get(i + 2) + ", i=" + i + ", i+1=" + (i + 1) + ", length=" + (wordList.size() - 1));
        	/*if (wordList.get(i + 1) == wordList.get(i + 2) && (i + 2) < wordList.size() - 1) {
        		System.out.println(wordList.get(i));
        	}*/
        }
        
	}

}