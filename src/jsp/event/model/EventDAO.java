package jsp.event.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class EventDAO {

	// singleton : 한번 생성한 객체를 같이 사용
		private static EventDAO instance = new EventDAO();

		// 다른 클래스에서 memberDao생성자를 사용할 수 없게 만듬
		private EventDAO() {
			
		}

		public static EventDAO getInstance() {
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
			String sql = "select event_num.nextval from dual";
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
		
		//이벤트 글 작성
		public boolean boardInsert(EventBean event) {

			boolean result = false;
			Connection conn = getConnection();
			PreparedStatement pstmt = null;
			String sql = "insert into event(event_num, event_id, event_file, event_date, event_del)"
					+ "values(?,?,?,sysdate,'n')";
			// 시퀀스 값을 글번호로 사용
			int num = event.getEvent_num();
			
			try {
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, num);
				pstmt.setString(2, event.getEvent_id());
				pstmt.setString(3, event.getEvent_file());

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
		
		//이벤트 목록 호출
		public List<EventBean> selectList(){
			
			List<EventBean> list = new ArrayList<EventBean>();
			Connection conn = getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "select * from event order by event_num desc";
			
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					EventBean event = new EventBean();
					event.setEvent_num(rs.getInt("event_num"));
					event.setEvent_id(rs.getString("event_id"));
					event.setEvent_file(rs.getString("event_file"));
					event.setEvent_date(rs.getDate("event_date"));
					event.setEvent_del(rs.getString("event_del"));
					
					list.add(event);
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

		//이벤트 상세보기
		public EventBean getDetail(int eventNum) {
			
			EventBean event = null;
			Connection conn = getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "select * from event where event_num = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, eventNum);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					event = new EventBean();
					event.setEvent_num(eventNum);
					event.setEvent_id(rs.getString("event_id"));
					event.setEvent_file(rs.getString("event_file"));
					event.setEvent_date(rs.getDate("event_date"));
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
			return event;
		}
		
		//이벤트 수정
		public boolean updateBoard(EventBean event) {
			
			boolean result = false;
			Connection conn = getConnection();
			PreparedStatement pstmt = null;
			String sql = "update event set event_file=?, event_date=sysdate where event_num=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, event.getEvent_file());
				pstmt.setInt(2, event.getEvent_num());
				
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
		
		//삭제할 파일명 호출
		public String getFileName(int eventNum) {
			String fileName = null;
			Connection conn = getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "select event_file from event where event_num=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, eventNum);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					fileName = rs.getString("event_file");
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
		public boolean deleteBoard(int eventNum) {
			boolean result = false;
			Connection conn = getConnection();
			PreparedStatement pstmt = null;
			String sql="update event set event_del='y' where event_num=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, eventNum);
				
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

}
