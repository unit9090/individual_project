package edu.java.services;

import java.awt.Component;
import java.util.List;

import javax.swing.JOptionPane;

import edu.java.controller.JoinUserDaoImpl;
import edu.java.controller.MemberDaoImpl;
import edu.java.controller.TrainerDaoImpl;
import edu.java.model.JoinUser;
import edu.java.model.Members;

public class TrainerService {
	// Dao
	private final JoinUserDaoImpl juDao = JoinUserDaoImpl.getInstance();
	private final MemberDaoImpl mbDao = MemberDaoImpl.getInstance();
	private final TrainerDaoImpl trDao = TrainerDaoImpl.getInstance();
	
	// 회원 등록 서비스
	public int createNewMember(Component frame, Members member) {
		String checkPhone = member.getPhone().replaceAll("-", "");
		System.out.println(checkPhone);
		if(!juDao.doubleCheckMember(checkPhone, "MB")) {
			JOptionPane.showMessageDialog(
					frame,
					"해당 전화번호로 가입된 회원이 있습니다.",
					"경고",
					JOptionPane.WARNING_MESSAGE
			);
			
			return 0;
		}
		
		// User 생성
		JoinUser joinUser = new JoinUser();
		joinUser.setId(checkPhone);
		joinUser.setPwd(checkPhone);
		joinUser.setPhone(member.getPhone());
		joinUser.setDivision("MB");
		
		if(juDao.insertUser(joinUser) == 0) {
			JOptionPane.showMessageDialog(
					frame,
					"User 생성에 실패했습니다.",
					"User 생성",
					JOptionPane.WARNING_MESSAGE
			);
			
			return 0;
		}
		
		// Member 생성
		if(mbDao.createMember(member) == 0) {
			JOptionPane.showMessageDialog(
					frame,
					"Member 생성에 실패했습니다.",
					"Member 생성",
					JOptionPane.WARNING_MESSAGE
			);
			
			return 0;
		}
		
		return 1;
	}
	
	// 특정 회원 info
	public Members selectMemberInfo(String id) {
		Members member = mbDao.readSelectMemberInfo(id);
		
		return member;
	}
	
	// 회원 수정 서비스
	public int updateMember(Component frame, Members member) {
		// Member 수정
		if(mbDao.updateMember(member) == 0) {
			JOptionPane.showMessageDialog(
					frame,
					"Member 수정에 실패했습니다.",
					"Member 수정",
					JOptionPane.WARNING_MESSAGE
			);
			
			return 0;
		}
		
		return 1;
	}
	
	// 회원 리스트 서비스
	public List<Members> loadAllMemberList(String userId) {
		List<Members> list = mbDao.readTrainerAllMember(userId);
		
		return list;
	}
	
	// 회원 삭제 서비스
	public void deleteMember(String id) {
		mbDao.deleteMember(id);
		// TODO: 해당 아이디로 연결된 pt일지, diary도 삭제
		juDao.deleteUser(id);
	}
	
}
