package jsp.Book.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BookDAO {

	// singleton : 한번 생성한 객체를 같이 사용
	private static BookDAO instance = new BookDAO();

	// 다른 클래스에서 memberDao생성자를 사용할 수 없게 만듬
	private BookDAO() {
	}

	public static BookDAO getInstance() {
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
	public BookBean select(String id) {
		BookBean book = null;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from classBook where customer_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				book = new BookBean();
				book.setCname(rs.getString("cname"));
				book.setPeople(rs.getInt("people"));
				book.setBday(rs.getString("bday"));
				book.setCustomer_id(rs.getString("id"));
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
		return book;
	}
 
	// class book
	public int insert(BookBean book) {
		Connection conn = getConnection(); 	
		int result = 0;
		PreparedStatement pstmt = null;		
		String sql="insert into Book values(?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getCname());
			pstmt.setInt(2, book.getPeople());
			pstmt.setString(3, book.getBday());
			pstmt.setString(4, book.getCustomer_id());
			result = pstmt.executeUpdate();
		}catch(Exception e){System.out.println(e.getMessage());
		}finally {
			try{if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			}catch (Exception e) {		}
		}
		return result;
	}
	//내가 신청한 클래스 보기
	public List<BookBean> selectmylist(String customer_id){
		List<BookBean> list = new ArrayList<>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println("customer_id : " + customer_id);
		String sql = "select * from book where customer_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, customer_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				BookBean book = new BookBean();				
				book.setCname(rs.getString("cname"));
				book.setBday(rs.getString("bday"));
				book.setCustomer_id(rs.getString("customer_id"));
				book.setPeople(rs.getInt("people"));
				list.add(book);
				}
			}catch (Exception e) {
				System.out.println("book 리스트 호출 에러  : " +e.getMessage());
				}finally {
					try {
						if(pstmt != null) pstmt.close();
						if(conn != null) conn.close();
						if(rs != null) rs.close();
					}catch(Exception e) {}
				}
			return list;
	}

	public ArrayList<BookBean> getBookList() {
		ArrayList<BookBean> bookList = new ArrayList<BookBean>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookBean book = null;
		String sql = "select * from book";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				book = new BookBean();				
				book.setCname(rs.getString("cname"));
				book.setPeople(rs.getInt("people"));
				book.setBday(rs.getString("bday"));
				book.setCustomer_id(rs.getString("customer_id"));;
				
				bookList.add(book);
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
		return bookList;
	}
}
