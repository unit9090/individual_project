package edu.java.services;

import java.awt.Component;
import java.util.List;

import javax.swing.JOptionPane;

import edu.java.controller.MemberDiaryDaoImpl;
import edu.java.model.MemberDiary;

public class MemberDiaryService {

	// Dao
	private final MemberDiaryDaoImpl mdDao = MemberDiaryDaoImpl.getInstance();
	
	// diary 리스트
	public List<MemberDiary> loadAllDiary(String id) {
		List<MemberDiary> list = mdDao.readMemberDiary(id);
		
		return list;
	}
	
	// diary 등록
	public int createNewDiary(Component frame, MemberDiary md) {
		if(mdDao.createDiary(md) == 0) {
			JOptionPane.showMessageDialog(
					frame,
					"Diary 생성에 실패했습니다.",
					"Diary 생성",
					JOptionPane.WARNING_MESSAGE
			);
			
			return 0;
		}
		
		return 1;
	}
	
	// 해당 idx에 저장되어 있는 PtDiary 삭제
	public void deleteDiary(int idx) {
		mdDao.deleteMemberDiary(idx);
	}
	
	// 해당 idx에 저장되어 있는 PtDiary 수정
	public int updateDiary(Component frame, MemberDiary md) {
		if(mdDao.updateMemberDiary(md) == 0) {
			JOptionPane.showMessageDialog(
					frame,
					"Diary 수정에 실패했습니다.",
					"Diary 수정",
					JOptionPane.WARNING_MESSAGE
			);
			
			return 0;
		}
		
		return 1;
	}
	
	// 해당 idx에 저장되어 있는 PtDiary 내용
	public MemberDiary selectDiaryInfo(int idx) {
		MemberDiary diary = mdDao.readSelectMemberDiary(idx);
		
		return diary;
	}
	
	
	
	
}
