package edu.java.services;

import java.awt.Component;

import javax.swing.JOptionPane;

import edu.java.controller.JoinUserDaoImpl;
import edu.java.controller.TrainerDaoImpl;

public class LoginViewService {
	
	private final JoinUserDaoImpl joinDao = JoinUserDaoImpl.getInstance();
	private final TrainerDaoImpl trDao = TrainerDaoImpl.getInstance();
	// TODO: 회원 dao도 들고 와야 함.
	
	// 로그인 버튼 클릭 시 ID 있는지 ID에 맞는 비밀번호인지 확인
	public String loginUser(Component frame, String id, String password) {
		String result = "";
		
		if(!joinDao.loginUserCheck(id)) {
			JOptionPane.showMessageDialog(
					frame,
					"가입된 아이디가 없습니다.",
					"아이디 missing",
					JOptionPane.WARNING_MESSAGE
			);
		} else {
			String[] user = joinDao.loginUserEqualsIdAndPwd(id);
			if(!user[0].equals(password)) {
				JOptionPane.showMessageDialog(
						frame,
						"비밀번호가 틀렸습니다.",
						"비밀번호 오류",
						JOptionPane.WARNING_MESSAGE
				);
			} else {
				result = user[1];
			}
		}
		
		return result;
	}
}
