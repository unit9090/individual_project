package edu.java.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.java.model.PtDiary;
import oracle.jdbc.OracleDriver;

// DB
import static edu.java.ojdbc.OracleConnect.*;
// Pt Diary
import static edu.java.model.PtDiary.Entity.*;


public class PtDiaryDaoImpl implements PtDiaryDao {

	private static PtDiaryDaoImpl instance = null;
	private PtDiaryDaoImpl() {}
	public static PtDiaryDaoImpl getInstance() {
		if(instance == null) {
			instance = new PtDiaryDaoImpl();
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
	
	// Pt 다이어리 생성
	private static final String SQL_INSERT = 
			"INSERT INTO " + TBL_NAME
			+ " (" + COL_TRID + ", " + COL_MBID 
			+ ", " + COL_TITLE + ", " + COL_CONTENT 
			+ " (?, ?, ?, ?)";
	
	@Override
	public int createPtDiary(PtDiary pt) {
		// 리턴 상수
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		System.out.println(SQL_INSERT);
		
		try {
			conn = getConnection();
			
			stmt = conn.prepareStatement(SQL_INSERT);			
			stmt.setString(1, pt.getTrId());
			stmt.setString(2, pt.getMbId());
			stmt.setString(3, pt.getTitle());
			stmt.setString(4, pt.getContent());
			
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
	
	// 해당 아이디에 저장된 Pt 다이어리 list
	private static final String SQL_SELECT_MB = 
			"SELECT * FROM " + TBL_NAME + " WHERE " + COL_MBID + " = ?";

	@Override
	public List<PtDiary> readMemberPtDiary(String mbId) {
		ArrayList<PtDiary> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			System.out.println(SQL_SELECT_MB);
			stmt = conn.prepareStatement(SQL_SELECT_MB);
			stmt.setString(1, mbId);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				// 각 컬럼의 값들을 읽음.
				int pidx = rs.getInt(COL_PIDX);
				String trId = rs.getString(COL_TRID);
				String mbid = rs.getString(COL_MBID);
				String title = rs.getString(COL_TITLE);
				String content = rs.getString(COL_CONTENT);
				
				list.add(new PtDiary(pidx, trId, mbid, title, content));
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

	// 선택한 pidx에 저장된 내용
	
	
	@Override
	public PtDiary readSelectPtDiary(int pidx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updatePtDiary(PtDiary pt) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePtDiary(int pidx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMemberPtDiary(String mbId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
