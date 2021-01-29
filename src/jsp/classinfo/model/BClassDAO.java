package jsp.classinfo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BClassDAO {

	// singleton : 한번 생성한 객체를 같이 사용
	private static BClassDAO instance = new BClassDAO();

	// 다른 클래스에서 memberDao생성자를 사용할 수 없게 만듬
	private BClassDAO() {
	}

	public static BClassDAO getInstance() {
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
	// 조회 
	public BClassBean select(String cname) {
		BClassBean bclass = null;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from bclass where cname=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cname);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bclass = new BClassBean();
				bclass.setCname(rs.getString("cname"));
				bclass.setTeacher(rs.getString("teacher"));
				bclass.setImage(rs.getString("image"));
				
			}
		}catch (Exception e) { // 에러가 발생하면 콘솔에 에러를 출력
			System.out.println(e.getMessage());
		}finally {
			try { // 작업이 끝났으면 DB에 관련된 것을 메모리에서 닫아라
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}catch (Exception e) {	}
		}
		return bclass;
	}
	
	//클래스 목록 호출
	public List<BClassBean> selectList(){
		
		List<BClassBean> list = new ArrayList<BClassBean>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from bclass";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BClassBean bclass = new BClassBean();
				bclass.setCname(rs.getString("cname"));
				bclass.setTeacher(rs.getString("teacher"));
				bclass.setImage(rs.getString("image"));
				bclass.setBday(rs.getString("bday"));
				
				list.add(bclass);
			}
		}catch(Exception e) {
			System.out.println("이벤트 목록 불러오기 에러 : " + e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				if(rs != null) rs.close();
			}catch(Exception e) {}
		}
		return list;
	}
 
	// class upload
	public int insert(BClassBean bclass) {
		Connection conn = getConnection(); 	
		int result = 0;
		PreparedStatement pstmt = null;		
		String sql="insert into bclass values(?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bclass.getCname());
			pstmt.setString(2, bclass.getTeacher());
			pstmt.setString(3, bclass.getImage());
			pstmt.setString(4, bclass.getBday());
			result = pstmt.executeUpdate();
		}catch(Exception e){System.out.println(e.getMessage());
		}finally {
			try{if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			}catch (Exception e) {		}
		}
		return result;
	}
	//상세보기
	public BClassBean getDetail(String cname) {
		BClassBean bclass = null;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from bclass where cname = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cname);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bclass = new BClassBean();
				
				bclass.setCname(rs.getString("cname"));
				bclass.setTeacher(rs.getString("teacher"));
				bclass.setImage(rs.getString("image"));
				bclass.setBday(rs.getString("bday"));
			}
		}catch(Exception e) {
			System.out.println("상세보기 에러 : " + e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				if(rs != null) rs.close();
			}catch(Exception e) { }
		}
		return bclass;
	}
}
