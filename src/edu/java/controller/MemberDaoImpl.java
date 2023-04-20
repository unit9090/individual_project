package edu.java.controller;

import edu.java.model.Members;
import oracle.jdbc.OracleDriver;

// member
import static edu.java.model.Members.Entity.*;
// DB
import static edu.java.ojdbc.OracleConnect.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImpl implements MemberDao {
	
	private static MemberDaoImpl instance = null;
	private MemberDaoImpl() {}
	public static MemberDaoImpl getInstance() {
		if(instance == null) {
			instance = new MemberDaoImpl();
		}
		
		return instance;
	}
	
	// 오라클 DB에 접속한 Connection 객체를 리턴.
	private Connection getConnection() throws SQLException {
		// 오라클 JDBC 드라이버(라이브러리)를 등록.
		DriverManager.registerDriver(new OracleDriver());
		
		// 오라클 DB에 접속.
		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		System.out.println(conn);
		
		return conn;
	}
	
	// Resources close(overloading)
	private void closeResources(Connection conn, Statement stmt) throws SQLException {
		stmt.close();
		conn.close();		
	}
	
	private void closeResource(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
		rs.close();
		closeResources(conn, stmt);
	}

	// Member 생성
	private static final String SQL_INSERT = 
			"INSERT INTO " + TBL_NAME
			+ " (" + COL_ID + ", " + COL_TRAINER + ", " + COL_NAME + ", " + COL_GENDER
			+ ", " + COL_BIRTH + ", "+ COL_PHONE + ", " + COL_HEIGHT
			+ ", " + COL_WEIGHT
			+ ") VALUES "
			+ " (?, ?, ?, ?, ?, ?, ?, ?)";
	
	@Override
	public int createMember(Members member) {
		// 리턴 상수
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		System.out.println(SQL_INSERT);
		
		try {
			conn = getConnection();
			
			stmt = conn.prepareStatement(SQL_INSERT);			
			stmt.setString(1, member.getId());
			stmt.setString(2, member.getTrainer());
			stmt.setString(3, member.getName());
			stmt.setString(4, member.getGender());
			stmt.setString(5, member.getBirth());
			stmt.setString(6, member.getPhone());
			stmt.setInt(7, member.getHeight());
			stmt.setInt(8, member.getWeight());
			
			result = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
		
	}
	
	// 모든 멤버 읽기
	private static final String SQL_SELECT_ALL =
			"SELECT * FROM " + TBL_NAME + " WHERE " + COL_TRAINER + " = ?";
	
	@Override
	public List<Members> readTrainerAllMember(String userId) {
		ArrayList<Members> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			System.out.println(SQL_SELECT_ALL);
			stmt = conn.prepareStatement(SQL_SELECT_ALL);
			stmt.setString(1, userId);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				// 각 컬럼의 값들을 읽음.
				String id = rs.getString(COL_ID);
				String trainer = rs.getString(COL_TRAINER);
				String name = rs.getString(COL_NAME);
				String gender = rs.getString(COL_GENDER);
				String birth = rs.getString(COL_BIRTH);
				String phone = rs.getString(COL_PHONE);
				int heigth = rs.getInt(COL_HEIGHT);
				int weigth = rs.getInt(COL_WEIGHT);
				
				list.add(new Members(id, trainer, name, gender, birth, phone, heigth, weigth));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResource(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
		
	}
	
	// 특정 멤버 Info 읽기
	private static final String SQL_SELECT_MEMBER_INFO =
			"SELECT * FROM " + TBL_NAME + " WHERE " + COL_ID + " = ?";
	
	@Override
	public Members readSelectMemberInfo(String id) {
		Members member = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			System.out.println(SQL_SELECT_MEMBER_INFO);
			stmt = conn.prepareStatement(SQL_SELECT_MEMBER_INFO);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				// 각 컬럼의 값들을 읽음.
				String mbId = rs.getString(COL_ID);
				String trainer = rs.getString(COL_TRAINER);
				String name = rs.getString(COL_NAME);
				String gender = rs.getString(COL_GENDER);
				String birth = rs.getString(COL_BIRTH);
				String phone = rs.getString(COL_PHONE);
				int heigth = rs.getInt(COL_HEIGHT);
				int weigth = rs.getInt(COL_WEIGHT);
				
				member = new Members(mbId, trainer, name, gender, birth, phone, heigth, weigth);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResource(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return member;
	}
	
	// 선택된 회원의 정보 수정
	private static final String SQL_UPDATE_MEMBER_INFO = 
			"UPDATE " + TBL_NAME
			+ " SET " + COL_TRAINER + " = ?, "
			+ COL_NAME + " = ?, "
			+ COL_GENDER + " = ?, "
			+ COL_BIRTH + " = ?, "
			+ COL_HEIGHT + " = ?, "
			+ COL_WEIGHT + " = ? "
			+ " WHERE " + COL_ID + " = ?";	
	
	@Override
	public int updateMember(Members member) {
		// 리턴 상수
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		System.out.println(SQL_UPDATE_MEMBER_INFO);
		
		try {
			conn = getConnection();
			
			stmt = conn.prepareStatement(SQL_UPDATE_MEMBER_INFO);			
			stmt.setString(1, member.getTrainer());
			stmt.setString(2, member.getName());
			stmt.setString(3, member.getGender());
			stmt.setString(4, member.getBirth());
			stmt.setInt(5, member.getHeight());
			stmt.setInt(6, member.getWeight());
			stmt.setString(7, member.getId());
			
			result = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	// 해당 아이디 회원 삭제
	private static final String SQL_DELETE = 
			"DELETE FROM " + TBL_NAME + " WHERE " + COL_ID + " = ?";
	
	@Override
	public int deleteMember(String id) {
		// 리턴 상수
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		System.out.println(SQL_DELETE);
		
		try {
			conn = getConnection();
			
			stmt = conn.prepareStatement(SQL_DELETE);			
			stmt.setString(1, id);
			
			result = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

}
