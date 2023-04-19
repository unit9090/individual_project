package edu.java.controller;

import java.awt.image.BufferedImage;

public interface ProfileDao {
	
	/**
	 * 이미지 파일 저장
	 * 
	 * @param id
	 * @param pblob
	 * @return int
	 */
	int saveImage(String id, BufferedImage image);
	
	/**
	 * 이미지 파일 찾기
	 * 
	 * @param id
	 * @return byte[]
	 */
	byte[] selectImage(String id);
}
