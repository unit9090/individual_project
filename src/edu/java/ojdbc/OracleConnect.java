package edu.java.ojdbc;

public interface OracleConnect {
	// Oracle DB 접속 URL
	String URL = "jdbc:oracle:thin:@localhost:1521:xe";
//	String URL = "jdbc:oracle:thin:@169.254.208.67:1521:xe";
	
	
	// Oracle DB 접속 계정
	String USER = "scott";
	
	// Oracle DB 접속 계정의 비밀번호
	String PASSWORD = "tiger";
}
