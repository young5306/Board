package com.ssafy.mvc.model.dto;

public class Habit {

	private int habitId;
	private String userId;
	private String content;
	
	public Habit() {
	}

	public Habit(String userId, String content) {
		this.userId = userId;
		this.content = content;
	}

	public int getHabitId() {
		return habitId;
	}

	public void setHabitId(int habitId) {
		this.habitId = habitId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Habit [habitId=" + habitId + ", userId=" + userId + ", content=" + content + "]";
	}
	

}
