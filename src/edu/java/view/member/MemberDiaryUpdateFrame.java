package edu.java.view.member;

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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import edu.java.model.MemberDiary;
import edu.java.model.PtDiary;
import edu.java.services.MemberDiaryService;
import net.miginfocom.swing.MigLayout;
import java.awt.CardLayout;

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
	
	private MemberView app;
	private String mbId;
	private int idx;
	
	private final MemberDiaryService mdService = new MemberDiaryService();
	
	/**
	 * Launch the application.
	 */
	public static void showMemberDiaryUpdate(Component parent, MemberView app, String mbId, int idx) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberDiaryUpdateFrame frame = new MemberDiaryUpdateFrame(parent, app, mbId, idx);
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
	public MemberDiaryUpdateFrame(Component parent, MemberView app, String mbId, int idx) {
		this.parent = parent;
		this.app = app;
		this.mbId = mbId;
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
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		textContents = new JTextArea();
		textContents.setFont(new Font("D2Coding", Font.PLAIN, 17));
		scrollPane.setViewportView(textContents);
		
		JPanel panelBtn = new JPanel();
		contentPane.add(panelBtn, BorderLayout.SOUTH);
		
		btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateDiary();
			}
		});
		btnUpdate.setFont(new Font("D2Coding", Font.PLAIN, 17));
		panelBtn.add(btnUpdate);
	}

	private void updateDiary() {
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
		
		MemberDiary mdiary = new MemberDiary(idx, mbId, title, contents, null, null);
		if(mdService.updateDiary(contentPane, mdiary) == 1) {
			JOptionPane.showMessageDialog(contentPane, "다이어리가 수정되었습니다.");
			
			app.resetTableDiary();
			
			dispose();
		}
	}

}
