package com.android.startnboost;

public class Users {
	private String userId;
	private String userType;
	private String dateRegistered;
	private String emailAdd;
	private String password;
	private String picid;
	
	public Users(){}
	public Users(String userId, String userType, String dateRegistered, String emailAdd, String password, String picid) {
		super();
		this.userId = userId;
		this.userType = userType;
		this.dateRegistered = dateRegistered;
		this.emailAdd = emailAdd;
		this.password = password;
		this.picid = picid;
	}
	public String getuserid() {
		return userId;
	}
	public void setuserid(String userId) {
		this.userId = userId;
	}
	public String getusertype() {
		return userType;
	}
	public void setusertype(String userType) {
		this.userType = userType;
	}
	public String getdateregistered() {
		return dateRegistered;
	}
	public void setdateregistered(String dateRegistered) {
		this.dateRegistered = dateRegistered;
	}
	public String getemailadd(){
		return emailAdd;
	}
	public void setemailadd(String emailAdd){
		this.emailAdd = emailAdd;
	}
	public String getpassword(){
		return password;
	}
	public void setpassword(String password){
		this.password = password;
	}
	public String getpicid(){
		return picid;
	}
	public void setpicid(String picid){
		this.picid = picid;
	}
}
