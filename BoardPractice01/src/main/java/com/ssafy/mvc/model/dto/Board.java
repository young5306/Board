package com.ssafy.mvc.model.dto;

import java.util.Date;

public class Board {
	
	private int boardId;
	private String title;
	private String writer;
	private String content;
	private String img = "zzang.png"; // 단일
	private int viewCnt;
	private Date regDate;
	
	public int getboardId() {
		return boardId;
	}
	public void setboardId(int boardId) {
		this.boardId = boardId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	// 단일
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "Board [boardId=" + boardId + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", img=" + img + ", viewCnt=" + viewCnt + ", regDate=" + regDate + "]";
	}
	
}
