package edu.java.view.trainer;

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

public class TrainerPtDiaryCreateFrame extends JFrame {

	private JPanel contentPane;
	private Component parent;
	private JTextField textTitle;
	private JTextArea textContents;
	private JButton btnCreate;

	/**
	 * Launch the application.
	 */
	public static void showPtDiaryCreate(Component parent) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainerPtDiaryCreateFrame frame = new TrainerPtDiaryCreateFrame(parent);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public TrainerPtDiaryCreateFrame(Component parent) {
		this.parent = parent;
		initialize();
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setTitle("PT 일지 등록");
		int x = 100;
		int y = 100;
		if(parent != null) {
			x = parent.getX() + parent.getWidth();
			y = parent.getY();
		}	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(x, y, 498, 466);
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
		
		btnCreate = new JButton("등록");
		btnCreate.setFont(new Font("D2Coding", Font.PLAIN, 17));
		panelBtn.add(btnCreate);
	}

}
