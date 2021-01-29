package jsp.recommend.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LikeDAO {

	// singleton : 한번 생성한 객체를 같이 사용
	private static LikeDAO instance = new LikeDAO();

	// 다른 클래스에서 memberDao생성자를 사용할 수 없게 만듬
	private LikeDAO() {
		
	}

	public static LikeDAO getInstance() {
		return instance;
	}

	// 공동으로 사용할 DB연결 (Database Connection pool)
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println("연결에러 : " + e.getMessage());
		}
		return conn;
	}
	
	public int like(String recommend_id, int recommend_num) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into board_recommend values(?,?)";
		LikeBean like = new LikeBean();
		
		System.out.println("likeDAO의 recommend_id : " + recommend_id);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, recommend_id);
			pstmt.setInt(2, recommend_num);
			
			return pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("추천수 증가 에러(LikeDAO) : " + e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) { }
		}
		
		return -1;//추천수 중복 오류
	}
}
