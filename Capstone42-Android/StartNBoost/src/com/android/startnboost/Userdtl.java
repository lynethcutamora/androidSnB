package com.android.startnboost;

public class Userdtl {
	private String lName;
	private String fName;
	private String midInit;
	private String age;
	private String gender;
	private String shortSelfDescription;
	private String nameOfBusiness;
	private String businessType;
	
	public Userdtl(){}
	public Userdtl(String lName, String fName, String midInit, String age, String gender, String shortSelfDescription, String nameOfBusiness, String businessType) {
		super();
		this.lName = lName;
		this.fName = fName;
		this.midInit = midInit;
		this.age = age;
		this.gender = gender;
		this.shortSelfDescription = shortSelfDescription;
		this.nameOfBusiness = nameOfBusiness;
		this.businessType = businessType;
	}
	public String getlname() {
		return lName;
	}
	public void setlname(String lName) {
		this.lName = lName;
	}
	
	public String getfname() {
		return fName;
	}
	public void setfname(String fName) {
		this.fName = fName;
	}
	
	public String getmidinit() {
		return midInit;
	}
	public void setmidinit(String midInit) {
		this.midInit = midInit;
	}
	
	public String getage() {
		return age;
	}
	public void setage(String age) {
		this.age = age;
	}
	
	public String getgender(){
		return gender;
	}
	public void setgender(String gender){
		this.gender = gender;
	}
	
	public String getdescription(){
		return shortSelfDescription;
	}
	public void setdescription(String shortSelfDescription){
		this.shortSelfDescription = shortSelfDescription;
	}
	
	public String getnameofbusiness(){
		return nameOfBusiness;
	}
	public void setnameofbusiness(String nameOfBusiness){
		this.nameOfBusiness = nameOfBusiness;
	}
	
	public String getbusinesstype(){
		return businessType;
	}
	public void setbusinesstype(String businessType){
		this.businessType = businessType;
	}
}
