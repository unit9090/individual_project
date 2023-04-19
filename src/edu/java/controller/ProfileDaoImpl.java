package edu.java.controller;

import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;

import oracle.jdbc.OracleDriver;

import static edu.java.model.JoinUser.Entity.COL_ID;
// profile
import static edu.java.model.Profile.Entity.*;
import static edu.java.ojdbc.OracleConnect.PASSWORD;
import static edu.java.ojdbc.OracleConnect.URL;
import static edu.java.ojdbc.OracleConnect.USER;

public class ProfileDaoImpl implements ProfileDao {
	
	private static ProfileDaoImpl instance = null;
	private ProfileDaoImpl() {}
	public static ProfileDaoImpl getInstance() {
		if(instance == null) {
			instance = new ProfileDaoImpl();
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

	// 이미지 파일 저장
	private static final String SQL_INSERT_IMAGE = 
			"INSERT INTO " + TBL_NAME + " (" + COL_ID + ", " + COL_PBLOB 
			+ ") VALUES (?, ?)";
	
	// 이미지 파일 업데이트
	private static final String SQL_UPDATE_IMAGE = 
			"UPDATE " + TBL_NAME
			+ " SET " + COL_PBLOB + " = ?"
			+ " WHERE " + COL_ID + " = ?";			
	
	@Override
	public int saveImage(String id, byte[] byteArr) {
		System.out.println(byteArr);
		// 리턴 상수
		int result = 0;
		
		int newFile = 0;
		if(selectImage(id) == null) {
			newFile = 1;
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			
			if(newFile == 1) {
				stmt = conn.prepareStatement(SQL_INSERT_IMAGE);
				stmt.setString(1, id);
				stmt.setBytes(2, byteArr);
				System.out.println(SQL_INSERT_IMAGE);
			} else {
				stmt = conn.prepareStatement(SQL_UPDATE_IMAGE);
				stmt.setBytes(1, byteArr);
				stmt.setString(2, id);
				System.out.println(SQL_UPDATE_IMAGE);
			}
			
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
	
	

	// 해당 아이디에 맞는 이미지 파일 select
	private static final String SQL_SELECT_IMAGE = 
			"SELECT " + COL_PBLOB + " FROM " + TBL_NAME + " WHERE " + COL_ID + " = ?";
	
	@Override
	public byte[] selectImage(String id) {
		byte[] result = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			System.out.println(SQL_SELECT_IMAGE);
			stmt = conn.prepareStatement(SQL_SELECT_IMAGE);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getBytes(COL_PBLOB);
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
