package edu.java.services;

import java.awt.Component;
import java.util.List;

import javax.swing.JOptionPane;

import edu.java.controller.PtDiaryDaoImpl;
import edu.java.model.PtDiary;

public class PtDiaryService {
	
	// Dao
	private final PtDiaryDaoImpl ptDao = PtDiaryDaoImpl.getInstance(); 
	
	// Pt 다이어리 리스트
	public List<PtDiary> loadAllPtDiary(String mbId) {
		List<PtDiary> list = ptDao.readMemberPtDiary(mbId);
		
		return list;
	}
	
	// Pt 다이어리 등록
	public int createNewPtDiary(Component frame, PtDiary pt) {
		if(ptDao.createPtDiary(pt) == 0) {
			JOptionPane.showMessageDialog(
					frame,
					"PtDiary 생성에 실패했습니다.",
					"PtDiary 생성",
					JOptionPane.WARNING_MESSAGE
			);
			
			return 0;
		}
		
		return 1;		
	}
	
	// 해당 idx에 저장되어 있는 PtDiary 삭제
	public void deletePtDiary(int idx) {
		ptDao.deletePtDiary(idx);
	}
	
	// 해당 idx에 저장되어 있는 PtDiary 수정
	public int updatePtDiary(Component frame, PtDiary pt) {
		if(ptDao.updatePtDiary(pt) == 0) {
			JOptionPane.showMessageDialog(
					frame,
					"PtDiary 수정에 실패했습니다.",
					"PtDiary 수정",
					JOptionPane.WARNING_MESSAGE
			);
			
			return 0;
		}
		
		return 1;
	}
	
	// 해당 idx에 저장되어 있는 PtDiary 내용
	public PtDiary selectPtDiaryInfo(int idx) {
		PtDiary ptDiary = ptDao.readSelectPtDiary(idx);
		
		return ptDiary;
	}
	
}
