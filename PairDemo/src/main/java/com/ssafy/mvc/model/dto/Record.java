package com.ssafy.mvc.model.dto;

import java.time.LocalDate;

public class Record {
	private int recordId;
	private String userId;
	private int habitId;
	private LocalDate date;
	
	public Record() {}

	public Record(String userId, int habitId, LocalDate date) {
		this.userId = userId;
		this.habitId = habitId;
		this.date = date;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getHabitId() {
		return habitId;
	}

	public void setHabitId(int habitId) {
		this.habitId = habitId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Record [recordId=" + recordId + ", userId=" + userId + ", habitId=" + habitId + ", date=" + date
				+ "]";
	};

}
