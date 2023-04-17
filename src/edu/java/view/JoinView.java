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
	private JTextField textField;
	private JLabel lblPasswordLabel;
	private JLabel lblRePasswordLabel;
	private JButton btnNewButton;

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
		lblName.setBounds(33, 139, 112, 36);
		frame.getContentPane().add(lblName);
		
		textName = new JTextField();
		textName.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textName.setBounds(157, 139, 251, 36);
		frame.getContentPane().add(textName);
		textName.setColumns(10);
		
		JLabel lblGender = new JLabel("성별");
		lblGender.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblGender.setBounds(33, 185, 112, 36);
		frame.getContentPane().add(lblGender);
		
		JRadioButton radioGender1 = new JRadioButton("남자");
		buttonGroupGender.add(radioGender1);
		radioGender1.setBackground(new Color(192, 192, 192));
		radioGender1.setFont(new Font("D2Coding", Font.PLAIN, 17));
		radioGender1.setBounds(159, 185, 90, 36);
		frame.getContentPane().add(radioGender1);
		
		JRadioButton radioGender2 = new JRadioButton("여자");
		buttonGroupGender.add(radioGender2);
		radioGender2.setFont(new Font("D2Coding", Font.PLAIN, 17));
		radioGender2.setBackground(Color.LIGHT_GRAY);
		radioGender2.setBounds(293, 185, 90, 36);
		frame.getContentPane().add(radioGender2);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblId.setBounds(33, 319, 112, 36);
		frame.getContentPane().add(lblId);
		
		textId = new JTextField();
		textId.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textId.setColumns(10);
		textId.setBounds(157, 319, 164, 36);
		frame.getContentPane().add(textId);
		
		JLabel lblPassword = new JLabel("비밀번호");
		lblPassword.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblPassword.setBounds(33, 365, 112, 36);
		frame.getContentPane().add(lblPassword);
		
		textPassword = new JTextField();
		textPassword.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textPassword.setColumns(10);
		textPassword.setBounds(157, 365, 251, 36);
		frame.getContentPane().add(textPassword);
		
		JLabel lblPasswordCheck = new JLabel("비밀번호 확인");
		lblPasswordCheck.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblPasswordCheck.setBounds(33, 428, 112, 36);
		frame.getContentPane().add(lblPasswordCheck);
		
		textPasswordCheck = new JTextField();
		textPasswordCheck.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textPasswordCheck.setColumns(10);
		textPasswordCheck.setBounds(157, 428, 251, 36);
		frame.getContentPane().add(textPasswordCheck);
		
		JButton btnJoin = new JButton("가입");
		btnJoin.setFont(new Font("D2Coding", Font.BOLD, 17));
		btnJoin.setBounds(167, 513, 100, 36);
		frame.getContentPane().add(btnJoin);
		
		JLabel lblPhone = new JLabel("전화번호");
		lblPhone.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblPhone.setBounds(33, 227, 112, 36);
		frame.getContentPane().add(lblPhone);
		
		textPhone = new JTextField();
		textPhone.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textPhone.setColumns(10);
		textPhone.setBounds(157, 227, 251, 36);
		frame.getContentPane().add(textPhone);
		
		JLabel lblEmail = new JLabel("이메일");
		lblEmail.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblEmail.setBounds(33, 273, 112, 36);
		frame.getContentPane().add(lblEmail);
		
		textField = new JTextField();
		textField.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textField.setColumns(10);
		textField.setBounds(157, 273, 251, 36);
		frame.getContentPane().add(textField);
		
		lblPasswordLabel = new JLabel("* 비밀번호는 4자리 이상으로 설정해주세요.");
		lblPasswordLabel.setForeground(Color.RED);
		lblPasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblPasswordLabel.setBounds(158, 403, 250, 15);
		frame.getContentPane().add(lblPasswordLabel);
		lblPasswordLabel.setVisible(false);
		
		lblRePasswordLabel = new JLabel("* 비밀번호가 다릅니다.");
		lblRePasswordLabel.setForeground(Color.RED);
		lblRePasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblRePasswordLabel.setBounds(158, 467, 250, 15);
		frame.getContentPane().add(lblRePasswordLabel);
		lblRePasswordLabel.setVisible(false);
		
		btnNewButton = new JButton("중복확인");
		btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnNewButton.setBounds(326, 319, 82, 36);
		frame.getContentPane().add(btnNewButton);
	}
}
