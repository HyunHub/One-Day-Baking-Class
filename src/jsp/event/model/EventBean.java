package jsp.event.model;

import java.sql.Date;

public class EventBean {
	
	private int event_num;
	private String event_id;
	private String event_file;
	private Date event_date;
	private String event_del;
	
	public int getEvent_num() {
		return event_num;
	}
	public void setEvent_num(int event_num) {
		this.event_num = event_num;
	}
	public Date getEvent_date() {
		return event_date;
	}
	public void setEvent_date(Date evnet_date) {
		this.event_date = evnet_date;
	}
	public String getEvent_del() {
		return event_del;
	}
	public void setEvent_del(String event_del) {
		this.event_del = event_del;
	}
	public String getEvent_id() {
		return event_id;
	}
	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}
	public String getEvent_file() {
		return event_file;
	}
	public void setEvent_file(String event_file) {
		this.event_file = event_file;
	}
	
}
