package com.df.dao.pojo;

/**
 * Admin entity. @author MyEclipse Persistence Tools
 */

public class Admin implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Integer adminId;
	private String adminName;
	private String password;
	private Integer parentId;
	private Integer rsgCheck;
	private Integer updateClass;
	private Integer shieldUser;
	private Integer qtype;
	private Integer postsManage;
	private Integer addAdmin;
	private String lastLogin;
	private String loginIp;
	private Integer con1;
	private Integer con2;

	// Constructors

	/** default constructor */
	public Admin() {
	}

	/** full constructor */
	public Admin(String adminName, String password, int i,
			int j, int k, int l, int m,
			int n, int o, String lastLogin,
			String loginIp, int p, int q) {
		this.adminName = adminName;
		this.password = password;
		this.parentId = i;
		this.rsgCheck = j;
		this.updateClass = k;
		this.shieldUser = l;
		this.qtype = m;
		this.postsManage = n;
		this.addAdmin = o;
		this.lastLogin = lastLogin;
		this.loginIp = loginIp;
		this.con1 = p;
		this.con2 = q;
	}

	// Property accessors

	public Integer getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return this.adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getRsgCheck() {
		return this.rsgCheck;
	}

	public void setRsgCheck(Integer rsgCheck) {
		this.rsgCheck = rsgCheck;
	}

	public Integer getUpdateClass() {
		return this.updateClass;
	}

	public void setUpdateClass(Integer updateClass) {
		this.updateClass = updateClass;
	}

	public Integer getShieldUser() {
		return this.shieldUser;
	}

	public void setShieldUser(Integer shieldUser) {
		this.shieldUser = shieldUser;
	}

	public Integer getQtype() {
		return this.qtype;
	}

	public void setQtype(Integer qtype) {
		this.qtype = qtype;
	}

	public Integer getPostsManage() {
		return this.postsManage;
	}

	public void setPostsManage(Integer postsManage) {
		this.postsManage = postsManage;
	}

	public Integer getAddAdmin() {
		return this.addAdmin;
	}

	public void setAddAdmin(Integer addAdmin) {
		this.addAdmin = addAdmin;
	}

	public String getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Integer getCon1() {
		return this.con1;
	}

	public void setCon1(Integer con1) {
		this.con1 = con1;
	}

	public Integer getCon2() {
		return this.con2;
	}

	public void setCon2(Integer con2) {
		this.con2 = con2;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName
				+ ", password=" + password + ", parentId=" + parentId
				+ ", rsgCheck=" + rsgCheck + ", updateClass=" + updateClass
				+ ", shieldUser=" + shieldUser + ", qtype=" + qtype
				+ ", postsManage=" + postsManage + ", addAdmin=" + addAdmin
				+ ", lastLogin=" + lastLogin + ", loginIp=" + loginIp
				+ ", con1=" + con1 + ", con2=" + con2 + "]";
	}

}