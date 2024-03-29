package edu.java.view.member;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import edu.java.controller.MemberDaoImpl;
import edu.java.controller.TrainerDaoImpl;
import edu.java.model.MemberDiary;
import edu.java.model.Members;
import edu.java.model.PtDiary;
import edu.java.model.Trainer;
import edu.java.services.MemberDiaryService;
import edu.java.services.PtDiaryService;
import edu.java.view.LoginView;
import edu.java.view.trainer.TrainerPtDiaryContentFrame;

public class MemberView {

	private JFrame frame;	
	private Component parent;
	private String userId;
	
	private JTabbedPane tabbedPane;
	private JPanel paneDiary;
	private JPanel panePT;		
	private JPanel paneMyPage;

	// 마이페이지	
	private JLabel lblImage;
	private JLabel showName;
	private JLabel showBirth;
	private JLabel showID;
	private JLabel showTrainer;
	private JButton btnshowTrainer;
	private JButton btnLogout;
	
	
	// PT 일지
	// 테이블 컬럼 이름
	private static final String[] PT_COLUMN_NAMES = {"NO", "제목", "작성자"};
	private DefaultTableModel modelPt;
	private JScrollPane scrollPanePt;	
	
	
	
	// 다이어리
	// 테이블 컬럼 이름
	private static final String[] DIARY_COLUMN_NAMES = {"NO", "제목", "내용"};
	private DefaultTableModel modelDiary;	
	private JButton btnAddDiary;
	private JButton btnEditDiary;
	private JButton btnDeleteDiary;
	private JScrollPane scrollPaneDiary;
	private JTable tableDiary;
	private JPanel panelBtnFrame;
	private Component verticalStrut;
	private Component verticalStrut_1;
	private JLabel lblPhone;
	private JLabel showPhone;
	private JTable tablePt;
	
	// dao
	private final MemberDaoImpl mDao = MemberDaoImpl.getInstance();
	private final TrainerDaoImpl trDao = TrainerDaoImpl.getInstance();
	
	// service
	private final PtDiaryService ptService = new PtDiaryService();
	private final MemberDiaryService mdService = new MemberDiaryService();

	// 상수
	private Members member;
	
