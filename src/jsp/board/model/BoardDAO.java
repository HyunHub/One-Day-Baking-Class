package jsp.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {

	// singleton : 한번 생성한 객체를 같이 사용
	private static BoardDAO instance = new BoardDAO();

	// 다른 클래스에서 memberDao생성자를 사용할 수 없게 만듬
	private BoardDAO() {
	}

	public static BoardDAO getInstance() {
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
		String sql = "select board_num.nextval from dual";
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

	// 게시글 삽입
	public boolean boardInsert(BoardBean board) {
		boolean result = false;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into member_board(board_num, board_id, board_subject, board_content, board_file, board_count, board_category, board_date, board_del)"
				+ "values(?,?,?,?,?,?,?,sysdate,'n')";
		// 시퀀스 값을 글번호로 사용
		int num = board.getBoard_num();

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBoard_id());
			pstmt.setString(3, board.getBoard_subject());
			pstmt.setString(4, board.getBoard_content());
			pstmt.setString(5, board.getBoard_file());
			pstmt.setInt(6, 0);
			pstmt.setString(7, board.getBoard_category());

			int flag = pstmt.executeUpdate();
			if (flag > 0) {
				result = true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {	}
		}
		return result;
	}
	
	//게시글 전체 조회
	public ArrayList<BoardBean> getBoardList(HashMap<String, Object> listOpt){
		ArrayList<BoardBean> list = new ArrayList<BoardBean>();
		
		String opt = (String)listOpt.get("opt");
		String condition = (String)listOpt.get("condition"); 
		int start = (Integer)listOpt.get("start");
		String board_category = (String)listOpt.get("board_category");
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql1 = "select * from (select rownum rnum, a.* from (select * from member_board where board_category=? "
				+ "order by board_num desc)a) where rnum>=? and rnum<=?";
		String sql2 = "select * from (select rownum rnum, a.* from (select * from member_board where board_subject like ? and board_category=? order by board_num desc)a)"
				+ "where rnum>=? and rnum<=?";
		String sql3 = "select * from (select rownum rnum, a.* from (select * from member_board where board_content like ? and board_category=? order by board_num desc)a)"
				+ "where rnum>=? and rnum<=?";
		String sql4 = "select * from (select rownum rnum, a.* from (select * from member_board where (board_subject like ? or board_content like ?) and board_category=? order by board_num desc)a)"
				+ "where rnum>=? and rnum<=?";
		String sql5 = "select * from (select rownum rnum, a.* from (select * from member_board where board_id like ? and board_category=? order by board_num desc)a)"
				+ "where rnum>=? and rnum<=?";
		
		try {
			if(opt == null) {
				//글 전체 조회
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, board_category);
				pstmt.setInt(2, start);
				pstmt.setInt(3, start+9);
			}
			else if(opt.equals("0")){
					//제목으로 검색
					pstmt = conn.prepareStatement(sql2);
					pstmt.setString(1, "%"+condition+"%");
					pstmt.setString(2, board_category);
					pstmt.setInt(3, start);
					pstmt.setInt(4, start+9);
					
				}else if(opt.equals("1")){
					//내용으로 검색
					pstmt = conn.prepareStatement(sql3);
					pstmt.setString(1, "%"+condition+"%");
					pstmt.setString(2, board_category);
					pstmt.setInt(3, start);
					pstmt.setInt(4, start+9);
					
				}else if(opt.equals("2")){
					//제목+내용으로 검색
					pstmt = conn.prepareStatement(sql4);
					pstmt.setString(1, "%"+condition+"%");
					pstmt.setString(2, "%"+condition+"%");
					pstmt.setString(3, board_category);
					pstmt.setInt(4, start);
					pstmt.setInt(5, start+9);
					
				}else if(opt.equals("3")){
					//글쓴이로 검색
					pstmt = conn.prepareStatement(sql5);
					pstmt.setString(1, "%"+condition+"%");
					pstmt.setString(2, board_category);
					pstmt.setInt(3, start);
					pstmt.setInt(4, start+9);

				}
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				BoardBean board = new BoardBean();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_id(rs.getString("board_id"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_file(rs.getString("board_file"));
				board.setBoard_count(rs.getInt("board_count"));
				board.setBoard_date(rs.getDate("board_date"));
				board.setBoard_del(rs.getString("board_del"));
				board.setBoard_category(rs.getString("board_category")); 
				board.setLike_count(rs.getInt("like_count"));
				list.add(board);
				}
			}catch (Exception e) {
				System.out.println("입력 에러 : " +e.getMessage());
				}finally {
					try {
						if(pstmt != null) pstmt.close();
						if(conn != null) conn.close();
						if(rs != null) rs.close();
					}catch(Exception e) {}
				}
			return list;
		}
	  
	// 글 전체 개수 가져오는 메소드
	public int getBoardListCount(HashMap<String, Object> listOpt){
		
		int result = 0;
		String opt = (String)listOpt.get("opt"); 
		String condition = (String)listOpt.get("condition");
		String board_category = (String)listOpt.get("board_category");
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql1 = "select count(*) from member_board where board_category=?";
		String sql2 = "select count(*) from member_board where board_category=? and board_subject like ?";
		String sql3 = "select count(*) from member_board where board_category=? and board_content like ?";
		String sql4 = "select count(*) from member_board where board_category=? and (board_subject like ? or board_content like ?)";
		String sql5 = "select count(*) from member_board where board_category=? and board_id like ?";
		
		try {
			if(opt == null)
			{
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, board_category);
			}
			else if(opt.equals("0"))
			{
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, board_category);
				pstmt.setString(2, '%'+condition+'%');
			}
			else if(opt.equals("1"))
			{
				pstmt = conn.prepareStatement(sql3);
				pstmt.setString(1, board_category);
				pstmt.setString(2, '%'+condition+'%');
			}
			else if(opt.equals("2"))
			{
				pstmt = conn.prepareStatement(sql4);
				pstmt.setString(1, board_category);
				pstmt.setString(2, '%'+condition+'%');
				pstmt.setString(3, '%'+condition+'%');
			}
			else if(opt.equals("3"))
			{
				pstmt = conn.prepareStatement(sql5);
				pstmt.setString(1, board_category);
				pstmt.setString(2, '%'+condition+'%');
			}
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				if(rs != null) rs.close();
			}catch(Exception e) {}
		}
		return result;
	}
	
	//상세보기
	public BoardBean getDetail(int boardNum) {
		BoardBean board = null;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member_board where board_num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board = new BoardBean();
				board.setBoard_num(boardNum);
				board.setBoard_id(rs.getString("board_id"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_file(rs.getString("board_file"));
				board.setBoard_count(rs.getInt("board_count"));
				board.setBoard_date(rs.getDate("board_date"));
				board.setBoard_category(rs.getString("board_category"));
				board.setLike_count(rs.getInt("like_count"));
				/* board.setBoard_del(rs.getString("board_del")); */
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
		return board;
	}
	
	//조회수 증가
	public boolean updateCount(int boardNum) {
		boolean result = false;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "update member_board set board_count = board_count +1 where board_num=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			
			int flag = pstmt.executeUpdate();
			if(flag > 0) {
				result = true;
			}
		}catch(Exception e) {
			System.out.println("조회수 증가 에러 : " + e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) { }
		}
		return result;
	}
	//삭제할 파일명 호출
	public String getFileName(int boardNum) {
		String fileName = null;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select board_file from member_board where board_num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				fileName = rs.getString("board_file");
			}
		}catch(Exception e) {
			System.out.println("파일명 호출 에러 : " + e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				if(rs != null) rs.close();
			}catch(Exception e) { }
		}
		return fileName;
	}
	
	//게시글 삭제
	public boolean deleteBoard(int boardNum) {
		boolean result = false;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql="update member_board set board_del='y' where board_num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			
			int flag = pstmt.executeUpdate();
			if(flag>0) {
				result = true;
			}
		}catch(Exception e) {
			System.out.println("파일명 호출 에러 : " + e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) { }
		}
		return result;
	}

	//게시글 수정
	public boolean updateBoard(BoardBean board) {
		
		boolean result = false;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "update member_board set board_subject=?, board_content=?, board_file=?, board_category=?, board_date=sysdate where board_num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBoard_subject());
			pstmt.setString(2, board.getBoard_content());
			pstmt.setString(3, board.getBoard_file());
			pstmt.setString(4, board.getBoard_category());
			pstmt.setInt(5, board.getBoard_num());
			
			int flag = pstmt.executeUpdate();
			if(flag>0) {
				result = true;
			}
		}catch(Exception e) {
			System.out.println("글 수정 dao 오류 : " + e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) { }
		}
		return result;
	}
	
	//게시글 추천 추천을 누르면 특정한 글 추천
	public int like(int boardNum) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null; 
		String sql = "update member_board set like_count = like_count +1 where board_num=?";
	  
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			return pstmt.executeUpdate();
			}catch(Exception e){
				System.out.println("추천수 에러 : " + e.getMessage());
			}finally{
				try{ 
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
					}catch(Exception e) { }
				}
		return -1;
		}
	 
	
	/*
	 * public boolean like(int boardNum) { boolean result = false; Connection conn =
	 * getConnection(); PreparedStatement pstmt = null; String sql =
	 * "update member_board set like_count = like_count +1 where board_num=?";
	 * 
	 * try { pstmt = conn.prepareStatement(sql); pstmt.setInt(1, boardNum);
	 * 
	 * int flag = pstmt.executeUpdate(); if(flag>0) { result = true; }
	 * }catch(Exception e) { System.out.println("추천수 에러 : " + e.getMessage());
	 * }finally { try { if(pstmt != null) pstmt.close(); if(conn != null)
	 * conn.close(); }catch(Exception e) { } } return result; }
	 */
}