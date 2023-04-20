package edu.java.view.trainer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import edu.java.controller.ProfileDaoImpl;
import edu.java.controller.TrainerDaoImpl;
import edu.java.model.Members;
import edu.java.model.Trainer;
import edu.java.services.ProfileService;
import edu.java.services.TrainerService;
import edu.java.view.LoginView;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class TrainerView {

	private JFrame frame;
	private Component parent;
	private String userId;
	
	private JTabbedPane tabbedPane;
	
	// 회원 관리
	private static final String[] MEMBER_COLUMN_NAMES = {"No", "ID", "이름", "전화번호"};
	private DefaultTableModel modelMember;
	
	private JPanel paneMemberManagement;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panelBtn;
	private JButton btnDownForm;
	private JButton btnCreateMember;
	private JButton btnUpdateMember;
	private JButton btnDeleteMember;
	private JButton btnPtMember;
	
	// 마이페이지
	private JPanel paneTrainerMyPage;
	private JLabel lblImage;
//	private JButton btnImageChage;
	private JLabel showBirth;
	private JLabel showName;
	private JLabel lblEmail;
	private JLabel showEmail;
	private JLabel lblPhone;
	private JLabel showPhone;
	private JLabel lblId;
	private JLabel showID;
	private JLabel lblMyPageIcon;
	private JLabel lblMyPage;
	private JButton btnLogout;
	
	// dao
	private final TrainerDaoImpl trDao = TrainerDaoImpl.getInstance();
	
	// service
	ProfileService proService = new ProfileService();
	TrainerService trService = new TrainerService();

	/**
	 * Launch the application.
	 */
	public static void TrainerViewShow(Component parent, String id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainerView window = new TrainerView(parent, id);
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
	public TrainerView(Component parent, String id) {
		this.parent = parent;
		this.userId = id;
		initialize();
		readTrainerInfo();
//		readUserImage();
		setTableModel();
	}
	
	// 사진 파일 set
//	public void readUserImage() {
//		String file = proService.ByteArrayToimage(userId);
//		if(file != null) {
//			Image memberImg = new ImageIcon(file).getImage();
//			Image chageMemberImg = memberImg.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
//			lblImage.setIcon(new ImageIcon(chageMemberImg));
//		}
//	}
	
	// 마이페이지 set
	public void readTrainerInfo() {
		Trainer trainer = trDao.selectTrainerInfo(userId);
		
		showID.setText(userId);
		showName.setText(trainer.getName());		
		showBirth.setText(trainer.getBirth());
		showEmail.setText(trainer.getEmail());
		showPhone.setText(trainer.getPhone());
	}
	
	// 회원 리스트
	public void resetTableModel() {
		modelMember = new DefaultTableModel(null, MEMBER_COLUMN_NAMES);
		setTableModel();
		table.setModel(modelMember);
	}
	
	// 맨 처음 화면에 보여지는 리스트
	public void setTableModel() {
		List<Members> list = trService.loadAllMemberList(userId);
		System.out.println(list);
		int count = 1;
		for(Members m : list) {
			Object[] row = {count++, m.getId(), m.getName(), m.getPhone()};
			modelMember.addRow(row);
		}
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
		tabbedPane.setFont(new Font("Gulim", Font.PLAIN, 12));
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		// 회원등록 - 회원 리스트, 회원 PT일지
		tabbedPane.addTab("회원 관리", paneMemberManagement());
		
		// 마이페이지
		tabbedPane.addTab("마이페이지", paneTrainerMyPage());
		
		lblEmail = new JLabel("이메일");
		lblEmail.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblEmail.setBounds(12, 377, 96, 25);
		paneTrainerMyPage.add(lblEmail);
		
		showEmail = new JLabel("test@test.com");
		showEmail.setFont(new Font("D2Coding", Font.PLAIN, 17));
		showEmail.setBounds(120, 377, 166, 25);
		paneTrainerMyPage.add(showEmail);
		
		lblPhone = new JLabel("전화번호");
		lblPhone.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblPhone.setBounds(12, 412, 96, 25);
		paneTrainerMyPage.add(lblPhone);
		
		showPhone = new JLabel("010-0000-0000");
		showPhone.setFont(new Font("D2Coding", Font.PLAIN, 17));
		showPhone.setBounds(120, 412, 166, 25);
		paneTrainerMyPage.add(showPhone);
		
		lblId = new JLabel("ID");
		lblId.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblId.setBounds(224, 64, 76, 25);
		paneTrainerMyPage.add(lblId);
		
		showID = new JLabel("72884444");
		showID.setFont(new Font("D2Coding", Font.PLAIN, 17));
		showID.setBounds(312, 64, 106, 25);
		paneTrainerMyPage.add(showID);
		
		lblMyPageIcon = new JLabel("");
		lblMyPageIcon.setBounds(12, 13, 37, 32);
		paneTrainerMyPage.add(lblMyPageIcon);
		
		lblMyPage = new JLabel("마이페이지");
		lblMyPage.setFont(new Font("D2Coding", Font.BOLD, 20));
		lblMyPage.setBounds(61, 10, 269, 35);
		paneTrainerMyPage.add(lblMyPage);
		
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
		paneTrainerMyPage.add(btnLogout);
		
		
	}
	
	// 회원등록
	private JPanel paneMemberManagement() {
		paneMemberManagement = new JPanel();
		paneMemberManagement.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		paneMemberManagement.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		modelMember = new DefaultTableModel(null, MEMBER_COLUMN_NAMES);
		table.setModel(modelMember);
		table.setFont(new Font("D2Coding", Font.PLAIN, 17));
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		panelBtn = new JPanel();
		paneMemberManagement.add(panelBtn, BorderLayout.EAST);
		panelBtn.setLayout(new BoxLayout(panelBtn, BoxLayout.Y_AXIS));
		
//		btnDownForm = new JButton("회원등록폼");
//		btnDownForm.setFont(new Font("D2Coding", Font.PLAIN, 15));
//		panelBtn.add(btnDownForm);
//		panelBtn.add(Box.createVerticalStrut(5));
		
		btnCreateMember = new JButton("회원 등록");
		btnCreateMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrainerMemberInfoCreateFrame.showMemberInfoCreate(frame, TrainerView.this, userId);
			}
		});
		btnCreateMember.setFont(new Font("D2Coding", Font.PLAIN, 15));
		panelBtn.add(btnCreateMember);
		panelBtn.add(Box.createVerticalStrut(5));
		
		btnUpdateMember = new JButton("회원 수정");
		btnUpdateMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				
				if(row == -1) {
					JOptionPane.showMessageDialog(
							frame,
							"수정할 행을 먼저 선택하세요.",
							"경고",
							JOptionPane.WARNING_MESSAGE
					);
					
					return;
				}
				
				String id = (String) modelMember.getValueAt(row, 1);
				
				TrainerMemberInfoUpdateFrame.showMemberInfoUpdate(frame, id, userId, TrainerView.this);
			}
		});
		btnUpdateMember.setFont(new Font("D2Coding", Font.PLAIN, 15));
		panelBtn.add(btnUpdateMember);
		panelBtn.add(Box.createVerticalStrut(5));
		
		btnDeleteMember = new JButton("회원 삭제");
		btnDeleteMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteMember();
			}
		});
		btnDeleteMember.setFont(new Font("D2Coding", Font.PLAIN, 15));
		panelBtn.add(btnDeleteMember);
		panelBtn.add(Box.createVerticalGlue());
		
		btnPtMember = new JButton("회원 PT 일지");
		btnPtMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				
				if(row == -1) {
					JOptionPane.showMessageDialog(
							frame,
							"회원을 먼저 선택하세요.",
							"경고",
							JOptionPane.WARNING_MESSAGE
					);
					
					return;
				}
				
				String id = (String) modelMember.getValueAt(row, 1);
				frame.setVisible(false);
				TrainerMemberPtDiaryView.showTrainerMemberPtDiary(frame, userId, id);
			}
		});
		btnPtMember.setFont(new Font("Dialog", Font.PLAIN, 12));
		panelBtn.add(btnPtMember);
		
		return paneMemberManagement;
	}
	
	private void deleteMember() {
		// 테이블에서 선택된 행의 인덱스 찾기
		int row = table.getSelectedRow();
		
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
		
		String id = (String) modelMember.getValueAt(row, 1);
		
		if(confirm == JOptionPane.YES_OPTION) {
			trService.deleteMember(id);
			modelMember.removeRow(row);
			
			JOptionPane.showConfirmDialog(frame, "삭제 성공");
		}
		
		
	}

	// 마이페이지
	private JPanel paneTrainerMyPage() {
		paneTrainerMyPage = new JPanel();
		paneTrainerMyPage.setLayout(null);
		
		JLabel lblName = new JLabel("이름");
		lblName.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblName.setBounds(12, 307, 96, 25);
		paneTrainerMyPage.add(lblName);
		
		lblImage = new JLabel();
		lblImage.setBounds(12, 64, 200, 200);
		paneTrainerMyPage.add(lblImage);
		Image memberImg = new ImageIcon("images/default_image.png").getImage();
		Image chageMemberImg = memberImg.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
		lblImage.setIcon(new ImageIcon(chageMemberImg));
		
//		btnImageChage = new JButton("이미지 변경");
//		btnImageChage.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				String filePath = proService.OpenActionFolder(frame, userId);
//				Image memberImg = new ImageIcon(filePath).getImage();
//				Image chageMemberImg = memberImg.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
//				lblImage.setIcon(new ImageIcon(chageMemberImg));
//			}
//		});
//		btnImageChage.setFont(new Font("D2Coding", Font.PLAIN, 17));
//		btnImageChage.setBounds(12, 274, 200, 23);
//		paneTrainerMyPage.add(btnImageChage);
		
		showName = new JLabel("홍익인간");
		showName.setFont(new Font("D2Coding", Font.PLAIN, 17));
		showName.setBounds(120, 307, 166, 25);
		paneTrainerMyPage.add(showName);
		
		JLabel lblBirth = new JLabel("생년월일");
		lblBirth.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblBirth.setBounds(12, 342, 96, 25);
		paneTrainerMyPage.add(lblBirth);
		
		showBirth = new JLabel("0000.00.00");
		showBirth.setFont(new Font("D2Coding", Font.PLAIN, 17));
		showBirth.setBounds(120, 342, 166, 25);
		paneTrainerMyPage.add(showBirth);
		
		
		return paneTrainerMyPage;
	}
	
}
