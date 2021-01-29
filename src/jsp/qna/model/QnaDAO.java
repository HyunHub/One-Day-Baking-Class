package jsp.qna.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class QnaDAO {

	// singleton : 한번 생성한 객체를 같이 사용
	private static QnaDAO instance = new QnaDAO();

	// 다른 클래스에서 memberDao생성자를 사용할 수 없게 만듬
	private QnaDAO() {
	}

	public static QnaDAO getInstance() {
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
		String sql = "select qna_num.nextval from dual";
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
	public boolean boardInsert(QnaBean qna) {
		boolean result = false;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into qna_board(qna_num, qna_id, qna_subject, qna_content, qna_category, qna_re_ref, qna_re_lev, qna_re_seq, qna_pw, qna_date, qna_del)"
				+ "values(?,?,?,?,?,?,?,?,?,sysdate,'n')";
		// 시퀀스 값을 글번호로 사용
		int num = qna.getQna_num();

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.setString(2, qna.getQna_id());
			pstmt.setString(3, qna.getQna_subject());
			pstmt.setString(4, qna.getQna_content());
			pstmt.setString(5, qna.getQna_category());
			
			
			if(qna.getQna_re_seq()== 0) {
				pstmt.setInt(6, num);
			}else {
				pstmt.setInt(6, qna.getQna_re_ref());
			}
			
			pstmt.setInt(7, qna.getQna_re_lev());
			pstmt.setInt(8, qna.getQna_re_seq());
			pstmt.setString(9, qna.getQna_pw());

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
	public ArrayList<QnaBean> getBoardList(HashMap<String, Object> listOpt){
		ArrayList<QnaBean> list = new ArrayList<QnaBean>();
		
		String opt = (String)listOpt.get("opt"); 
		int start = (Integer)listOpt.get("start");
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql1 = "select * from (select rownum rnum, a.* from (select * from qna_board "
				+ "order by  qna_re_ref desc, qna_re_seq asc)a) where rnum>=? and rnum<=?";
		try {
			if(opt == null) {
				//글 전체 조회
				pstmt = conn.prepareStatement(sql1);
				pstmt.setInt(1, start);
				pstmt.setInt(2, start+9);
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				QnaBean qna = new QnaBean();
				qna.setQna_num(rs.getInt("qna_num"));
				qna.setQna_id(rs.getString("qna_id"));
				qna.setQna_subject(rs.getString("qna_subject"));
				qna.setQna_content(rs.getString("qna_content"));
				qna.setQna_category(rs.getString("qna_category"));
				qna.setQna_re_ref(rs.getInt("qna_re_ref"));
				qna.setQna_re_lev(rs.getInt("qna_re_lev"));
				qna.setQna_re_seq(rs.getInt("qna_re_seq"));
				qna.setQna_date(rs.getDate("qna_date"));
				qna.setQna_del(rs.getString("qna_del"));
				qna.setQna_pw(rs.getString("qna_pw"));
				list.add(qna);
				}
			}catch (Exception e) {
				System.out.println("QnA 리스트 호출 에러  : " +e.getMessage());
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
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql1 = "select count(*) from qna_board";
		
		try {
			if(opt == null)
			{
				pstmt = conn.prepareStatement(sql1);
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
	public QnaBean getDetail(int qnaNum) {
		QnaBean qna = null;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from qna_board where qna_num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qnaNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				qna = new QnaBean();
				qna.setQna_num(qnaNum);
				qna.setQna_id(rs.getString("qna_id"));
				qna.setQna_subject(rs.getString("qna_subject"));
				qna.setQna_content(rs.getString("qna_content"));
				qna.setQna_date(rs.getDate("qna_date"));
				qna.setQna_category(rs.getString("qna_category"));
				qna.setQna_re_ref(rs.getInt("qna_re_ref"));
				qna.setQna_re_lev(rs.getInt("qna_re_lev"));
				qna.setQna_re_seq(rs.getInt("qna_re_seq"));
				qna.setQna_pw(rs.getString("qna_pw"));
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
		return qna;
	}
	//답변글 처리
	public boolean updateReSeq(QnaBean qna) {
		
		boolean result = false;
		int ref = qna.getQna_re_ref();
		int seq = qna.getQna_re_seq();
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "update qna_board set qna_re_seq = qna_re_seq+1 where qna_re_ref=? and qna_re_seq>?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ref);
			pstmt.setInt(2, seq);
			
			int flag = pstmt.executeUpdate();
			if(flag>0) {
				result = true;
			}
		}catch(Exception e) {
			System.out.println("답변글 dao 에러 : " + e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) { }
		}
		return result;
	}

	public boolean deleteQna(int qnaNum) {
		
		boolean result = false;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql="update qna_board set qna_del='y' where qna_num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qnaNum);
			
			int flag = pstmt.executeUpdate();
			if(flag>0) {
				result = true;
			}
		}catch(Exception e) {
			System.out.println("삭제 에러  : " + e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) { }
		}
		return result;
	}
	//게시글 수정
	public boolean updateBoard(QnaBean qna) {
		
		boolean result = false;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "update qna_board set qna_subject=?, qna_content=?, qna_category=?, qna_date=sysdate where qna_num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qna.getQna_subject());
			pstmt.setString(2, qna.getQna_content());
			pstmt.setString(3, qna.getQna_category());
			pstmt.setInt(4, qna.getQna_num());
			
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
	//내가 쓴 게시글 검색
	public List<QnaBean> selectmylist(String qna_id){
		List<QnaBean> list = new ArrayList<>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println("qna_id : " + qna_id);
		String sql = "select * from qna_board where qna_id=? order by qna_num desc";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qna_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				QnaBean qna = new QnaBean();
				qna.setQna_num(rs.getInt("qna_num"));
				qna.setQna_id(rs.getString("qna_id"));
				qna.setQna_subject(rs.getString("qna_subject"));
				qna.setQna_content(rs.getString("qna_content"));
				qna.setQna_category(rs.getString("qna_category"));
				/*
				 * qna.setQna_re_ref(rs.getInt("qna_re_ref"));
				 * qna.setQna_re_lev(rs.getInt("qna_re_lev"));
				 * qna.setQna_re_seq(rs.getInt("qna_re_seq"));
				 */
				qna.setQna_date(rs.getDate("qna_date"));
				qna.setQna_del(rs.getString("qna_del"));
				list.add(qna);
					}
			}catch (Exception e) {
				System.out.println("QnA 리스트 호출 에러  : " +e.getMessage());
				}finally {
					try {
						if(pstmt != null) pstmt.close();
						if(conn != null) conn.close();
						if(rs != null) rs.close();
					}catch(Exception e) {}
				}
			return list;
	}
	//비밀번호 체크
	public QnaBean getPassword(int qnaNum) {
		QnaBean qna = null;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from qna_board where qna_num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qnaNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				qna = new QnaBean();
				qna.setQna_num(qnaNum);
				qna.setQna_id(rs.getString("qna_id"));
				qna.setQna_subject(rs.getString("qna_subject"));
				qna.setQna_content(rs.getString("qna_content"));
				qna.setQna_date(rs.getDate("qna_date"));
				qna.setQna_category(rs.getString("qna_category"));
				qna.setQna_re_ref(rs.getInt("qna_re_ref"));
				qna.setQna_re_lev(rs.getInt("qna_re_lev"));
				qna.setQna_re_seq(rs.getInt("qna_re_seq"));
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
		return qna;
	}
}
