package edu.java.controller;

import edu.java.model.Trainer;
import oracle.jdbc.OracleDriver;


// Trainer
import static edu.java.model.Trainer.Entity.*;
//DB
import static edu.java.ojdbc.OracleConnect.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TrainerDaoImpl implements TrainerDao {
	
	private static TrainerDaoImpl instance = null;
	private TrainerDaoImpl() {}
	public static TrainerDaoImpl getInstance() {
		if(instance == null) {
			instance = new TrainerDaoImpl();
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
	

	// Trainer 생성
	private static final String SQL_INSERT = 
			"INSERT INTO " + TBL_NAME
			+ " (" + COL_ID + ", " + COL_NAME + ", " + COL_GENDER
			+ ", " + COL_BIRTH + ", "+ COL_PHONE + ", " + COL_EMAIL
			+ ") VALUES "
			+ " (?, ?, ?, ?, ?, ?)";
		
	@Override
	public int insertTrainer(Trainer trainer) {
		// 리턴 상수
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		System.out.println(SQL_INSERT);
		
		try {
			conn = getConnection();
			
			stmt = conn.prepareStatement(SQL_INSERT);			
			stmt.setString(1, trainer.getId());
			stmt.setString(2, trainer.getName());
			stmt.setString(3, trainer.getGender());
			stmt.setString(4, trainer.getBirth());
			stmt.setString(5, trainer.getPhone());
			stmt.setString(6, trainer.getEmail());
			
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
	
	// select Trainer Info
	private static final String SQL_SELECT_TRAINER = 
			"SELECT * FROM " + TBL_NAME + " WHERE " + COL_ID + " = ?";
	
	@Override
	public Trainer selectTrainerInfo(String id) {
		Trainer trainer = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			System.out.println(SQL_SELECT_TRAINER);
			stmt = conn.prepareStatement(SQL_SELECT_TRAINER);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				// 각 컬럼의 값들을 읽음.
				String ids = rs.getString(COL_ID);
				String name = rs.getString(COL_NAME);
				String gender = rs.getString(COL_GENDER);
				String birth = rs.getString(COL_BIRTH);
				String phone = rs.getString(COL_PHONE);
				String email = rs.getString(COL_EMAIL);
				
				trainer = new Trainer(ids, name, gender, birth, phone, email);
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
		
		return trainer;
	}

}
