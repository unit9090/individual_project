package edu.java.view.member;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MemberTrainerInfoFrame extends JFrame {

	private JPanel contentPane;
	private Component parent;
	private JLabel lblImage;
	private JLabel showName;
	private JLabel showGender;
	private JLabel showPhone;
	
	/**
	 * Launch the application.
	 */
	public static void showTrainerInfoFrame(Component parent) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberTrainerInfoFrame frame = new MemberTrainerInfoFrame(parent);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MemberTrainerInfoFrame(Component parent) {
		this.parent = parent;
		initialize();
		
	}
	
	public void initialize() {
		setTitle("트레이너 정보 조회");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int x = 100;	// 부모 컴포넌트가 null인 경우에 사용할 x 좌표 기본값.
		int y = 100;	// 부모 컴포넌트가 null인 경우에 사용할 y 좌표 기본값.
		if(parent != null) {
			x = parent.getX() + parent.getWidth();	// 부모 컴포넌트의 x 좌표
			y = parent.getY();	// 부모 컴포넌트의 y 좌표
		}		
		setBounds(x, y, 498, 307);	// 다이얼로그 좌표(x, y), 크기(가로, 세로)
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblImage = new JLabel("");
		lblImage.setBounds(12, 20, 200, 230);
		contentPane.add(lblImage);
		
		JLabel lblName = new JLabel("이름");
		lblName.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblName.setBounds(224, 20, 74, 21);
		contentPane.add(lblName);
		
		showName = new JLabel("홍길동");
		showName.setFont(new Font("D2Coding", Font.PLAIN, 17));
		showName.setBounds(306, 20, 164, 21);
		contentPane.add(showName);
		
		JLabel lblGender = new JLabel("성별");
		lblGender.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblGender.setBounds(224, 51, 74, 21);
		contentPane.add(lblGender);
		
		showGender = new JLabel("남");
		showGender.setFont(new Font("D2Coding", Font.PLAIN, 17));
		showGender.setBounds(306, 51, 164, 21);
		contentPane.add(showGender);
		
		JLabel lblPhone = new JLabel("전화번호");
		lblPhone.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblPhone.setBounds(224, 82, 74, 21);
		contentPane.add(lblPhone);
		
		showPhone = new JLabel("010-6666-6666");
		showPhone.setFont(new Font("D2Coding", Font.PLAIN, 17));
		showPhone.setBounds(306, 82, 164, 21);
		contentPane.add(showPhone);
	}

}
