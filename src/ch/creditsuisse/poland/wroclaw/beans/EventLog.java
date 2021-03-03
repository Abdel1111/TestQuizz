package ch.creditsuisse.poland.wroclaw.beans;

public class EventLog {
	protected String id;
	protected Integer duration;
	protected String host;
	protected String type;
	protected boolean flag= false;
	
	public EventLog(String id, Integer duration, String host, String type) {
		super();
		this.id = id;
		this.duration = duration;
		this.host = host;
		this.type = type;
	}

	public EventLog() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
