package edu.java.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.java.model.MemberDiary;
import oracle.jdbc.OracleDriver;


//DB
import static edu.java.ojdbc.OracleConnect.*;
//MemberDiary 상수
import static edu.java.model.MemberDiary.Entity.*;

public class MemberDiaryDaoImpl implements MemberDiaryDao {
	
	private static MemberDiaryDaoImpl instance = null;
	private MemberDiaryDaoImpl() {}
	public static MemberDiaryDaoImpl getInstance() {
		if(instance == null) {
			instance = new MemberDiaryDaoImpl();
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
	
	// diary 생성
	private static final String SQL_INSERT = 
			"INSERT INTO " + TBL_NAME
			+ " (" + COL_ID + ", " + COL_TITLE + ", " + COL_CONTENT + ")"
			+ " VALUES (?, ?, ?)";
	
	@Override
	public int createDiary(MemberDiary diary) {
		// 리턴 상수
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		System.out.println(SQL_INSERT);
		
		try {
			conn = getConnection();
			
			stmt = conn.prepareStatement(SQL_INSERT);			
			stmt.setString(1, diary.getId());
			stmt.setString(2, diary.getTitle());
			stmt.setString(3, diary.getContent());
			
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
	
	// 해당 member id에 저장된 다이어리 list
	private static final String SQL_SELECT_ALL =
			"SELECT * FROM " + TBL_NAME + " WHERE " + COL_ID + " = ? ORDER BY " + COL_MIDX;

	@Override
	public List<MemberDiary> readMemberDiary(String id) {
		ArrayList<MemberDiary> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			System.out.println(SQL_SELECT_ALL);
			stmt = conn.prepareStatement(SQL_SELECT_ALL);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				// 각 컬럼의 값들을 읽음.
				int idx = rs.getInt(COL_MIDX);
				String mid = rs.getString(COL_ID);
				String title = rs.getString(COL_TITLE);
				String content = rs.getString(COL_CONTENT);
				LocalDateTime created = rs.getTimestamp(COL_CREATED).toLocalDateTime();
				LocalDateTime modified = rs.getTimestamp(COL_MODIFIED).toLocalDateTime();
				
				MemberDiary mb = new MemberDiary(idx, mid, title, content, created, modified);
				
				list.add(mb);
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
	
	// 선택한 mdIdx에 저장된 내용
	private static final String SQL_SELECT_IDX =
			"SELECT * FROM " + TBL_NAME + " WHERE "
			+ COL_MIDX + " = ?";

	@Override
	public MemberDiary readSelectMemberDiary(int mdIdx) {
		
		MemberDiary md = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			stmt = conn.prepareStatement(SQL_SELECT_IDX);
			stmt.setInt(1, mdIdx);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				md = new MemberDiary(
						  mdIdx						
						, rs.getString(COL_ID)
						, rs.getString(COL_TITLE)
						, rs.getString(COL_CONTENT)
						, rs.getTimestamp(COL_CREATED).toLocalDateTime()
						, rs.getTimestamp(COL_MODIFIED).toLocalDateTime()
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
				
		return md;
	}

	// 선택한 mdIdx의 내용 수정
	private static final String SQL_UPDATE = 
			"UPDATE " + TBL_NAME + " SET "
			+ COL_TITLE + " = ?, " + COL_CONTENT + " = ?, "
			+ COL_MODIFIED + " = SYSDATE WHERE " + COL_MIDX + " = ?";
	
	@Override
	public int updateMemberDiary(MemberDiary diary) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			
			stmt = conn.prepareStatement(SQL_UPDATE);
			System.out.println(SQL_UPDATE);
			stmt.setString(1, diary.getTitle());
			stmt.setString(2, diary.getContent());
			stmt.setInt(3, diary.getMidx());
			
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

	// 선택한 mdIdx 삭제
	private static final String SQL_DELETE =
			"DELETE FROM " + TBL_NAME + " WHERE " + COL_MIDX + " = ?";	
	
	@Override
	public int deleteMemberDiary(int mdIdx) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			
			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setInt(1, mdIdx);
			
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
			"DELETE FROM " + TBL_NAME + " WHERE " + COL_ID + " = ?";	
	
	@Override
	public int deleteMemberDiary(String mdId) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			
			stmt = conn.prepareStatement(SQL_DELETE_USER);
			stmt.setString(1, mdId);
			
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
