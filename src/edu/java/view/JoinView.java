package edu.java.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import edu.java.controller.JoinUserDaoImpl;
import edu.java.services.TextFilter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JoinView {

	private JFrame frame;
	private JTextField textName;
	private final ButtonGroup buttonGroupGender = new ButtonGroup();
	private JTextField textId;
	private JPasswordField textPassword;
	private JPasswordField textPasswordCheck;
	private JTextField textPhone;
	
	private Component parent;
	private JTextField textEmail;
	private JLabel lblRePasswordLabel;
	private JButton btnNewButton;
	private JTextField textBirth;
	
	private final JoinUserDaoImpl dao = JoinUserDaoImpl.getInstance();

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
		lblName.setBounds(33, 128, 112, 36);
		frame.getContentPane().add(lblName);
		
		textName = new JTextField();
		textName.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textName.setBounds(157, 128, 251, 36);
		frame.getContentPane().add(textName);
		textName.setColumns(10);
		
		JLabel lblGender = new JLabel("성별");
		lblGender.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblGender.setBounds(33, 174, 112, 36);
		frame.getContentPane().add(lblGender);
		
		JRadioButton radioGender1 = new JRadioButton("남자");
		buttonGroupGender.add(radioGender1);
		radioGender1.setBackground(new Color(192, 192, 192));
		radioGender1.setFont(new Font("D2Coding", Font.PLAIN, 17));
		radioGender1.setBounds(159, 174, 90, 36);
		frame.getContentPane().add(radioGender1);
		
		JRadioButton radioGender2 = new JRadioButton("여자");
		buttonGroupGender.add(radioGender2);
		radioGender2.setFont(new Font("D2Coding", Font.PLAIN, 17));
		radioGender2.setBackground(Color.LIGHT_GRAY);
		radioGender2.setBounds(293, 174, 90, 36);
		frame.getContentPane().add(radioGender2);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblId.setBounds(33, 358, 112, 36);
		frame.getContentPane().add(lblId);
		
		textId = new JTextField();
		textId.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textId.setColumns(10);
		textId.setBounds(157, 358, 164, 36);
		frame.getContentPane().add(textId);
		
		JLabel lblPassword = new JLabel("비밀번호");
		lblPassword.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblPassword.setBounds(33, 404, 112, 36);
		frame.getContentPane().add(lblPassword);
		
		// 비밀번호
		textPassword = new JPasswordField();
		textPassword.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textPassword.setColumns(10);
		textPassword.setBounds(157, 404, 251, 36);
		frame.getContentPane().add(textPassword);
		
		JLabel lblPasswordCheck = new JLabel("비밀번호 확인");
		lblPasswordCheck.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblPasswordCheck.setBounds(33, 450, 112, 36);
		frame.getContentPane().add(lblPasswordCheck);
		
		textPasswordCheck = new JPasswordField();
		textPasswordCheck.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textPasswordCheck.setColumns(10);
		textPasswordCheck.setBounds(157, 450, 251, 36);
		frame.getContentPane().add(textPasswordCheck);
		
		JButton btnJoin = new JButton("가입");
		btnJoin.setFont(new Font("D2Coding", Font.BOLD, 17));
		btnJoin.setBounds(167, 542, 100, 36);
		frame.getContentPane().add(btnJoin);
		
		JLabel lblPhone = new JLabel("전화번호");
		lblPhone.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblPhone.setBounds(33, 266, 112, 36);
		frame.getContentPane().add(lblPhone);
		
		// 전화번호
		textPhone = new JTextField(13);
		textPhone.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textPhone.setBounds(157, 266, 251, 36);
		textPhone.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				TextFilter.checkPhone(frame, e);
			}
		});
		frame.getContentPane().add(textPhone);
		
		
		JLabel lblEmail = new JLabel("이메일");
		lblEmail.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblEmail.setBounds(33, 312, 112, 36);
		frame.getContentPane().add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textEmail.setColumns(10);
		textEmail.setBounds(157, 312, 251, 36);
		frame.getContentPane().add(textEmail);
		
		lblRePasswordLabel = new JLabel("* 비밀번호가 다릅니다.");
		lblRePasswordLabel.setForeground(Color.RED);
		lblRePasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblRePasswordLabel.setBounds(158, 489, 250, 15);
		frame.getContentPane().add(lblRePasswordLabel);
		lblRePasswordLabel.setVisible(false);
		
		btnNewButton = new JButton("중복확인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				dao.doubleCheck(text)
				dao.doubleCheck(textId.getText());
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnNewButton.setBounds(326, 358, 82, 36);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblBirth = new JLabel("생년월일");
		lblBirth.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblBirth.setBounds(33, 220, 112, 36);
		frame.getContentPane().add(lblBirth);
		
		// 생년월일
		textBirth = new JTextField();
		textBirth.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textBirth.setBounds(157, 220, 251, 36);
		textBirth.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				TextFilter.checkBirth(frame, e);
			}
		});
		frame.getContentPane().add(textBirth);
	}
	
}
