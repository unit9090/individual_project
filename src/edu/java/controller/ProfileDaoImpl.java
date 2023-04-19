package edu.java.controller;

import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

import oracle.jdbc.OracleDriver;

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
	
	@Override
	public int saveImage(String id, BufferedImage image) {
		// 리턴 상수
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		System.out.println(SQL_INSERT_IMAGE);
		
		try {
			conn = getConnection();
			
			stmt = conn.prepareStatement(SQL_INSERT_IMAGE);
			
			// url을 byte로 변경
			byte[] imageArray = null;
			String BASE_64_PREFIX = "data:image/png;base64,";
			String base64Url = String.valueOf(image);
			if(base64Url.startsWith(BASE_64_PREFIX)) {
				imageArray = Base64.getDecoder().decode(base64Url.substring(BASE_64_PREFIX.length()));
				System.out.println(new String(imageArray));
			}
			
			stmt.setString(1, id);
			stmt.setBytes(2, imageArray);
			
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
	public byte[] selectImage(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
