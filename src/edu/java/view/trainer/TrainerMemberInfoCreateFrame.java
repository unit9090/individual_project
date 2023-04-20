package edu.java.view.trainer;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import edu.java.model.Members;
import edu.java.services.JoinViewService;
import edu.java.services.TextFilter;
import edu.java.services.TrainerService;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class TrainerMemberInfoCreateFrame extends JFrame {

	private JPanel contentPane;
	private Component parent;
	private JTextField textName;
	private JTextField textBirth;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textHeight;
	private JTextField textWeight;
	private JTextField textPhone;
	private JButton btnCreate;
	private JRadioButton radioGender1;
	private JRadioButton radioGender2;
	
	private String userId;
	// service
	private JoinViewService joinService = new JoinViewService();
	private TrainerService trService = new TrainerService();

	// view
	private TrainerView app;
	
	/**
	 * Launch the application.
	 */
	public static void showMemberInfoCreate(Component parent, TrainerView app, String userId) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainerMemberInfoCreateFrame frame = new TrainerMemberInfoCreateFrame(parent, app, userId);
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
	public TrainerMemberInfoCreateFrame(Component parent, TrainerView app, String userId) {
		this.parent = parent;
		this.userId = userId;
		this.app = app;
		System.out.println(userId);
		initialize();
	}
	
	private void initialize() {
		setTitle("회원 정보 입력");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int x = 100;	// 부모 컴포넌트가 null인 경우에 사용할 x 좌표 기본값.
		int y = 100;	// 부모 컴포넌트가 null인 경우에 사용할 y 좌표 기본값.
		if(parent != null) {
			x = parent.getX() + parent.getWidth();	// 부모 컴포넌트의 x 좌표
			y = parent.getY();	// 부모 컴포넌트의 y 좌표
		}		
		setBounds(x, y, 421, 401);	// 다이얼로그 좌표(x, y), 크기(가로, 세로)
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("이름");
		lblName.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblName.setBounds(12, 10, 112, 36);
		contentPane.add(lblName);
		
		textName = new JTextField();
		textName.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textName.setColumns(10);
		textName.setBounds(136, 10, 251, 36);
		contentPane.add(textName);
		
		JLabel lblBirth = new JLabel("생년월일");
		lblBirth.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblBirth.setBounds(12, 56, 112, 36);
		contentPane.add(lblBirth);
		
		textBirth = new JTextField();
		textBirth.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textBirth.setColumns(10);
		textBirth.setBounds(136, 56, 251, 36);
		contentPane.add(textBirth);
		textBirth.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				TextFilter.checkBirth(contentPane, e);
			}
		});
		
		radioGender2 = new JRadioButton("여자");
		buttonGroup.add(radioGender2);
		radioGender2.setFont(new Font("D2Coding", Font.PLAIN, 17));
		radioGender2.setBackground(Color.LIGHT_GRAY);
		radioGender2.setBounds(272, 102, 90, 36);
		contentPane.add(radioGender2);
		
		radioGender1 = new JRadioButton("남자");
		buttonGroup.add(radioGender1);
		radioGender1.setFont(new Font("D2Coding", Font.PLAIN, 17));
		radioGender1.setBackground(Color.LIGHT_GRAY);
		radioGender1.setBounds(138, 102, 90, 36);
		contentPane.add(radioGender1);
		
		JLabel lblGender = new JLabel("성별");
		lblGender.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblGender.setBounds(12, 102, 112, 36);
		contentPane.add(lblGender);
		
		textHeight = new JTextField(3);
		textHeight.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textHeight.setColumns(10);
		textHeight.setBounds(136, 198, 251, 36);
		contentPane.add(textHeight);
		textHeight.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				TextFilter.checkInt(contentPane, e);
				if(textHeight.getText().length() > 2) {
					e.consume();
				}
			}
		});
		
		JLabel lblHeight = new JLabel("신장");
		lblHeight.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblHeight.setBounds(12, 198, 112, 36);
		contentPane.add(lblHeight);
		
		JLabel lblWeight = new JLabel("몸무게");
		lblWeight.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblWeight.setBounds(12, 244, 112, 36);
		contentPane.add(lblWeight);
		
		textWeight = new JTextField(3);
		textWeight.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textWeight.setColumns(10);
		textWeight.setBounds(136, 244, 251, 36);
		contentPane.add(textWeight);
		textWeight.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				TextFilter.checkInt(contentPane, e);
				if(textWeight.getText().length() > 2) {
					e.consume();
				}
			}
		});
		
		JLabel lblPhone = new JLabel("전화번호");
		lblPhone.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblPhone.setBounds(12, 152, 112, 36);
		contentPane.add(lblPhone);
		
		textPhone = new JTextField();
		textPhone.setFont(new Font("D2Coding", Font.PLAIN, 17));
		textPhone.setBounds(136, 152, 251, 36);
		contentPane.add(textPhone);
		textPhone.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				TextFilter.checkPhone(contentPane, e);
			}
		});
		
		btnCreate = new JButton("등록");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createMember();
			}
		});
		btnCreate.setFont(new Font("D2Coding", Font.BOLD, 17));
		btnCreate.setBounds(152, 316, 100, 36);
		contentPane.add(btnCreate);
		
	}
	
	
	private void createMember() {
		String id = textPhone.getText().replaceAll("-", "");
		String pwd = textPhone.getText().replaceAll("-", "");
		String name = textName.getText();
		String phone = textPhone.getText();
		String gender = "";
		if(radioGender1.isSelected()) gender = "M";
		else gender = "F";
		String birth = textBirth.getText();
		int height = Integer.parseInt(textHeight.getText());
		int weight = Integer.parseInt(textWeight.getText());
		
		Members member = new Members(id, userId, name, gender, birth, phone, height, weight);
		
		// 회원 등록 시 빈칸 확인
		JTextField[] field = {textName, textPhone, textBirth, textHeight, textWeight};
		
		for(JTextField f : field) {
			if(f.getText().isEmpty()) {
				JOptionPane.showMessageDialog(
						contentPane,
						"빈 칸이 있습니다. 확인해주세요.",
						"공백 확인",
						JOptionPane.INFORMATION_MESSAGE
				);
				
				return;
			}
		}
		
		if(!(radioGender1.isSelected() || radioGender2.isSelected())) {
			JOptionPane.showMessageDialog(
					contentPane,
					"성별을 선택해주세요.",
					"공백 확인",
					JOptionPane.INFORMATION_MESSAGE
			);
			
			return;
		}
		
		// 빈 칸 확인 후 dao 호출
		// 성공 시 완료 메시지와 페이지 닫기
		// table 리셋
		if(trService.createNewMember(contentPane, member) == 1) {
			JOptionPane.showMessageDialog(contentPane, "회원이 등록되었습니다.");
			
			// TODO: 테이블 리셋
			app.resetTableModel();
			
			dispose();
		}
		
	}

}
