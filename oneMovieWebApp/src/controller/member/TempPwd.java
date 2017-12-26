package controller.member;

import java.util.Random;

public class TempPwd {
	
	public static String createTempPwd() {
		
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		
		String chars[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", 
						  "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", 
						  "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", 
						  "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		
		for(int i=0; i<=15; i++) {
			buffer.append(chars[random.nextInt(chars.length)]);
		}
		
		String tempPwd = buffer.toString();
				
		return tempPwd;
	}
	

}
