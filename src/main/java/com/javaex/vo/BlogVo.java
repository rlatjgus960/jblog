package com.javaex.vo;

public class BlogVo {

	private String id;
	private String blogTitle;
	private String logoFile;
	private String userName;
	private int cateNo;

	public BlogVo() {
		super();
	}
	
	

	public BlogVo(String id, String blogTitle) {
		super();
		this.id = id;
		this.blogTitle = blogTitle;
	}



	public BlogVo(String id, String blogTitle, String logoFile, String userName, int cateNo) {
		super();
		this.id = id;
		this.blogTitle = blogTitle;
		this.logoFile = logoFile;
		this.userName = userName;
		this.cateNo = cateNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	@Override
	public String toString() {
		return "BlogVo [id=" + id + ", blogTitle=" + blogTitle + ", logoFile=" + logoFile + ", userName=" + userName
				+ ", cateNo=" + cateNo + "]";
	}

}
