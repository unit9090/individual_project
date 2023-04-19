package edu.java.controller;

import java.util.List;

import edu.java.model.JoinUser;

public interface JoinUserDao {

	/**
	 * 회원 가입
	 * @param joinUser
	 * @return 0 or 1
	 */
	int joinUser(JoinUser joinUser);
	
	/**
	 * 아이디 중복확인
	 * @param id
	 * @return boolean
	 */
	boolean doubleCheck(String id);
}
