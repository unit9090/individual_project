package edu.java.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import edu.java.services.LoginViewService;
import edu.java.view.member.MemberView;
import edu.java.view.trainer.TrainerView;

public class LoginView {

	private JFrame frame;
	private JLabel lblId;
	private JTextField textId;
	private JPasswordField textPwd;
	private JLabel lblPwd;
	private JButton btnLogin;
	
	private Component parent;
	private static int count = 0;
	
	// service
	private LoginViewService service = new LoginViewService();

	/**
	 * Launch the application.
	 */
	public static void showLoginView(Component parent) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView window = new LoginView(parent);
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
	public LoginView(Component parent) {
		this.parent = parent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("운동 기록");
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		int x = 100;
		int y = 100;
		if(parent != null) {
			x = parent.getX();
			y = parent.getY();
		}		
		frame.setBounds(x, y, 451, 659);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
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
		
		textPwd = new JPasswordField();
		textPwd.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textPwd.setBounds(141, 449, 150, 22);
		frame.getContentPane().add(textPwd);
		
		btnLogin = new JButton("로그인");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "아이디를 입력해주세요.");
					return;
				}
				
				if(textPwd.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "비밀번호를 입력해주세요.");
					return;
				}
				
				String x = service.loginUser(frame, textId.getText(), textPwd.getText());
				
				if(x != null) {
					if(x.equals("MB")) {
						frame.dispose();
						MemberView.MemberViewShow(frame, textId.getText());
					} else if(x.equals("TR")) {
						frame.dispose();
						TrainerView.TrainerViewShow(frame, textId.getText());
					}
				}
			}
		});
		btnLogin.setFont(new Font("D2Coding", Font.BOLD, 15));
		btnLogin.setBounds(303, 411, 83, 60);
		frame.getContentPane().add(btnLogin);
		
//		btnFindPwd = new JButton("비밀번호 찾기");
//		btnFindPwd.setFont(new Font("D2Coding", Font.BOLD, 15));
//		btnFindPwd.setBounds(238, 481, 148, 36);
//		frame.getContentPane().add(btnFindPwd);
		
		JButton btnJoin = new JButton(new ImageIcon("images/login_image.png"));
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count++;
				if(count == 10) {
					count = 0;
					frame.setVisible(false);
					JoinView.showJoinView(frame);
				}
			}
		});
		btnJoin.setBounds(89, 122, 256, 256);
		// JButton의 외곽선을 없앰
		btnJoin.setBorderPainted(false);
		// JButton의 내용영역 채우지 않음
		btnJoin.setContentAreaFilled(false);
		// JButton이 선택되었을 때 생기는 테두리 사용 안함.
		btnJoin.setFocusPainted(false);
		frame.getContentPane().add(btnJoin);
	}
	
	
}
