package edu.java.controller;

public class ReplaceText {
	
	// 전화번호
	public static String inputPhoneNumber(String s) {
		String phone = "";
		
		if(s.length() <= 0) {
			return s;
		} else {
			String str = s.replaceAll("[^0-9]", "");
			
			System.out.println(str);
			
			if(str.length() < 4) {
				return str;
			} else if(str.length() < 7) {
				phone += str.substring(0, 3);
				phone += "-";
				phone += str.substring(3);
			} else if(str.length() < 11){
				phone += str.substring(0, 3);
		        phone += "-";
		        phone += str.substring(3, 3);
		        phone += "-";
			} else {
				phone += str.substring(0, 3);
		        phone += "-";
		        phone += str.substring(3, 4);
		        phone += "-";
		        phone += str.substring(7);
			}
			
		}
		
		return phone;
	}
}
