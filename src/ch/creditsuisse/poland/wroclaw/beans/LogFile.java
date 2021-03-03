package ch.creditsuisse.poland.wroclaw.beans;

import java.math.BigDecimal;

public class LogFile {
	protected String id;
	protected String state;
	protected String type;
	protected BigDecimal timestamp;
	protected String host="";

	public LogFile(String id, String state, String type, BigDecimal timestamp, String host) {
		super();
		this.id = id;
		this.state = state;
		this.type = type;
		this.timestamp = timestamp;
		this.host = host;
	}

	public LogFile() {
		super();
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(BigDecimal timestamp) {
		this.timestamp = timestamp;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
}
