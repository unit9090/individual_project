package edu.java.view.trainer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TrainerMemberPtDiaryView {

	private JFrame frame;
	private Component parent;
	
	private static final String[] PT_COLUMN_NAMES = {"날짜", "제목"};
	private DefaultTableModel modelPt;
	private JButton btnCreate;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JTable table;
	
	private String trId;
	private String mbId;
	
	// dao
	
	
	/**
	 * Launch the application.
	 */
	public static void showTrainerMemberPtDiary(Component parent, String trId, String mbId) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainerMemberPtDiaryView window = new TrainerMemberPtDiaryView(parent, trId, mbId);
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
	public TrainerMemberPtDiaryView(Component parent, String trId, String mbId) {
		this.parent = parent;
		this.trId = trId;
		this.mbId = mbId;
		
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("회원 PT 일지");
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
				frame.dispose();
				// TODO: 활성화
				TrainerView.TrainerViewShow(frame, trId);
			}
		});
		
		JPanel panelBtn = new JPanel();
		frame.getContentPane().add(panelBtn, BorderLayout.EAST);
		
		btnCreate = new JButton("PT 일지 등록");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrainerPtDiaryCreateFrame.showPtDiaryCreate(frame);
			}
		});
		btnCreate.setFont(new Font("D2Coding", Font.PLAIN, 15));
		panelBtn.add(btnCreate);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panelBtn.add(verticalStrut);
		
		btnUpdate = new JButton("PT 일지 수정");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrainerPtDiaryUpdateFrame.showPtDiaryUpdate(frame);
			}
		});
		btnUpdate.setFont(new Font("D2Coding", Font.PLAIN, 15));
		panelBtn.add(btnUpdate);
		panelBtn.setLayout(new BoxLayout(panelBtn, BoxLayout.Y_AXIS));
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panelBtn.add(verticalStrut_1);
		
		btnDelete = new JButton("PT 일지 삭제");
		btnDelete.setFont(new Font("D2Coding", Font.PLAIN, 15));
		panelBtn.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		modelPt = new DefaultTableModel(null, PT_COLUMN_NAMES);
		table.setModel(modelPt);
		table.setFont(new Font("D2Coding", Font.PLAIN, 17));
		scrollPane.setViewportView(table);
	}

}
