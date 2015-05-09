package com.pigai.vo;

import com.pigai.util.UserType;

public class User {
	

	private Integer userId;
	private String userNo;
	private String userName;
	private UserType userType;

	public User() {

	}
	
	public User(Integer userId, String userNo, String userName,
			UserType userType) {
		super();
		this.userId = userId;
		this.userNo = userNo;
		this.userName = userName;
		this.userType = userType;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	


}
