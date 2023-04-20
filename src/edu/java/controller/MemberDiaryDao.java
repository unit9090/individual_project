package edu.java.controller;

import java.util.List;

import edu.java.model.MemberDiary;

public interface MemberDiaryDao {

	/**
	 * diary 생성
	 * @param diary
	 * @return
	 */
	int createDiary(MemberDiary diary);
	
	/**
	 * 해당 member id에 저장된 다이어리 list
	 * @param id
	 * @return
	 */
	List<MemberDiary> readMemberDiary(String id);
	
	/**
	 * 선택한 mdIdx에 저장된 내용
	 * @param mdIdx
	 * @return
	 */
	MemberDiary readSelectMemberDiary(int mdIdx);
	
	/**
	 * 선택한 mdIdx의 내용 수정
	 * @param diary
	 * @return
	 */
	int updateMemberDiary(MemberDiary diary);
	
	/**
	 * 선택한 mdIdx 삭제
	 * @param mdIdx
	 * @return
	 */
	int deleteMemberDiary(int mdIdx);
	
	/**
	 * 해당 아이디가 삭제될 때 내용도 같이 삭제
	 * 
	 * @param mdId
	 * @return
	 */
	int deleteMemberDiary(String mdId);
	
}
