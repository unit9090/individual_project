package edu.java.view.trainer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.BoxLayout;

public class TrainerView {

	private JFrame frame;
	private Component parent;
	private JTabbedPane tabbedPane;
	private JPanel paneMemberManagement;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel;
	private JButton btnDownForm;
	private JButton btnCreateMember;

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
		paneMemberManagement.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		paneMemberManagement.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		panel = new JPanel();
		paneMemberManagement.add(panel, BorderLayout.EAST);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		btnDownForm = new JButton("회원등록폼");
		btnDownForm.setFont(new Font("D2Coding", Font.PLAIN, 15));
		panel.add(btnDownForm);
		
		btnCreateMember = new JButton("회원등록");
		btnCreateMember.setFont(new Font("D2Coding", Font.PLAIN, 15));
		panel.add(btnCreateMember);
		// 마이페이지
		
	}
	
	// 회원등록
	private JPanel paneMemberManagement() {
		paneMemberManagement = new JPanel();
		
		return paneMemberManagement;
	}
}
