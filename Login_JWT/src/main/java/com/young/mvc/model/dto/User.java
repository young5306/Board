package com.young.mvc.model.dto;

import io.micrometer.common.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
// @AllArgsConstructor
// @RequiredArgsConstructor
// @Data
// @Builder
public class User {
	
	private int userId;
	@NonNull
	private String loginId;
	@NonNull
	private String password;
	@NonNull
	private String email;
	private String refreshToken;
	
	private User(Builder builder) {
		this.userId = builder.userId;
		this.loginId = builder.loginId;
		this.password = builder.password;
		this.email = builder.email;
		this.refreshToken = builder.refreshToken;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private int userId;
		private String loginId;
		private String password;
		private String email;
		private String refreshToken;
		
		public Builder userId(int userId) {
			this.userId = userId;
			return this;
		}
		
		public Builder loginId(String loginId) {
			this.loginId = loginId;
			return this;
		}
		
		public Builder password(String password) {
			this.password = password;
			return this;
		}
		
		public Builder email(String email) {
			this.email = email;
			return this;
		}
		
		public Builder refreshToken(String refreshToken) {
			this.refreshToken = refreshToken;
			return this;
		}
		
		public User build() {
			return new User(this);
		}
	}
	
	
}
