package edu.java.view.trainer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import edu.java.controller.PtDiaryDaoImpl;
import edu.java.model.PtDiary;
import edu.java.services.PtDiaryService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TrainerMemberPtDiaryView {

	private JFrame frame;
	private Component parent;
	
	private static final String[] PT_COLUMN_NAMES = {"번호", "제목"};
	private DefaultTableModel modelPt;
	private JButton btnCreate;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JTable table;
	
	private String trId;
	private String mbId;
	
	// dao
	private final PtDiaryDaoImpl ptDao = PtDiaryDaoImpl.getInstance();
	
	// service
	private final PtDiaryService ptService = new PtDiaryService();
	
	
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
		setTableModel();
	}
	
	// 맨 처음 호출되는 모델
	public void setTableModel() {
		List<PtDiary> list = ptService.loadAllPtDiary(mbId);
		int count = 1;
		for(PtDiary p : list) {
			Object[] row = {count++, p.getTitle()};
			modelPt.addRow(row);
		}
		
	}
	
	// 리셋 모델
	public void resetTableModel() {
		modelPt = new DefaultTableModel(null, PT_COLUMN_NAMES);
		setTableModel();
		table.setModel(modelPt);
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
				TrainerView.TrainerViewShow(frame, trId);
			}
		});
		
		JPanel panelBtn = new JPanel();
		frame.getContentPane().add(panelBtn, BorderLayout.EAST);
		
		btnCreate = new JButton("PT 일지 등록");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrainerPtDiaryCreateFrame.showPtDiaryCreate(frame, TrainerMemberPtDiaryView.this
							, trId, mbId);
			}
		});
		btnCreate.setFont(new Font("D2Coding", Font.PLAIN, 15));
		panelBtn.add(btnCreate);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panelBtn.add(verticalStrut);
		
		btnUpdate = new JButton("PT 일지 수정");
		btnUpdate.addActionListener(new ActionListener() {
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
				
				List<PtDiary> list = ptDao.readMemberPtDiary(mbId);
				int idx = list.get(row).getPidx();				
				
				TrainerPtDiaryUpdateFrame.showPtDiaryUpdate(frame, TrainerMemberPtDiaryView.this
						, trId, mbId, idx);
			}
		});
		btnUpdate.setFont(new Font("D2Coding", Font.PLAIN, 15));
		panelBtn.add(btnUpdate);
		panelBtn.setLayout(new BoxLayout(panelBtn, BoxLayout.Y_AXIS));
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panelBtn.add(verticalStrut_1);
		
		btnDelete = new JButton("PT 일지 삭제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletePtDiary();
			}
		});
		btnDelete.setFont(new Font("D2Coding", Font.PLAIN, 15));
		panelBtn.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		modelPt = new DefaultTableModel(null, PT_COLUMN_NAMES);
		table.setModel(modelPt);
		table.setFont(new Font("D2Coding", Font.PLAIN, 17));
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	private void deletePtDiary() {
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
		
		List<PtDiary> list = ptService.loadAllPtDiary(mbId);
		int idx = list.get(row).getPidx();
		
		if(confirm == JOptionPane.YES_OPTION) {
			ptService.deletePtDiary(idx);
			modelPt.removeRow(row);
			
			JOptionPane.showMessageDialog(frame, "삭제 성공");
		}
		
	}

}
