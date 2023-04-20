package edu.java.controller;

import java.util.List;

import edu.java.model.Members;

public interface MemberDao {
	
	/**
	 * Member insert
	 * 
	 * @param member
	 * @return 행의 개수
	 */
	int createMember(Members member);
	
	/**
	 * 해당 트레이너가 등록한 멤버 table에 뿌려줄 모든 멤버
	 * 
	 * @return
	 */
	List<Members> readTrainerAllMember(String id);
	
	/**
	 * 선택된 회원의 정보 읽어오기
	 * @param id
	 * @return
	 */
	Members readSelectMemberInfo(String id);
	
	/**
	 * 선택된 회원의 정보 수정
	 * @param member
	 * @return
	 */
	int updateMember(Members member);
	
	/**
	 * 선택된 회원 정보 삭제
	 * @param id
	 * @return
	 */
	int deleteMember(String id);
	
}
