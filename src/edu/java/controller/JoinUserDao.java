package edu.java.controller;

import edu.java.model.JoinUser;

public interface JoinUserDao {

	/**
	 * 회원 가입
	 * JoinUser에 먼저 생성
	 * 
	 * @param joinUser
	 * @return 생성되면 1을, 생성되지 않으면 0을 입력한다.
	 */
	int insertUser(JoinUser joinUser);
	
	/**
	 * 아이디 중복확인
	 * DB에 있는 ID들을 뽑아 그 중에 포함되는지 확인
	 * 
	 * @param id
	 * @return boolean
	 */
	boolean doubleCheck(String id, String division);

	/**
	 * 로그인 시 있는 아이디인지 확인
	 * DB에 있는 TR & MB의 아이디들 select
	 * 
	 * @param id
	 * @return boolean
	 */
	boolean loginUserCheck(String id);
	
	/**
	 * 해당 아이디와 일치하는 비밀번호를 리턴
	 * 
	 * @param id
	 * @return String pwd
	 */
	String[] loginUserEqualsIdAndPwd(String id);
}
