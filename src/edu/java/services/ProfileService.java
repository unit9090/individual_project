package edu.java.services;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.java.controller.ProfileDaoImpl;

public class ProfileService {
	
	// Dao
	private final ProfileDaoImpl proDao = ProfileDaoImpl.getInstance();
	
	
	// 사진 파일 변경
	public String OpenActionFolder(Component frame, String userId) {
		JFileChooser chooser = new JFileChooser();
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"JPG & PNG Images",	// 파일 이름난에 출력될 문자열
				"jpg", "png");		// 파일 필터로 사용되는 확장자. *.jpg, *.png만 나열됨.
		
		// 파일 다이얼로그에 파일 필터 설정
		chooser.setFileFilter(filter);
		
		// 파일 다이얼로그 출력
		int ret = chooser.showOpenDialog(null);
		if(ret != JFileChooser.APPROVE_OPTION) {
			// 사용자가 창을 강제로 닫았거나 취소버튼을 누른 경우
			JOptionPane.showMessageDialog(
					frame,
					"파일을 선택하지 않았습니다.",
					"경고",
					JOptionPane.WARNING_MESSAGE);
			
			return null;
		}
		
		// 사용자가 파일을 선택하고 "열기" 버튼을 누른 경우
		String filePath = chooser.getSelectedFile().getPath();	// 파일 경로명 리턴
		byte[] byteArr = imageToByteArray(filePath);
		int result = proDao.saveImage(userId, byteArr);
		
		if(result == 1) {
			JOptionPane.showMessageDialog(frame, "이미지가 저장되었습니다.");
		}
		
		return filePath;
		
	}
	
	// 사진 파일 byte[] 파일로 변환
	public byte[] imageToByteArray(String filePath) {
		File file = new File(filePath);
		byte[] returnValue = null;
		
		ByteArrayOutputStream baos = null;
		FileInputStream fis = null;
		
		try {
			baos = new ByteArrayOutputStream();
			fis = new FileInputStream(file);

			byte[] buf = new byte[1024];
			int read = 0;
			
			// 저장된 바이트 배열로 요청된 바이트 수를 읽는다.
			// 스트림의 끝이 감지되거나 예외가 발생할 때까지 계속한다.
			while((read = fis.readNBytes(buf, 0, buf.length)) != 0) {
				// baos에 읽은 바이트들을 쓴다.
				baos.write(buf, 0, read);
			}
			
			returnValue = baos.toByteArray();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(baos != null) baos.close();
				if(fis != null) fis.close();
				
			} catch (IOException e) {
				e.printStackTrace();
				
			}			
		}
		
		return returnValue;
	}
	
	// byte[]를 사진 파일로 변경
	public String ByteArrayToimage(String id) {
		byte[] imageByte = proDao.selectImage(id);
		
		String s = "";
		File file = new File("/images/new.png");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("파일 생성");
		}
		
		InputStream is = null;
		FileOutputStream fos = null;
		
		try {
			is = new ByteArrayInputStream(imageByte);
			fos = new FileOutputStream(file);
			
			int binaryRead;
			
			while((binaryRead = is.read()) != -1) {
				fos.write(binaryRead);
			}
			
			s = fos.toString();
			System.out.println(s);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(is != null) {
				try {
					is.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println(fos);
		
//		if(imageByte == null) {
//			return null;
//		}
//		File file = null;
//		System.out.println(imageByte);
//		String s = "";
//		ByteArrayInputStream baos = null;
//		FileInputStream fis = null;
//		
//		try {
//			baos = new ByteArrayInputStream(imageByte);
//			fis = new FileInputStream(file);
//
//			byte[] buf = new byte[1024];
//			int read = 0;
//			
//			while((read = baos.readNBytes(buf, 0, buf.length)) != 0) {
//				System.out.println(baos.read(buf, 0, read));
//			}
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		
		
//		BufferedImage image = null;
//		
//		try {
//			image = ImageIO.read(new ByteArrayInputStream(imageByte));
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		
//		String filePath = image.toString();
		
		return s;
	}
	
}
