package com.young.mvc.model.dto;

public class User {
	private int userId; // 불변 autoincrement값 추가 (변경x값을 참조하는게 안정적)
	private String loginId; // 로그인아이디 (유니크, 변경가능)
	private String password; // 로그인비번
	private String email; // 유니크
	
	public User() {}

	public User(String loginId, String password, String email) {
		this.loginId = loginId;
		this.password = password;
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", loginId=" + loginId + ", password=" + password + ", email=" + email + "]";
	}
	
}
