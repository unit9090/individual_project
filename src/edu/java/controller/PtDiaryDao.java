package edu.java.controller;

import java.util.List;

import edu.java.model.PtDiary;

public interface PtDiaryDao {

	/**
	 * Pt 다이어리 생성
	 * @param pt
	 * @return
	 */
	int createPtDiary(PtDiary pt);
	
	/**
	 * 해당 아이디에 저장된 Pt 다이어리 list
	 * @param mbId
	 * @return
	 */
	List<PtDiary> readMemberPtDiary(String mbId);
	
	/**
	 * 선택한 pidx에 저장된 내용
	 * @param pidx
	 * @return
	 */
	PtDiary readSelectPtDiary(int pidx);
	
	/**
	 * 선택한 pidx 내용 수정
	 * @param pt
	 * @return
	 */
	int updatePtDiary(PtDiary pt);
	
	/**
	 * 선택한 pidx 삭제
	 * @param pidx
	 * @return
	 */
	int deletePtDiary(int pidx);
	
	/**
	 * 해당 아이디가 삭제될 때 내용도 같이 삭제
	 * @param mbId
	 * @return
	 */
	int deleteMemberPtDiary(String mbId);
	
}
