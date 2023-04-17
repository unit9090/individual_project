package edu.java.view.trainer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import edu.java.view.LoginView;

public class TrainerView {

	private JFrame frame;
	private Component parent;
	private JTabbedPane tabbedPane;
	
	// 회원 관리
	private static final String[] MEMBER_COLUMN_NAMES = {"No", "ID", "이름", "전화번호"};
	private DefaultTableModel modelMember;
	
	private JPanel paneMemberManagement;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel;
	private JButton btnDownForm;
	private JButton btnCreateMember;
	private JButton btnUpdateMember;
	private JButton btnDeleteMember;
	private JButton btnPtMember;
	private JPanel paneTrainerMyPage;
	private JLabel lblImage;
	private JButton btnImageChage;
	private JLabel showBirth;
	private Component showName;
	private JLabel lblEmail;
	private JLabel showEmail;
	private JLabel lblPhone;
	private JLabel showPhone;
	private JLabel lblId;
	private JLabel showID;
	private JLabel lblMyPageIcon;
	private JLabel lblMyPage;
	private JButton btnLogout;

	/**
	 * Launch the application.
	 */
//	public static void TrainerViewShow(Component parent) {
//	EventQueue.invokeLater(new Runnable() {
//		public void run() {
//			try {
//				TrainerView window = new TrainerView(parent);
//				window.frame.setVisible(true);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	});
//}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainerView window = new TrainerView();
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
//	public TrainerView(Component parent) {
//	this.parent = parent;
//	initialize();
//}
	public TrainerView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("트레이너 화면");
		int x = 100;
		int y = 100;
		if(parent != null) {
			x = parent.getX();
			y = parent.getY();
		}		
		frame.setBounds(x, y, 451, 659);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		// 회원등록 - 회원 리스트, 회원 인바디 관리, 회원 PT일지
		tabbedPane.addTab("회원 관리", paneMemberManagement());
		
		// 마이페이지
		tabbedPane.addTab("마이페이지", paneTrainerMyPage());
		
		lblEmail = new JLabel("이메일");
		lblEmail.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblEmail.setBounds(12, 377, 96, 25);
		paneTrainerMyPage.add(lblEmail);
		
		showEmail = new JLabel("test@test.com");
		showEmail.setFont(new Font("D2Coding", Font.PLAIN, 17));
		showEmail.setBounds(120, 377, 166, 25);
		paneTrainerMyPage.add(showEmail);
		
		lblPhone = new JLabel("전화번호");
		lblPhone.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblPhone.setBounds(12, 412, 96, 25);
		paneTrainerMyPage.add(lblPhone);
		
		showPhone = new JLabel("010-0000-0000");
		showPhone.setFont(new Font("D2Coding", Font.PLAIN, 17));
		showPhone.setBounds(120, 412, 166, 25);
		paneTrainerMyPage.add(showPhone);
		
		lblId = new JLabel("ID");
		lblId.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblId.setBounds(224, 64, 76, 25);
		paneTrainerMyPage.add(lblId);
		
		showID = new JLabel("72884444");
		showID.setFont(new Font("D2Coding", Font.PLAIN, 17));
		showID.setBounds(312, 64, 106, 25);
		paneTrainerMyPage.add(showID);
		
		lblMyPageIcon = new JLabel("");
		lblMyPageIcon.setBounds(12, 13, 37, 32);
		paneTrainerMyPage.add(lblMyPageIcon);
		
		lblMyPage = new JLabel("마이페이지");
		lblMyPage.setFont(new Font("D2Coding", Font.BOLD, 20));
		lblMyPage.setBounds(61, 10, 269, 35);
		paneTrainerMyPage.add(lblMyPage);
		
		btnLogout = new JButton("로그아웃");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(
						frame,
						"로그아웃 하시겠습니까?",
						"로그아웃 확인",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE
				);
				System.out.println(result);
				if(result == 0) {
					frame.setVisible(false);
					LoginView.showLoginView(frame);
				}
			}
			
		});
		btnLogout.setFont(new Font("D2Coding", Font.PLAIN, 17));
		btnLogout.setBounds(312, 546, 106, 35);
		paneTrainerMyPage.add(btnLogout);
		
		
	}
	
	// 회원등록
	private JPanel paneMemberManagement() {
		paneMemberManagement = new JPanel();
		paneMemberManagement.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		paneMemberManagement.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		modelMember = new DefaultTableModel(null, MEMBER_COLUMN_NAMES);
		table.setModel(modelMember);
		table.setFont(new Font("D2Coding", Font.PLAIN, 17));
		scrollPane.setViewportView(table);
		
		panel = new JPanel();
		paneMemberManagement.add(panel, BorderLayout.EAST);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		btnDownForm = new JButton("회원등록폼");
		btnDownForm.setFont(new Font("D2Coding", Font.PLAIN, 15));
		panel.add(btnDownForm);
		panel.add(Box.createVerticalStrut(5));
		
		btnCreateMember = new JButton("회원 등록");
		btnCreateMember.setFont(new Font("D2Coding", Font.PLAIN, 15));
		panel.add(btnCreateMember);
		panel.add(Box.createVerticalStrut(5));
		
		btnUpdateMember = new JButton("회원 수정");
		btnUpdateMember.setFont(new Font("D2Coding", Font.PLAIN, 15));
		panel.add(btnUpdateMember);
		panel.add(Box.createVerticalStrut(5));
		
		btnDeleteMember = new JButton("회원 삭제");
		btnDeleteMember.setFont(new Font("D2Coding", Font.PLAIN, 15));
		panel.add(btnDeleteMember);
		panel.add(Box.createVerticalGlue());
		
		btnPtMember = new JButton("회원 PT 일지");
		btnPtMember.setFont(new Font("D2Coding", Font.PLAIN, 15));
		panel.add(btnPtMember);
		
		return paneMemberManagement;
	}
	
	// 마이페이지
	private JPanel paneTrainerMyPage() {
		paneTrainerMyPage = new JPanel();
		paneTrainerMyPage.setLayout(null);
		
		JLabel lblName = new JLabel("이름");
		lblName.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblName.setBounds(12, 307, 96, 25);
		paneTrainerMyPage.add(lblName);
		
		lblImage = new JLabel();
		lblImage.setBounds(12, 64, 200, 200);
		paneTrainerMyPage.add(lblImage);
		Image memberImg = new ImageIcon("images/default_image.png").getImage();
		Image chageMemberImg = memberImg.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
		lblImage.setIcon(new ImageIcon(chageMemberImg));
		
		btnImageChage = new JButton("이미지 변경");
		btnImageChage.addActionListener(new OpenActionListener());
		btnImageChage.setFont(new Font("D2Coding", Font.PLAIN, 17));
		btnImageChage.setBounds(12, 274, 200, 23);
		paneTrainerMyPage.add(btnImageChage);
		
		showName = new JLabel("홍익인간");
		showName.setFont(new Font("D2Coding", Font.PLAIN, 17));
		showName.setBounds(120, 307, 166, 25);
		paneTrainerMyPage.add(showName);
		
		JLabel lblBirth = new JLabel("생년월일");
		lblBirth.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblBirth.setBounds(12, 342, 96, 25);
		paneTrainerMyPage.add(lblBirth);
		
		showBirth = new JLabel("0000.00.00");
		showBirth.setFont(new Font("D2Coding", Font.PLAIN, 17));
		showBirth.setBounds(120, 342, 166, 25);
		paneTrainerMyPage.add(showBirth);
		
		
		return paneTrainerMyPage;
	}
	
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