	/**
	 * Launch the application.
	 */
	public static void MemberViewShow(Component parent, String id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberView window = new MemberView(parent, id);
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
	public MemberView(Component parent, String id) {
		this.parent = parent;
		this.userId = id;
		initialize();
		readMemberInfo();
		setTablePtModel();
		setTableDiaryModal();
	}
	
	// 마이페이지 set
	public void readMemberInfo() {
		member = mDao.readSelectMemberInfo(userId);
		Trainer trainer = trDao.selectTrainerInfo(member.getTrainer());
		
		showID.setText(userId);
		showName.setText(member.getName());
		showPhone.setText(member.getPhone());
		showBirth.setText(member.getBirth());
		showTrainer.setText(trainer.getName());
	}
	
	// Pt 일지 set
	public void setTablePtModel() {
		List<PtDiary> list = ptService.loadAllPtDiary(userId);
		
		int count = 1;
		for(PtDiary p : list) {
			String trainer = trDao.selectTrainerInfo(p.getTrId()).getName();
			Object[] row =  {count++, p.getTitle(), trainer};
			modelPt.addRow(row);
		}
		
	}
	
	// 다이어리 set
	public void setTableDiaryModal() {
		List<MemberDiary> list = mdService.loadAllDiary(userId);
		
		int count = 1;
		for(MemberDiary m : list) {
			String str = m.getContent().replaceAll("<br>", "\n");
			Object[] row = {count++, m.getTitle(), str};
			modelDiary.addRow(row);
		}
	}
	
	// 다이어리 리셋
	public void resetTableDiary() {
		modelDiary = new DefaultTableModel(null, DIARY_COLUMN_NAMES);
		setTableDiaryModal();
		tableDiary.setModel(modelDiary);
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("사용자 화면");
		int x = 100;
		int y = 100;
		if(parent != null) {
			x = parent.getX();
			y = parent.getY();
		}		
		frame.setBounds(x, y, 451, 659);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		// 다이어리
		tabbedPane.addTab("다이어리", paneDiary());		
		// PT 일지
		tabbedPane.addTab("PT 일지", panePT());		
		// 마이페이지
		tabbedPane.addTab("마이페이지", paneMyPage());
		
		lblPhone = new JLabel("전화번호");
		lblPhone.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblPhone.setBounds(12, 377, 96, 25);
		paneMyPage.add(lblPhone);
		
		showPhone = new JLabel("010-2222-2222");
		showPhone.setFont(new Font("D2Coding", Font.PLAIN, 17));
		showPhone.setBounds(120, 377, 166, 25);
		paneMyPage.add(showPhone);
	}
	
	// 다이어리
	private JPanel paneDiary() {
		paneDiary = new JPanel();
		paneDiary.setLayout(new BorderLayout(0, 0));
		
		scrollPaneDiary = new JScrollPane();
		paneDiary.add(scrollPaneDiary, BorderLayout.CENTER);
		
		tableDiary = new JTable();
		// 더블 클릭 수정 block
		modelDiary = new DefaultTableModel(null, DIARY_COLUMN_NAMES) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableDiary.setModel(modelDiary);
		tableDiary.setFont(new Font("D2Coding ligature", Font.PLAIN, 17));
		scrollPaneDiary.setViewportView(tableDiary);
		
		tableDiary.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable t = (JTable) e.getSource();
				
				if(e.getClickCount() == 2) {
					Point p = e.getPoint();
					int i = t.rowAtPoint(p);
					
					List<MemberDiary> list = mdService.loadAllDiary(userId);
					int idx = list.get(i).getMidx();
					
					MemberDiaryContentFrame.showMemberDiaryContent(frame, idx);
				}
			}
		});
		
		panelBtnFrame = new JPanel();
		paneDiary.add(panelBtnFrame, BorderLayout.EAST);
		
		btnAddDiary = new JButton("글 쓰기");
		btnAddDiary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberDiaryCreateFrame.showMemberDiaryCreate(frame, MemberView.this, userId);
			}
		});
		btnAddDiary.setFont(new Font("D2Coding", Font.PLAIN, 17));
		panelBtnFrame.add(btnAddDiary);
		
		verticalStrut = Box.createVerticalStrut(20);
		panelBtnFrame.add(verticalStrut);
		
		btnEditDiary = new JButton("글 수정");
		btnEditDiary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableDiary.getSelectedRow();
				
				if(row == -1) {
					JOptionPane.showMessageDialog(
							frame,
							"수정할 행을 먼저 선택하세요.",
							"경고",
							JOptionPane.WARNING_MESSAGE
					);
					
					return;
				}
				
				List<MemberDiary> list = mdService.loadAllDiary(userId);
				int idx = list.get(row).getMidx();				
				
				MemberDiaryUpdateFrame.showMemberDiaryUpdate(frame, MemberView.this, userId, idx);
			}
		});
		btnEditDiary.setFont(new Font("D2Coding", Font.PLAIN, 17));
		panelBtnFrame.add(btnEditDiary);
		
		verticalStrut_1 = Box.createVerticalStrut(20);
		panelBtnFrame.add(verticalStrut_1);
		
		btnDeleteDiary = new JButton("글 삭제");
		btnDeleteDiary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteDiary();
			}
		});
		btnDeleteDiary.setFont(new Font("D2Coding", Font.PLAIN, 17));
		panelBtnFrame.add(btnDeleteDiary);

		panelBtnFrame.setLayout(new BoxLayout(panelBtnFrame, BoxLayout.Y_AXIS));
		
		return paneDiary;
	}
	
	
	private void deleteDiary() {
		// 테이블에서 선택된 행의 인덱스 찾기
		int row = tableDiary.getSelectedRow();
		
		if(row == -1) {
			JOptionPane.showMessageDialog(
					frame,
					"삭제할 행을 먼저 선택하세요.",
					"경고",
					JOptionPane.WARNING_MESSAGE
			);
			
			return;
		}
		
		int confirm = JOptionPane.showConfirmDialog(
				frame,
				"정말 삭제하시겠습니까?",
				"삭제 확인",
				JOptionPane.YES_NO_OPTION
		);
		
		List<MemberDiary> list = mdService.loadAllDiary(userId);
		int idx = list.get(row).getMidx();
		
		if(confirm == JOptionPane.YES_OPTION) {
			mdService.deleteDiary(idx);
			modelDiary.removeRow(row);
			
			JOptionPane.showMessageDialog(frame, "다이어리가 삭제되었습니다.");
		}
		
		
	}

	// PT 일지 Panel
	private JPanel panePT() {
		
		panePT = new JPanel();
		panePT.setLayout(null);
		
		JLabel lblpanePt = new JLabel("PT 일지");
		lblpanePt.setFont(new Font("D2Coding", Font.BOLD, 20));
		lblpanePt.setBounds(61, 7, 269, 35);
		panePT.add(lblpanePt);
		
		JLabel lblPtIcon = new JLabel("");
		lblPtIcon.setBounds(12, 10, 37, 32);
		panePT.add(lblPtIcon);
		Image iconImg = new ImageIcon("images/right-arrow.png").getImage();
		Image chageIconImg = iconImg.getScaledInstance(lblPtIcon.getWidth(), lblPtIcon.getHeight(), Image.SCALE_SMOOTH);
		lblPtIcon.setIcon(new ImageIcon(chageIconImg));
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 50, 430, 541);
		panePT.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		scrollPanePt = new JScrollPane();
		panel.add(scrollPanePt, BorderLayout.CENTER);
		
		tablePt = new JTable();
		// 더블 클릭 수정 block
		modelPt = new DefaultTableModel(null, PT_COLUMN_NAMES) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablePt.setModel(modelPt);
		tablePt.setFont(new Font("D2Coding", Font.PLAIN, 17));
		scrollPanePt.setViewportView(tablePt);
		
		tablePt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable t = (JTable) e.getSource();
				
				if(e.getClickCount() == 2) {
					Point p = e.getPoint();
					int i = t.rowAtPoint(p);
					
					List<PtDiary> list = ptService.loadAllPtDiary(userId);
					int idx = list.get(i).getPidx();
					
					TrainerPtDiaryContentFrame.showPtDiaryContents(frame, idx);
					
				}
			}
		});
		
		return panePT;
	}
	
	
	// 마이페이지 Panel
	private JPanel paneMyPage() {
		
		paneMyPage = new JPanel();
		paneMyPage.setLayout(null);
		
		JLabel lblName = new JLabel("이름");
		lblName.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblName.setBounds(12, 307, 96, 25);
		paneMyPage.add(lblName);
		
		lblImage = new JLabel();
		lblImage.setBounds(12, 64, 200, 200);
		paneMyPage.add(lblImage);
		Image memberImg = new ImageIcon("images/default_image.png").getImage();
		Image chageMemberImg = memberImg.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
		lblImage.setIcon(new ImageIcon(chageMemberImg));
		
//		btnImageChage = new JButton("이미지 변경");
//		btnImageChage.setFont(new Font("D2Coding", Font.PLAIN, 17));
//		btnImageChage.setBounds(12, 274, 200, 23);
//		paneMyPage.add(btnImageChage);
		
		showName = new JLabel("홍익인간");
		showName.setFont(new Font("D2Coding", Font.PLAIN, 17));
		showName.setBounds(120, 307, 166, 25);
		paneMyPage.add(showName);
		
		JLabel lblBirth = new JLabel("생년월일");
		lblBirth.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblBirth.setBounds(12, 342, 96, 25);
		paneMyPage.add(lblBirth);
		
		showBirth = new JLabel("0000.00.00");
		showBirth.setFont(new Font("D2Coding", Font.PLAIN, 17));
		showBirth.setBounds(120, 342, 166, 25);
		paneMyPage.add(showBirth);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblId.setBounds(224, 64, 76, 25);
		paneMyPage.add(lblId);
		
		showID = new JLabel("72884444");
		showID.setFont(new Font("D2Coding", Font.PLAIN, 17));
		showID.setBounds(312, 64, 106, 25);
		paneMyPage.add(showID);
		
		JLabel lblMyPage = new JLabel("마이페이지");
		lblMyPage.setFont(new Font("D2Coding", Font.BOLD, 20));
		lblMyPage.setBounds(61, 7, 269, 35);
		paneMyPage.add(lblMyPage);
		
		JLabel lblMyPageIcon = new JLabel("");
		lblMyPageIcon.setBounds(12, 10, 37, 32);
		paneMyPage.add(lblMyPageIcon);
		Image iconImg = new ImageIcon("images/right-arrow.png").getImage();
		Image chageIconImg = iconImg.getScaledInstance(lblMyPageIcon.getWidth(), lblMyPageIcon.getHeight(), Image.SCALE_SMOOTH);
		lblMyPageIcon.setIcon(new ImageIcon(chageIconImg));
		
		JLabel lblTrainer = new JLabel("트레이너");
		lblTrainer.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblTrainer.setBounds(224, 99, 76, 25);
		paneMyPage.add(lblTrainer);
		
		showTrainer = new JLabel("ㅇㅇ");
		showTrainer.setFont(new Font("D2Coding", Font.PLAIN, 17));
		showTrainer.setBounds(312, 99, 106, 25);
		paneMyPage.add(showTrainer);
		
		// 트레이너 정보 열람 버튼
		btnshowTrainer = new JButton("트레이너 정보보기");
		btnshowTrainer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberTrainerInfoFrame.showTrainerInfoFrame(frame, member.getTrainer());
			}
		});
		btnshowTrainer.setFont(new Font("D2Coding", Font.PLAIN, 17));
		btnshowTrainer.setBounds(224, 134, 194, 23);
		paneMyPage.add(btnshowTrainer);
		
		// 로그아웃 버튼
		btnLogout = new JButton("로그아웃");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(
						frame,
						"로그아웃 하시겠습니까?",
						"로그아웃 확인",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE
				);
				System.out.println(result);
				if(result == 0) {
					frame.setVisible(false);
					LoginView.showLoginView(frame);
				}
			}
			
		});
		btnLogout.setFont(new Font("D2Coding", Font.PLAIN, 17));
		btnLogout.setBounds(312, 546, 106, 35);
		paneMyPage.add(btnLogout);
		
		return paneMyPage;		
	}
	
}

