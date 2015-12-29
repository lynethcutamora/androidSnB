package com.android.startnboost;

public class Companydtl {
	private String userid;
	private String c_name;
	private String c_businesstype;
	private String c_yearfounded;
	private String c_lname;
	private String c_fname;
	private String c_mi;
	private String c_about;
	
	public Companydtl(){}
	public Companydtl(String userid, String c_name, String c_businesstype, String c_yearfounded, String c_lname, String c_fname, String c_mi, String c_about) {
		super();
		this.userid = userid;
		this.c_name = c_name;
		this.c_businesstype = c_businesstype;
		this.c_yearfounded = c_yearfounded;
		this.c_lname = c_lname;
		this.c_fname = c_fname;
		this.c_mi = c_mi;
		this.c_about = c_about;
	}
	public String getuserid() {
		return userid;
	}
	public void setuserid(String userid) {
		this.userid = userid;
	}
	
	public String getc_name() {
		return c_name;
	}
	public void setc_name(String c_name) {
		this.c_name = c_name;
	}
	
	public String getc_businesstype() {
		return c_businesstype;
	}
	public void setc_businesstype(String c_businesstype) {
		this.c_businesstype = c_businesstype;
	}
	
	public String getc_yearfounded() {
		return c_yearfounded;
	}
	public void setc_yearfounded(String c_yearfounded) {
		this.c_yearfounded = c_yearfounded;
	}
	
	public String getc_lname(){
		return c_lname;
	}
	public void setc_lname(String c_lname){
		this.c_lname = c_lname;
	}
	
	public String getc_fname(){
		return c_fname;
	}
	public void setc_fname(String c_fname){
		this.c_fname = c_fname;
	}
	
	public String getc_mi(){
		return c_mi;
	}
	public void setc_mi(String c_mi){
		this.c_mi = c_mi;
	}
	
	public String getc_about(){
		return c_about;
	}
	public void setc_about(String c_about){
		this.c_about = c_about;
	}
}
