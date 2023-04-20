package edu.java.services;

import java.awt.Component;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TextFilter {
	
	// 전화번호
	public static void checkPhone(Component frame, KeyEvent e) {
		JTextField field = (JTextField) e.getSource();
		String before = "";
		String after = field.getText().replaceAll("-", "");
		int len = after.length() + 1;
		char c = e.getKeyChar();
		System.out.println("len = " + len);
		
		// 숫자가 아니라면 날림.
		if(!((c >= '0') && (c <= '9') ||
			(c == KeyEvent.VK_BACK_SPACE) ||
			(c == KeyEvent.VK_DELETE))) {
			e.consume();
			JOptionPane.showMessageDialog(frame, "숫자로 입력해주세요.");
			return;
		}
		
		// 핸드폰 번호로 끊음.
		// 전화번호 길이 11자리를 넘기면 날림.
		if(len != 0 && len > 3 && len < 8) {
			before += after.substring(0, 3);
			before += "-";
			before += after.substring(3, len - 1);
			field.setText(before);
			System.out.println(before);
		} else if(len >= 7 && len <= 11) {
			before += after.substring(0, 3);
			before += "-";
			before += after.substring(3, 7);
			before += "-";
			before += after.substring(7, len - 1);
			field.setText(before);
			System.out.println(before);
		} else if(len > 11) {
			e.consume();
		}
		
	}
	
	
	// 생년월일
	public static void checkBirth(Component frame, KeyEvent e) {
		JTextField field = (JTextField) e.getSource();
		String before = "";
		String after = field.getText().replaceAll("-", "");
		int len = after.length() + 1;
		char c = e.getKeyChar();
		
		// 숫자가 아니라면 날림.
		if(!((c >= '0') && (c <= '9') ||
			(c == KeyEvent.VK_BACK_SPACE) ||
			(c == KeyEvent.VK_DELETE))) {
			e.consume();
			JOptionPane.showMessageDialog(frame, "숫자로 입력해주세요.");
		}
		
		// 생년월일 8자리
		System.out.println(len);
		if(len != 0 && len > 4 && len < 7) {
			before += after.substring(0, 4);
			before += "-";
			before += after.substring(4, len - 1);
			field.setText(before);
			System.out.println(before);
		} else if(len >= 6 && len < 9) {
			before += after.substring(0, 4);
			before += "-";
			before += after.substring(4, 6);
			before += "-";
			before += after.substring(6, len - 1);
			field.setText(before);
		} else if(len >= 9) {
			e.consume();
		}
		
	}
	
	public static void checkInt(Component frame, KeyEvent e) {
		char c = e.getKeyChar();
		
		// 숫자가 아니라면 날림.
		if(!((c >= '0') && (c <= '9') ||
			(c == KeyEvent.VK_BACK_SPACE) ||
			(c == KeyEvent.VK_DELETE))) {
			e.consume();
			JOptionPane.showMessageDialog(frame, "숫자로 입력해주세요.");
		}
	}
}
