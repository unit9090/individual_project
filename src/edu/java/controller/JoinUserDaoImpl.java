package edu.java.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.java.model.JoinUser;
import oracle.jdbc.OracleDriver;

//JoinUser 상수
import static edu.java.model.JoinUser.Entity.*;
//DB
import static edu.java.ojdbc.OracleConnect.*;

public class JoinUserDaoImpl implements JoinUserDao {
	
	private static JoinUserDaoImpl instance = null;
	private JoinUserDaoImpl() {}
	public static JoinUserDaoImpl getInstance() {
		if(instance == null) {
			instance = new JoinUserDaoImpl();
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
	
	
	// USER ID, PWD 생성
	private static final String SQL_INSERT = 
			"INSERT INTO" + TBL_NAME
			+ " (" + COL_ID + ", " + COL_PWD
			+ ", " + COL_PHONE + ", " + COL_DIVISION
			+ ") VALUES"
			+ " (?, ?, ?, ?)";
	
	@Override
	public int joinUser(JoinUser joinUser) {
		// 리턴 상수
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			
			stmt = conn.prepareStatement(SQL_INSERT);			
			stmt.setString(1, joinUser.getId());
			stmt.setString(2, joinUser.getPwd());
			stmt.setString(3, joinUser.getPhone());
			stmt.setString(4, joinUser.getDivision());
			
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
	
	// 중복확인
	private static final String SQL_SELECT_DOUBLE = 
			"SELECT " + COL_ID + " FROM" + TBL_NAME;
	@Override
	public boolean doubleCheck(String id) {
		boolean result = false;
		ArrayList<String> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			System.out.println(SQL_SELECT_DOUBLE);
			stmt = conn.prepareStatement(SQL_SELECT_DOUBLE);	
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				// 각 컬럼의 값들을 읽음.
				String ids = rs.getString(COL_ID);
				list.add(ids);
			}
			
			if(!list.contains(id)) {
				result = true;
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
		
		return result;
	}
	
	 
}
