package edu.java.view.member;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import edu.java.model.MemberDiary;
import edu.java.services.MemberDiaryService;
import net.miginfocom.swing.MigLayout;

public class MemberDiaryContentFrame extends JFrame {

	private JPanel contentPane;
	private Component parent;
	
	private JTextField textTitle;
	private JTextArea textContents;
	
	private int idx;
	
	private final MemberDiaryService mdService = new MemberDiaryService();
	
	/**
	 * Launch the application.
	 */
	public static void showMemberDiaryContent(Component parent, int idx) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberDiaryContentFrame frame = new MemberDiaryContentFrame(parent, idx);
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
	public MemberDiaryContentFrame(Component parent, int idx) {
		this.parent = parent;
		this.idx = idx;
		initialize();
		readDiary();
	}
	
	private void readDiary() {
		MemberDiary md = mdService.selectDiaryInfo(idx);
		
		textTitle.setText(md.getTitle());
		String str = md.getContent().replaceAll("<br>", "\n");
		textContents.setText(str);
	}
	
	public void initialize() {
		setTitle("글 자세히 보기");
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
		panelTitle.setLayout(new MigLayout("", "[472px]", "[31px]"));
		
		JLabel lblTitle = new JLabel("제목   ");
		lblTitle.setFont(new Font("D2Coding", Font.PLAIN, 17));
		panelTitle.add(lblTitle, "cell 0 0,alignx left,aligny top");
		
		textTitle = new JTextField();
		textTitle.setFont(new Font("D2Coding", Font.PLAIN, 17));
		panelTitle.add(textTitle, "cell 0 0,growx,aligny top");
		textTitle.setColumns(40);
		textTitle.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		textContents = new JTextArea();
		textContents.setFont(new Font("D2Coding", Font.PLAIN, 17));
		scrollPane.setViewportView(textContents);
		textContents.setEditable(false);
		
		JPanel panelBtn = new JPanel();
		contentPane.add(panelBtn, BorderLayout.SOUTH);
		
	}


}
