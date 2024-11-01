package com.ssafy.mvc.model.dto;

public class User {
	private String userId;
	private String password;
	private String name;
	private int curriculumCode;
	private String curriculumName;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCurriculumCode() {
		return curriculumCode;
	}

	public void setCurriculumCode(int curriculumCode) {
		this.curriculumCode = curriculumCode;
	}

	public String getCurriculumName() {
		return curriculumName;
	}

	public void setCurriculumName(String curriculumName) {
		this.curriculumName = curriculumName;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", curriculumCode=" + curriculumCode
				+ ", curriculumName=" + curriculumName + "]";
	}
	

}
