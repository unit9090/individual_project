package edu.java.view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HealthLoginView {

	private JFrame frame;
	private JLabel lblImage;
	private JLabel lblId;
	private JTextField textId;
	private JTextField textPwd;
	private JButton btnCreate;
	private JLabel lblPwd;
	private JButton btnLogin;
	private JButton btnFindPwd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HealthLoginView window = new HealthLoginView();
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
	public HealthLoginView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("운동 기록");
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 451, 659);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblImage = new JLabel(new ImageIcon("images/login_image.png"));
		lblImage.setBounds(89, 122, 256, 256);
		frame.getContentPane().add(lblImage);
		
		JLabel lblTitle = new JLabel("운동 기록");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("D2Coding", Font.BOLD, 24));
		lblTitle.setBounds(158, 66, 119, 47);
		frame.getContentPane().add(lblTitle);
		
		lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("D2Coding", Font.BOLD, 18));
		lblId.setBounds(72, 417, 57, 22);
		frame.getContentPane().add(lblId);
		
		textId = new JTextField();
		textId.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textId.setBounds(141, 417, 150, 22);
		frame.getContentPane().add(textId);
		textId.setColumns(10);
		
		lblPwd = new JLabel("PWD");
		lblPwd.setHorizontalAlignment(SwingConstants.CENTER);
		lblPwd.setFont(new Font("D2Coding", Font.BOLD, 18));
		lblPwd.setBounds(72, 449, 57, 22);
		frame.getContentPane().add(lblPwd);
		
		textPwd = new JTextField();
		textPwd.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textPwd.setColumns(10);
		textPwd.setBounds(141, 449, 150, 22);
		frame.getContentPane().add(textPwd);
		
		btnLogin = new JButton("로그인");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogin.setFont(new Font("D2Coding", Font.BOLD, 15));
		btnLogin.setBounds(303, 411, 83, 60);
		frame.getContentPane().add(btnLogin);
		
		btnCreate = new JButton("회원가입");
		btnCreate.setFont(new Font("D2Coding", Font.BOLD, 15));
		btnCreate.setBounds(326, 574, 97, 36);
		frame.getContentPane().add(btnCreate);
		
		btnFindPwd = new JButton("비밀번호 찾기");
		btnFindPwd.setFont(new Font("D2Coding", Font.BOLD, 15));
		btnFindPwd.setBounds(12, 574, 148, 36);
		frame.getContentPane().add(btnFindPwd);
	}
}
