package com.ssafy.fitcha.model.dto;

public class User {
	private int userBoardId; // DB user 테이블에서 user들을 식별하는 고유 id (auto_increment로 생김)
	private String userId;
	private String password;
	private String email;
	private String name;
	private String nickName;
	private int age;
	private String gender;

	public int getUserBoardId() {
		return userBoardId;
	}

	public void setUserBoardId(int userBoardId) {
		this.userBoardId = userBoardId;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "User [userBoardId=" + userBoardId + ", userId=" + userId + ", password=" + password + ", email=" + email
				+ ", name=" + name + ", nickName=" + nickName + ", age=" + age + ", gender=" + gender + "]";
	}
	
	
	
	
}
