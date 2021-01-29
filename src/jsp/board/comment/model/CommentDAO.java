package jsp.board.comment.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CommentDAO {
	// singleton : 한번 생성한 객체를 같이 사용
	private static CommentDAO instance = new CommentDAO();

	// 다른 클래스에서 memberDao생성자를 사용할 수 없게 만듬
	private CommentDAO() {
	}

	public static CommentDAO getInstance() {
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
	// 시퀀스
	public int getSeq() {
		int result = 1;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select comment_seq.nextval from dual";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
				if (rs != null) rs.close();
			} catch (Exception e) {	}
		}
		return result;
	}
	
	//댓글 등록
	public boolean insertComment(CommentBean comment) {
		boolean result = false;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into comment_board(comment_num, comment_board, comment_id, comment_date, comment_parent, comment_content, comment_del) "
				+ "values(?,?,?,sysdate,?,?,'n')";
		try {
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getComment_num());
			pstmt.setInt(2, comment.getComment_board());
			pstmt.setString(3, comment.getComment_id());
			pstmt.setInt(4, comment.getComment_parent());
			pstmt.setString(5, comment.getComment_content());
			
			int flag = pstmt.executeUpdate();
			if(flag>0) {
				result = true;
			}
		}catch(Exception e) {
			System.out.println("comment 삽입 에러 : " + e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) { }
		}
		return result;
	}
	//댓글 목록 가져오기
	public ArrayList<CommentBean> getCommentList(int boardNum){
		ArrayList<CommentBean> list = new ArrayList<CommentBean>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		/*
		 * String sql =
		 * "select comment_num, comment_board, comment_id, comment_date, comment_parent, comment_content "
		 * + "from comment_board where comment_board=? order by comment_num desc";
		 */
		
		String sql = "select level, comment_num, comment_board, comment_id, comment_date, comment_parent, comment_content, comment_del "
				+ "from comment_board where comment_board=? start with comment_parent=0 connect by prior comment_num=comment_parent";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CommentBean comment = new CommentBean();
				comment.setComment_level(rs.getInt("level"));
				comment.setComment_num(rs.getInt("comment_num"));
				comment.setComment_board(rs.getInt("comment_board"));
				comment.setComment_id(rs.getString("comment_id"));
				comment.setComment_date(rs.getDate("comment_date"));
				comment.setComment_parent(rs.getInt("comment_parent"));
				comment.setComment_content(rs.getString("comment_content"));
				comment.setComment_del(rs.getString("comment_del"));
				list.add(comment);
			}
			}catch(Exception e) {
				System.out.println("댓글 삽입 오류 : " +e.getMessage());
			}finally {
				try {
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
					if(rs != null) rs.close();
				}catch(Exception e) { }
			}
			return list;
		}

	public CommentBean getComment(int comment_num) {
		
		CommentBean comment = null;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from comment_board where comment_num=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment_num);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				comment = new CommentBean();
				comment.setComment_num(rs.getInt("comment_num"));
				comment.setComment_board(rs.getInt("comment_board"));
				comment.setComment_id(rs.getString("comment_id"));
				comment.setComment_date(rs.getDate("comment_date"));
				comment.setComment_parent(rs.getInt("comment_parent"));
				comment.setComment_content(rs.getString("comment_content"));
			}
			
		}catch(Exception e) {
			System.out.println("댓글 답변 dao 오류 : " + e.getMessage());
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				if(rs != null) rs.close();	
			}catch(Exception e) { }
		}
		return comment;
	}

	public boolean deleteComment(int cmNum) {
		
		boolean result = false;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql="update comment_board set comment_del='y' where comment_num=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cmNum);
			
			int flag = pstmt.executeUpdate();
			if(flag>0) {
				result = true;
			}
		}catch(Exception e) {
			System.out.println("댓글 삭제 에러 : " + e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) { }
		}
		return result;
	}

	public boolean updateComment(CommentBean comment) {
		
		boolean result = false;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "update comment_board set comment_content = ? where comment_num=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getComment_content());
			pstmt.setInt(2, comment.getComment_num());
			
			int flag = pstmt.executeUpdate();
			if(flag>0) {
				result = true;
			}
		}catch(Exception e) {
			System.out.println("댓글 수정 dao 오류 : " + e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) { }
		}
		return result;
	}
}
