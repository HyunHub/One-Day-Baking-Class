package jsp.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CustomerDAO {

	// singleton : 한번 생성한 객체를 같이 사용
	private static CustomerDAO instance = new CustomerDAO();
	// 다른 클래스에서 memberDao생성자를 사용할 수 없게 만듬
	private CustomerDAO() {} 
	public static CustomerDAO getInstance() {
		return instance;
	}
	// 공동으로 사용할 DB연결 (Database Connection pool)
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)
				ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		}catch (Exception e) {
			System.out.println("연결에러 : "+e.getMessage());
		}
		return conn;
	}
	
	public void insertMember(CustomerBean customer) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql="insert into customers values(?,?,?,?,?,?,sysdate,0)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, customer.getCustomer_id());
			pstmt.setString(2, customer.getCustomer_pw());
			pstmt.setString(3, customer.getCustomer_name());
			pstmt.setString(4, customer.getCustomer_email());
			pstmt.setString(5, customer.getCustomer_phone());
			pstmt.setString(6, customer.getCustomer_address());
			pstmt.executeUpdate();
		}catch (Exception e) { // 에러가 발생하면 콘솔에 에러를 출력
			System.out.println(e.getMessage());
		}finally {
			try { // 작업이 끝났으면 DB에 관련된 것을 메모리에서 닫아라
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}catch (Exception e) {	}
		}
	}
	
	public int loginCheck(String id, String pw) {
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		String sql = "select customer_pw from customers where customer_id=? and customer_withdraw='0'";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbPass = rs.getString("customer_pw");
				// id와 암호가 일치
				if (dbPass.equals(pw)) x = 1;
				else x = 0; // id는 맞는데 암호가 달라
			} else x = -1; // 없는 id
		}catch (Exception e) { // 에러가 발생하면 콘솔에 에러를 출력
			System.out.println(e.getMessage());
		}finally {
			try { // 작업이 끝났으면 DB에 관련된 것을 메모리에서 닫아라
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}catch (Exception e) {	}
		}
		return x;		
	}
	//나의 정보 가져오기
	public CustomerBean getUserInfo(String customer_id) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerBean customer = null;
		String sql = "select * from customers where customer_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, customer_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {                
				customer = new CustomerBean();
				customer.setCustomer_id(rs.getString("customer_id"));
				customer.setCustomer_pw(rs.getString("customer_pw"));
				customer.setCustomer_name(rs.getString("customer_name"));
				customer.setCustomer_email(rs.getString("customer_email"));
				customer.setCustomer_phone(rs.getString("customer_phone"));
				customer.setCustomer_address(rs.getString("customer_address"));
				customer.setCustomer_join(rs.getDate("customer_join"));
			}
		}catch (Exception e) { // 에러가 발생하면 콘솔에 에러를 출력
			System.out.println("내 정보 불러오기 : " + e.getMessage());
		}finally {
			try { // 작업이 끝났으면 DB에 관련된 것을 메모리에서 닫아라
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}catch (Exception e) {	}
		}
		return customer;
	}
	
	//회원 정보 수정
	public int updateMember(CustomerBean customer) {
		int result = 0;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "update customers set customer_pw=?, customer_name=?, customer_address=?, customer_phone=?, customer_email=? where customer_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, customer.getCustomer_pw());
			pstmt.setString(2, customer.getCustomer_name());
			pstmt.setString(3, customer.getCustomer_address());
			pstmt.setString(4, customer.getCustomer_phone());
			pstmt.setString(5, customer.getCustomer_email());			
			pstmt.setString(6, customer.getCustomer_id());
			result = pstmt.executeUpdate();
	
		}catch (Exception e) { // 에러가 발생하면 콘솔에 에러를 출력
			System.out.println(e.getMessage());
		}finally {
			try { // 작업이 끝났으면 DB에 관련된 것을 메모리에서 닫아라
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}catch (Exception e) {	}
		}
		return result;
	}
	
	public int deleteMember(String customer_id) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update customers set customer_withdraw='1' where id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, customer_id);
			result = pstmt.executeUpdate();

		}catch (Exception e) { 
			System.out.println(e.getMessage());
		}finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}catch (Exception e) {	}
		}
		
		return result;
	}
	
	public ArrayList<CustomerBean> getMemberList(){
		ArrayList<CustomerBean> memberList = new ArrayList<CustomerBean>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerBean customer = null;
		String sql = "select * from customers order by customer_join desc";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				customer = new CustomerBean();				
				customer.setCustomer_id(rs.getString("customer_id"));
				customer.setCustomer_pw(rs.getString("customer_pw"));
				customer.setCustomer_name(rs.getString("customer_name"));
				customer.setCustomer_email(rs.getString("customer_email"));
				customer.setCustomer_phone(rs.getString("customer_phone"));
				customer.setCustomer_address(rs.getString("customer_address"));
				customer.setCustomer_join(rs.getDate("customer_join"));
				
                memberList.add(customer);
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
		return memberList;
	}
	// 조회 
	public CustomerBean select(String customer_id) {
		CustomerBean customer = null;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 중복체크할 때는 탈퇴된 아이디를 사용 못하게 막아야 한다
		String sql = "select * from customers where customer_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, customer_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				customer = new CustomerBean();
				customer.setCustomer_id(rs.getString("customer_id"));
				customer.setCustomer_pw(rs.getString("customer_pw"));
				customer.setCustomer_name(rs.getString("customer_name"));
				customer.setCustomer_email(rs.getString("customer_email"));
				customer.setCustomer_phone(rs.getString("customer_phone"));
				customer.setCustomer_address(rs.getString("customer_address"));
				customer.setCustomer_join(rs.getDate("customer_join"));
				customer.setCustomer_withdraw(rs.getInt("customer_withdraw"));
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
		return customer;		
	}
}
