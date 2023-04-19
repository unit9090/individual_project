package edu.java.controller;

public interface ProfileDao {
	
	/**
	 * 이미지 파일 저장
	 * 
	 * @param id
	 * @param pblob
	 * @return int
	 */
	int saveImage(String id, byte[] byteArr);
	
	/**
	 * 이미지 파일 찾기
	 * 
	 * @param id
	 * @return byte[]
	 */
	byte[] selectImage(String id);
	
	
}
