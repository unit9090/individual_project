package edu.java.services;

import java.awt.Component;

import javax.swing.JOptionPane;

import edu.java.controller.JoinUserDaoImpl;
import edu.java.controller.TrainerDaoImpl;
import edu.java.model.JoinUser;
import edu.java.model.Trainer;

public class JoinViewService {
	
	private final JoinUserDaoImpl joinDao = JoinUserDaoImpl.getInstance();
	private final TrainerDaoImpl trDao = TrainerDaoImpl.getInstance();
	
	// 중복확인
	public boolean checkIdDouble(Component frame, String id, String division) {
		if(!joinDao.doubleCheck(id, division)) {
			JOptionPane.showMessageDialog(
					frame,
					"ID가 중복됩니다. 다시 설정해주세요.",
					"아이디 중복 확인",
					JOptionPane.WARNING_MESSAGE
			);
			
			return false;
		}
		
		JOptionPane.showMessageDialog(
				frame,
				"사용 가능한 ID 입니다.",
				"아이디 중복 확인",
				JOptionPane.INFORMATION_MESSAGE
		);
		
		return true;
	}
	
	// 가입 버튼
	public int joinUserCheck(Component frame, JoinUser joinUser, Trainer trainer) {
		if(joinDao.insertUser(joinUser) == 0) {
			JOptionPane.showMessageDialog(
					frame,
					"User 생성에 실패했습니다.",
					"User 생성",
					JOptionPane.WARNING_MESSAGE
			);
			
			return 0;
		}
		
		if(trDao.insertTrainer(trainer) == 0) {
			JOptionPane.showMessageDialog(
					frame,
					"Trainer 생성에 실패했습니다.",
					"Trainer 생성",
					JOptionPane.WARNING_MESSAGE
			);
			
			return 0;
		}
		
		return 1;
	}
	
}
