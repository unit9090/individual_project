package edu.java.services;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageFile {
	
	
	// 사진 파일 올리기
	class OpenActionListener implements ActionListener {
		
		private JFileChooser chooser;
		
		public OpenActionListener() {
			// 파일 다이얼로그 생성
			chooser = new JFileChooser();
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"JPG & PNG Images",		// 파일 이름난에 출력될 문자열
					"jpg", "png");			// 파일 필터로 사용되는 확장자. *.jpg, *.png만 나열됨.
			
			// 파일 다이얼로그에 파일 필터 설정
			chooser.setFileFilter(filter);
			// 파일 다이얼로그 출력
			int ret = chooser.showOpenDialog(null);
			if(ret != JFileChooser.APPROVE_OPTION) {
				// 사용자가 창을 강제로 닫았거나 취소버튼을 누른 경우
//				JOptionPane.showMessageDialog(
//						frame,
//						"파일을 선택하지 않았습니다.",
//						"경고",
//						JOptionPane.WARNING_MESSAGE);
//				
//				return;
			}
			
			// 사용자가 파일을 선택하고 "열기" 버튼을 누른 경우
			String filePath = chooser.getSelectedFile().getPath();	// 파일 경로명 리턴
			
			BufferedImage image = null;
			try {
				File imageFile = new File(filePath);
				image = ImageIO.read(imageFile);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
			
			ImageIcon icon = new ImageIcon(filePath);
			Image img = icon.getImage();
			Image chageImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		}
	
	}
}
