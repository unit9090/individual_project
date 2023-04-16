package edu.java.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MemberView {

	private JFrame frame;
	
	private Component parent;
	private JTabbedPane tabbedPane;
	private JLabel lblImage;
	private JLabel lblName;
	private JPanel panePT;
	private JPanel paneMyPage;
	private JPanel paneDiary;

	private JButton btnImageChage;

	/**
	 * Launch the application.
	 */
//	public static void MemberViewShow(Component parent) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MemberView window = new MemberView(parent);
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberView window = new MemberView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
//	public MemberView(Component parent) {
//		this.parent = parent;
//		initialize();
//	}
	
	public MemberView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("사용자 화면");
		int x = 100;
		int y = 100;
		if(parent != null) {
			x = parent.getX();
			y = parent.getY();
		}		
		frame.setBounds(x, y, 451, 659);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		paneMyPage = new JPanel();
		tabbedPane.addTab("마이페이지", null, paneMyPage, null);
		paneMyPage.setLayout(null);
		
		lblName = new JLabel("이름");
		lblName.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblName.setBounds(224, 10, 57, 25);
		paneMyPage.add(lblName);
		
		lblImage = new JLabel();
		lblImage.setBounds(12, 10, 200, 200);
		paneMyPage.add(lblImage);
		
		btnImageChage = new JButton("이미지 변경");
		btnImageChage.addActionListener(new OpenActionListener());
		btnImageChage.setFont(new Font("Dialog", Font.PLAIN, 17));
		btnImageChage.setBounds(12, 220, 200, 23);
		paneMyPage.add(btnImageChage);
		
		panePT = new JPanel();
		tabbedPane.addTab("PT 일지", null, panePT, null);
		
		paneDiary = new JPanel();
		tabbedPane.addTab("다이어리", null, paneDiary, null);
			
	}
	
	class OpenActionListener implements ActionListener {
		
		private JFileChooser chooser;
		
		public OpenActionListener() {
			// 파일 다이얼로그 생성
			chooser = new JFileChooser();
			System.out.println("???");
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
				JOptionPane.showMessageDialog(
						frame,
						"파일을 선택하지 않았습니다.",
						"경고",
						JOptionPane.WARNING_MESSAGE);
				
				return;
			}
			
			// 사용자가 파일을 선택하고 "열기" 버튼을 누른 경우
			String filePath = chooser.getSelectedFile().getPath();	// 파일 경로명 리턴
			ImageIcon icon = new ImageIcon(filePath);
			Image img = icon.getImage();
			Image chageImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			lblImage.setIcon(new ImageIcon(chageImg));	// 이미지 출력
		}
		
	}
}

