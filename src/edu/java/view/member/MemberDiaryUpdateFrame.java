package edu.java.view.member;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MemberDiaryUpdateFrame extends JFrame {

	private JPanel contentPane;
	private Component parent;
	
	private JLabel lblImage;
	private JLabel showName;
	private JLabel showGender;
	private JLabel showPhone;
	private JTextField textTitle;
	private JTextArea textContents;
	private JButton btnUpdate;
	
	/**
	 * Launch the application.
	 */
	public static void showMemberDiaryUpdate(Component parent) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberDiaryUpdateFrame frame = new MemberDiaryUpdateFrame(parent);
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
	public MemberDiaryUpdateFrame(Component parent) {
		this.parent = parent;
		initialize();
		
	}
	
	public void initialize() {
		setTitle("글 수정");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int x = 100;	// 부모 컴포넌트가 null인 경우에 사용할 x 좌표 기본값.
		int y = 100;	// 부모 컴포넌트가 null인 경우에 사용할 y 좌표 기본값.
		if(parent != null) {
			x = parent.getX() + parent.getWidth();	// 부모 컴포넌트의 x 좌표
			y = parent.getY();	// 부모 컴포넌트의 y 좌표
		}		
		setBounds(x, y, 498, 466);	// 다이얼로그 좌표(x, y), 크기(가로, 세로)
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelTitle = new JPanel();
		getContentPane().add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblTitle = new JLabel("제목   ");
		lblTitle.setFont(new Font("D2Coding", Font.PLAIN, 17));
		panelTitle.add(lblTitle);
		
		textTitle = new JTextField();
		textTitle.setFont(new Font("D2Coding", Font.PLAIN, 17));
		panelTitle.add(textTitle);
		textTitle.setColumns(40);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		textContents = new JTextArea();
		textContents.setFont(new Font("D2Coding", Font.PLAIN, 17));
		scrollPane.setViewportView(textContents);
		
		JPanel panelBtn = new JPanel();
		getContentPane().add(panelBtn, BorderLayout.SOUTH);
		
		btnUpdate = new JButton("수정");
		btnUpdate.setFont(new Font("D2Coding", Font.PLAIN, 17));
		panelBtn.add(btnUpdate);
	}

}
