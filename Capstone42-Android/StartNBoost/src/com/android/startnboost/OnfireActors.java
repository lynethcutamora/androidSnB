package com.android.startnboost;

public class OnfireActors {

	private String name;
	private String dateofpost;
	private String postcontent;
	private String imageprofile;
	private String imagepost;

	public OnfireActors() {
		// TODO Auto-generated constructor stub
	}

	public OnfireActors(String name, String dateofpost, String postcontent,
			String imageprofile, String imagepost) {
		super();
		this.name = name;
		this.dateofpost = dateofpost;
		this.postcontent = postcontent;
		this.imageprofile = imageprofile;
		this.imagepost = imagepost;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getdateofpost() {
		return dateofpost;
	}

	public void setdateofpost(String dateofpost) {
		this.dateofpost = dateofpost;
	}
	public String getpostcontent() {
		return postcontent;
	}

	public void setpostcontent(String postcontent) {
		this.postcontent = postcontent;
	}

	public String getimageprofile() {
		return imageprofile;
	}

	public void setimageprofile(String imageprofile) {
		this.imageprofile = imageprofile;
	}

	public String getimagepost() {
		return imagepost;
	}

	public void setimagepost(String imagepost) {
		this.imagepost = imagepost;
	}

}

