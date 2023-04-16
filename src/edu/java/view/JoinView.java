package edu.java.view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class JoinView {

	private JFrame frame;
	private JTextField textName;
	private final ButtonGroup buttonGroupGender = new ButtonGroup();
	private JTextField textId;
	private JTextField textPassword;
	private JTextField textPasswordCheck;
	private final ButtonGroup buttonGroupMember = new ButtonGroup();
	private JTextField textPhone;
	
	private Component parent;

	/**
	 * Launch the application.
	 */
	public static void showJoinView (Component parent) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JoinView window = new JoinView(parent);
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
	public JoinView(Component parent) {
		this.parent = parent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("회원가입");
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		int x = 100;
		int y = 100;
		if(parent != null) {
			x = parent.getX();
			y = parent.getY();
		}		
		frame.setBounds(x, y, 451, 659);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				frame.setVisible(false);
				LoginView.showLoginView(frame);
			}
		});
		
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("회원가입");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("D2Coding", Font.BOLD, 24));
		lblTitle.setBounds(158, 66, 119, 47);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblName = new JLabel("이름");
		lblName.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblName.setBounds(32, 149, 112, 36);
		frame.getContentPane().add(lblName);
		
		textName = new JTextField();
		textName.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textName.setBounds(156, 149, 251, 36);
		frame.getContentPane().add(textName);
		textName.setColumns(10);
		
		JLabel lblGender = new JLabel("성별");
		lblGender.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblGender.setBounds(32, 204, 112, 36);
		frame.getContentPane().add(lblGender);
		
		JRadioButton radioGender1 = new JRadioButton("남자");
		buttonGroupGender.add(radioGender1);
		radioGender1.setBackground(new Color(192, 192, 192));
		radioGender1.setFont(new Font("D2Coding", Font.PLAIN, 17));
		radioGender1.setBounds(158, 204, 90, 36);
		frame.getContentPane().add(radioGender1);
		
		JRadioButton radioGender2 = new JRadioButton("여자");
		buttonGroupGender.add(radioGender2);
		radioGender2.setFont(new Font("D2Coding", Font.PLAIN, 17));
		radioGender2.setBackground(Color.LIGHT_GRAY);
		radioGender2.setBounds(292, 204, 90, 36);
		frame.getContentPane().add(radioGender2);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblId.setBounds(32, 342, 112, 36);
		frame.getContentPane().add(lblId);
		
		textId = new JTextField();
		textId.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textId.setColumns(10);
		textId.setBounds(156, 342, 251, 36);
		frame.getContentPane().add(textId);
		
		JLabel lblMember = new JLabel("구분");
		lblMember.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblMember.setBounds(32, 292, 112, 36);
		frame.getContentPane().add(lblMember);
		
		JRadioButton radioMember1 = new JRadioButton("트레이너");
		buttonGroupMember.add(radioMember1);
		radioMember1.setFont(new Font("D2Coding", Font.PLAIN, 17));
		radioMember1.setBackground(Color.LIGHT_GRAY);
		radioMember1.setBounds(158, 292, 116, 36);
		frame.getContentPane().add(radioMember1);
		
		JRadioButton radioMember2 = new JRadioButton("회원");
		buttonGroupMember.add(radioMember2);
		radioMember2.setFont(new Font("D2Coding", Font.PLAIN, 17));
		radioMember2.setBackground(Color.LIGHT_GRAY);
		radioMember2.setBounds(292, 293, 90, 36);
		frame.getContentPane().add(radioMember2);
		
		JLabel lblPassword = new JLabel("비밀번호");
		lblPassword.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblPassword.setBounds(32, 393, 112, 36);
		frame.getContentPane().add(lblPassword);
		
		textPassword = new JTextField();
		textPassword.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textPassword.setColumns(10);
		textPassword.setBounds(156, 393, 251, 36);
		frame.getContentPane().add(textPassword);
		
		JLabel lblPasswordCheck = new JLabel("비밀번호 확인");
		lblPasswordCheck.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblPasswordCheck.setBounds(32, 447, 112, 36);
		frame.getContentPane().add(lblPasswordCheck);
		
		textPasswordCheck = new JTextField();
		textPasswordCheck.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textPasswordCheck.setColumns(10);
		textPasswordCheck.setBounds(156, 447, 251, 36);
		frame.getContentPane().add(textPasswordCheck);
		
		JButton btnJoin = new JButton("가입");
		btnJoin.setFont(new Font("D2Coding", Font.BOLD, 17));
		btnJoin.setBounds(167, 513, 100, 36);
		frame.getContentPane().add(btnJoin);
		
		JLabel lblPhone = new JLabel("전화번호");
		lblPhone.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblPhone.setBounds(32, 246, 112, 36);
		frame.getContentPane().add(lblPhone);
		
		textPhone = new JTextField();
		textPhone.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textPhone.setColumns(10);
		textPhone.setBounds(156, 246, 251, 36);
		frame.getContentPane().add(textPhone);
	}
}
