package edu.java.view.trainer;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TrainerMemberInfoUpdateFrame extends JFrame {

	private JPanel contentPane;
	private Component parent;
	private JTextField textName;
	private JTextField textBirth;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textHeight;
	private JTextField textWeight;
	private Component textPhone;

	/**
	 * Launch the application.
	 */
	public static void showMemberInfoUpdate(Component parent) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainerMemberInfoUpdateFrame frame = new TrainerMemberInfoUpdateFrame(parent);
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
	public TrainerMemberInfoUpdateFrame(Component parent) {
		this.parent = parent;
		initialize();
	}
	
	private void initialize() {
		setTitle("회원 정보 수정");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int x = 100;	// 부모 컴포넌트가 null인 경우에 사용할 x 좌표 기본값.
		int y = 100;	// 부모 컴포넌트가 null인 경우에 사용할 y 좌표 기본값.
		if(parent != null) {
			x = parent.getX() + parent.getWidth();	// 부모 컴포넌트의 x 좌표
			y = parent.getY();	// 부모 컴포넌트의 y 좌표
		}		
		setBounds(x, y, 421, 401);	// 다이얼로그 좌표(x, y), 크기(가로, 세로)
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("이름");
		lblName.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblName.setBounds(12, 10, 112, 36);
		contentPane.add(lblName);
		
		textName = new JTextField();
		textName.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textName.setColumns(10);
		textName.setBounds(136, 10, 251, 36);
		contentPane.add(textName);
		
		JLabel lblBirth = new JLabel("생년월일");
		lblBirth.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblBirth.setBounds(12, 56, 112, 36);
		contentPane.add(lblBirth);
		
		textBirth = new JTextField();
		textBirth.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textBirth.setColumns(10);
		textBirth.setBounds(136, 56, 251, 36);
		contentPane.add(textBirth);
		
		JRadioButton radioGender2 = new JRadioButton("여자");
		buttonGroup.add(radioGender2);
		radioGender2.setFont(new Font("D2Coding", Font.PLAIN, 17));
		radioGender2.setBackground(Color.LIGHT_GRAY);
		radioGender2.setBounds(272, 102, 90, 36);
		contentPane.add(radioGender2);
		
		JRadioButton radioGender1 = new JRadioButton("남자");
		buttonGroup.add(radioGender1);
		radioGender1.setFont(new Font("D2Coding", Font.PLAIN, 17));
		radioGender1.setBackground(Color.LIGHT_GRAY);
		radioGender1.setBounds(138, 102, 90, 36);
		contentPane.add(radioGender1);
		
		JLabel lblGender = new JLabel("성별");
		lblGender.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblGender.setBounds(12, 102, 112, 36);
		contentPane.add(lblGender);
		
		textHeight = new JTextField();
		textHeight.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textHeight.setColumns(10);
		textHeight.setBounds(136, 198, 251, 36);
		contentPane.add(textHeight);
		
		JLabel lblHeight = new JLabel("신장");
		lblHeight.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblHeight.setBounds(12, 198, 112, 36);
		contentPane.add(lblHeight);
		
		JLabel lblWeight = new JLabel("몸무게");
		lblWeight.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblWeight.setBounds(12, 244, 112, 36);
		contentPane.add(lblWeight);
		
		textWeight = new JTextField();
		textWeight.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textWeight.setColumns(10);
		textWeight.setBounds(136, 244, 251, 36);
		contentPane.add(textWeight);
		
		JLabel lblPhone = new JLabel("전화번호");
		lblPhone.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblPhone.setBounds(12, 152, 112, 36);
		contentPane.add(lblPhone);
		
		textPhone = new JTextField();
		textPhone.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textPhone.setBounds(136, 152, 251, 36);
		contentPane.add(textPhone);
		
		JButton btnUpdate = new JButton("수정");
		btnUpdate.setFont(new Font("D2Coding", Font.BOLD, 17));
		btnUpdate.setBounds(152, 316, 100, 36);
		contentPane.add(btnUpdate);
		
	}

}
