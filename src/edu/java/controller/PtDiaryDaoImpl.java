package edu.java.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			+ ", " + COL_TITLE + ", " + COL_CONTENT + ") VALUES"
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
			"SELECT * FROM " + TBL_NAME + " WHERE " + COL_MBID + " = ?"
			+ " ORDER BY " + COL_PIDX;

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
	private static final String SQL_SELECT_PIDX = 
			"SELECT * FROM " + TBL_NAME + " WHERE "
			+ COL_PIDX + " = ?";
	
	@Override
	public PtDiary readSelectPtDiary(int pidx) {
		
		PtDiary ptDiary = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			stmt = conn.prepareStatement(SQL_SELECT_PIDX);
			stmt.setInt(1, pidx);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {	// 검색된 행(row) 데이터가 있다면
				ptDiary = new PtDiary(
						  pidx
						, rs.getString(COL_TRID)
						, rs.getString(COL_MBID)
						, rs.getString(COL_TITLE)
						, rs.getString(COL_CONTENT)
				);
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
				
		return ptDiary;
		
	}

	// 선택한 pidx 내용 수정
	private static final String SQL_UPDATE =
			"UPDATE " + TBL_NAME + " SET " 
			+ COL_TITLE + " = ?, " + COL_CONTENT + " = ? WHERE "
			+ COL_PIDX + " = ?";
	
	@Override
	public int updatePtDiary(PtDiary pt) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			
			stmt = conn.prepareStatement(SQL_UPDATE);
			stmt.setString(1, pt.getTitle());
			stmt.setString(2, pt.getContent());
			stmt.setInt(3, pt.getPidx());
			
			result = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
	}
	
	// 선택한 pidx 삭제
	private static final String SQL_DELETE =
			"DELETE FROM " + TBL_NAME + " WHERE " + COL_PIDX + " = ?";

	@Override
	public int deletePtDiary(int pidx) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			
			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setInt(1, pidx);
			
			result = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	// 해당 아이디가 삭제될 때 내용도 같이 삭제
	private static final String SQL_DELETE_USER =
			"DELETE FROM " + TBL_NAME + " WHERE " + COL_MBID + " = ?";
	
	@Override
	public int deleteMemberPtDiary(String mbId) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			
			stmt = conn.prepareStatement(SQL_DELETE_USER);
			stmt.setString(1, mbId);
			
			result = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

}
