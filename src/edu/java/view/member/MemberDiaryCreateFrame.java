package edu.java.view.member;

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

import edu.java.model.MemberDiary;
import edu.java.services.MemberDiaryService;
import net.miginfocom.swing.MigLayout;

public class MemberDiaryCreateFrame extends JFrame {

	private JPanel contentPane;
	private Component parent;
	
	private JTextField textTitle;
	private JTextArea textContents;
	private JButton btnCreate;
	
	private String mbId;
	private MemberView app;
	
	private final MemberDiaryService mdService = new MemberDiaryService();
	
	/**
	 * Launch the application.
	 */
	public static void showMemberDiaryCreate(Component parent, MemberView app, String mbId) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberDiaryCreateFrame frame = new MemberDiaryCreateFrame(parent, app, mbId);
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
	public MemberDiaryCreateFrame(Component parent, MemberView app, String mbId) {
		this.parent = parent;
		this.mbId = mbId;
		this.app = app;
		initialize();
		
	}
	
	public void initialize() {
		setTitle("글 쓰기");
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
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTitle = new JPanel();
		contentPane.add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new MigLayout("", "[47px][425px]", "[31px]"));
		
		JLabel lblTitle = new JLabel("제목   ");
		lblTitle.setFont(new Font("D2Coding", Font.PLAIN, 17));
		panelTitle.add(lblTitle, "cell 0 0,alignx left,aligny center");
		
		textTitle = new JTextField();
		textTitle.setColumns(40);
		textTitle.setFont(new Font("D2Coding", Font.PLAIN, 17));
		panelTitle.add(textTitle, "cell 1 0,alignx center,aligny center");
		
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
				createDiary();
			}
		});
		btnCreate.setFont(new Font("D2Coding", Font.PLAIN, 17));
		panelBtn.add(btnCreate);
	}

	private void createDiary() {
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
		
		MemberDiary mdiary = new MemberDiary(0, mbId, title, contents, null, null);
		if(mdService.createNewDiary(contentPane, mdiary) == 1) {
			JOptionPane.showMessageDialog(contentPane, "다이어리가 등록되었습니다.");
			
			app.resetTableDiary();
			
			dispose();
		}
		
	}

}
