package edu.java.controller;

import edu.java.model.JoinUser;

public interface JoinUserDao {

	/**
	 * 회원 가입
	 * @param joinUser
	 * @return 0 or 1
	 */
	int joinUser(JoinUser joinUser);
	
	
}
