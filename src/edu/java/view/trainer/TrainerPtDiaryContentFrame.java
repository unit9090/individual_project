package edu.java.view.trainer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import edu.java.model.PtDiary;
import edu.java.services.PtDiaryService;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TrainerPtDiaryContentFrame extends JFrame {

	private JPanel contentPane;
	private Component parent;
	private JTextField textTitle;
	private JTextArea textContents;
	
	private int idx;
	
	private final PtDiaryService ptService = new PtDiaryService();

	/**
	 * Launch the application.
	 */
	public static void showPtDiaryContents(Component parent, int idx) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainerPtDiaryContentFrame frame = new TrainerPtDiaryContentFrame(parent, idx);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public TrainerPtDiaryContentFrame(Component parent, int idx) {
		this.parent = parent;
		this.idx = idx;
		initialize();
		readPtDiary();
		
	}
	
	private void readPtDiary() {
		PtDiary ptDiary = ptService.selectPtDiaryInfo(idx);
		
		textTitle.setText(ptDiary.getTitle());
		String str = ptDiary.getContent().replaceAll("<br>", "\n");
		textContents.setText(str);
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setTitle("PT 일지");
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

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTitle = new JPanel();
		contentPane.add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new MigLayout("", "[482px]", "[31px]"));
		
		JLabel lblTitle = new JLabel("제목   ");
		lblTitle.setFont(new Font("D2Coding", Font.PLAIN, 17));
		panelTitle.add(lblTitle, "cell 0 0,grow");
		
		textTitle = new JTextField();
		textTitle.setFont(new Font("D2Coding", Font.PLAIN, 17));
		panelTitle.add(textTitle, "cell 0 0,alignx right,aligny top");
		textTitle.setColumns(40);
		textTitle.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		textContents = new JTextArea();
		textContents.setFont(new Font("D2Coding", Font.PLAIN, 17));
		scrollPane.setViewportView(textContents);
		textContents.setEditable(false);
		
	}


}
