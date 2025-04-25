package com.ssafy.fitcha.model.dto;

public class File {
private int challengeFileId;
	private int challengeBoardId;
	private String fileOriginalName;
	private String fileUrl;
	private String writer;
	public int getChallengeFileId() {
		return challengeFileId;
	}
	public void setChallengeFileId(int challengeFileId) {
		this.challengeFileId = challengeFileId;
	}
	public int getChallengeBoardId() {
		return challengeBoardId;
	}
	public void setChallengeBoardId(int challengeBoardId) {
		this.challengeBoardId = challengeBoardId;
	}
	public String getFileOriginalName() {
		return fileOriginalName;
	}
	public void setFileOriginalName(String fileOriginalName) {
		this.fileOriginalName = fileOriginalName;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}

}
