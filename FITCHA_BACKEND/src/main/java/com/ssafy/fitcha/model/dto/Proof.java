package com.ssafy.fitcha.model.dto;

import java.util.List;

public class Proof {

	// 생성자
	public Proof() {
	}

	private int proofBoardId; // 인증글 식별 id
	private String userId; // 작성자 식별 id
	private String title; // 제목
	private String content; // 내용
	private String writer; // 작성자
	private String regDate; // 작성일
	private List<BoardFile> files; // 인증글 파일 리스트

	// getter / setter
	public int getProofBoardId() {
		return proofBoardId;
	}

	public void setProofBoardId(int proofBoardId) {
		this.proofBoardId = proofBoardId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public List<BoardFile> getFiles() {
		return files;
	}

	public void setFiles(List<BoardFile> files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "Proof [proofBoardId=" + proofBoardId + ", userId=" + userId + ", title=" + title + ", content="
				+ content + ", writer=" + writer + ", regDate=" + regDate + ", files=" + files + "]";
	}

}
