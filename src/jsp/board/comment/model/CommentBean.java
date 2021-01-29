package jsp.board.comment.model;

import java.sql.Date;

public class CommentBean {
	
	private int comment_num; //댓글 글번호
	private int comment_board; //게시글 번호
	private String comment_id; //댓글 작성자
	private Date comment_date; //작성일
	private int comment_parent; //부모글
	private String comment_content; //댓글 내용
	private int comment_level; //댓글의 답변 깊이
	private String comment_del; //삭제여부
	
	public String getComment_del() {
		return comment_del;
	}
	public void setComment_del(String comment_del) {
		this.comment_del = comment_del;
	}
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public int getComment_board() {
		return comment_board;
	}
	public void setComment_board(int comment_board) {
		this.comment_board = comment_board;
	}
	public String getComment_id() {
		return comment_id;
	}
	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}
	public Date getComment_date() {
		return comment_date;
	}
	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}
	public int getComment_parent() {
		return comment_parent;
	}
	public void setComment_parent(int comment_parent) {
		this.comment_parent = comment_parent;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public int getComment_level() {
		return comment_level;
	}
	public void setComment_level(int comment_level) {
		this.comment_level = comment_level;
	}

}
