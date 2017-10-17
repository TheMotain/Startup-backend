package fr.iagl.gamification.utils;

public class Notification<T> {
	private String table;
	private T data;
	private String type;
	
	public Notification(String table, T data, String type) {
		super();
		this.table = table;
		this.type = type;
		this.data = data;
	}
	
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
