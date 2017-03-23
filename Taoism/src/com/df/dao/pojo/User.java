package com.df.dao.pojo;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String username;
	private String password;
	private String userType;
	private String gender;
	private String city;
	private String phone;
	private String realname;
	private String birthday;
	private String picture;
	private String qq;
	private String weixin;
	private String mail;
	private String introduce;
	private Integer state;
	private String con1; // 用户修改用户类型资料
	private String con2;
	private String con3;
	private String con4;
	private String con5;
	private String con6;
	private String con7;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** 登录 */
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public User(Integer userId, String username, String password) {
		this.userId = userId;
		this.username = username;
		this.password = password;
	}

	/** minimal constructor */
	public User(String username, String password, String userType,
			String gender, String city, String phone, String mail) {
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.gender = gender;
		this.city = city;
		this.phone = phone;
		this.mail = mail;
	}

	/** full constructor */
	public User(String username, String password, String userType,
			String gender, String city, String phone, String realname,
			String birthday, String picture, String qq, String weixin,
			String mail, String introduce, Integer state, String con1,
			String con2, String con3, String con4, String con5, String con6,
			String con7) {
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.gender = gender;
		this.city = city;
		this.phone = phone;
		this.realname = realname;
		this.birthday = birthday;
		this.picture = picture;
		this.qq = qq;
		this.weixin = weixin;
		this.mail = mail;
		this.introduce = introduce;
		this.state = state;
		this.con1 = con1;
		this.con2 = con2;
		this.con3 = con3;
		this.con4 = con4;
		this.con5 = con5;
		this.con6 = con6;
		this.con7 = con7;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeixin() {
		return this.weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getIntroduce() {
		return this.introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCon1() {
		return this.con1;
	}

	public void setCon1(String con1) {
		this.con1 = con1;
	}

	public String getCon2() {
		return this.con2;
	}

	public void setCon2(String con2) {
		this.con2 = con2;
	}

	public String getCon3() {
		return this.con3;
	}

	public void setCon3(String con3) {
		this.con3 = con3;
	}

	public String getCon4() {
		return this.con4;
	}

	public void setCon4(String con4) {
		this.con4 = con4;
	}

	public String getCon5() {
		return this.con5;
	}

	public void setCon5(String con5) {
		this.con5 = con5;
	}

	public String getCon6() {
		return this.con6;
	}

	public void setCon6(String con6) {
		this.con6 = con6;
	}

	public String getCon7() {
		return this.con7;
	}

	public void setCon7(String con7) {
		this.con7 = con7;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username
				+ ", password=" + password + ", userType=" + userType
				+ ", gender=" + gender + ", city=" + city + ", phone=" + phone
				+ ", realname=" + realname + ", birthday=" + birthday
				+ ", picture=" + picture + ", qq=" + qq + ", weixin=" + weixin
				+ ", mail=" + mail + ", introduce=" + introduce + ", state="
				+ state + ", con1=" + con1 + ", con2=" + con2 + ", con3="
				+ con3 + ", con4=" + con4 + ", con5=" + con5 + ", con6=" + con6
				+ ", con7=" + con7 + "]";
	}

}