package edu.java.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			+ " (" + COL_ID + ", " + COL_TITLE + ", " + COL_CONTENT
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

	@Override
	public List<MemberDiary> readMemberDiary(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberDiary readSelectMemberDiary(int mdIdx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateMemberDiary(MemberDiary diary) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMemberDiary(int mdIdx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMemberDiary(String mdId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
