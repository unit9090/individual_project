package edu.java.view.trainer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class TrainerPtDiaryCreateFrame extends JFrame {

	private JPanel contentPane;
	
	private JTextField textTitle;
	private JTextArea textContents;
	private JButton btnCreate;
	
	private Component parent;
	private TrainerMemberPtDiaryView app;
	private String trId;
	private String mbId;
	
	private final PtDiaryService ptService = new PtDiaryService();

	/**
	 * Launch the application.
	 */
	public static void showPtDiaryCreate(Component parent, TrainerMemberPtDiaryView app
			, String trId, String mbId) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainerPtDiaryCreateFrame frame = new TrainerPtDiaryCreateFrame(parent, app, trId, mbId);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public TrainerPtDiaryCreateFrame(Component parent, TrainerMemberPtDiaryView app
			, String trId, String mbId) {
		this.parent = parent;
		this.app = app;
		this.trId = trId;
		this.mbId = mbId;
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
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		textContents = new JTextArea();
		textContents.setFont(new Font("D2Coding", Font.PLAIN, 17));
		scrollPane.setViewportView(textContents);
		
		JPanel panelBtn = new JPanel();
		contentPane.add(panelBtn, BorderLayout.SOUTH);
		
		btnCreate = new JButton("등록");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createPtDiary();
			}
		});
		btnCreate.setFont(new Font("D2Coding", Font.PLAIN, 17));
		panelBtn.add(btnCreate);
	}

	private void createPtDiary() {
		String title = textTitle.getText();
		String contents = textContents.getText();
		System.out.println(contents);
		
		// 등록 전 빈칸 확인
		if(textTitle.getText().isEmpty() || textContents.getText().isEmpty()) {
			JOptionPane.showMessageDialog(
					contentPane,
					"빈 칸이 있습니다. 확인해주세요.",
					"공백 확인",
					JOptionPane.INFORMATION_MESSAGE
			);
			
			return;
		}
		
		contents = contents.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		System.out.println(contents);
		
		PtDiary pt = new PtDiary(0, trId, mbId, title, contents);
		if(ptService.createNewPtDiary(contentPane, pt) == 1) {
			JOptionPane.showMessageDialog(contentPane, "Pt 다이어리가 등록되었습니다.");
			
			app.resetTableModel();
			
			dispose();
		}
		
	}

}
