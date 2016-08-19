package com.df.dao.pojo;

/**
 * QuestionType entity. @author MyEclipse Persistence Tools
 */

public class QuestionType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer QTypeId;
	private String QTypeName;
	private String con1;
	private String con2;
	private String con3;
	private String con4;

	// Constructors

	/** default constructor */
	public QuestionType() {
	}

	/** minimal constructor */
	public QuestionType(String QTypeName) {
		this.QTypeName = QTypeName;
	}

	/** full constructor */
	public QuestionType(String QTypeName, String con1, String con2,
			String con3, String con4) {
		this.QTypeName = QTypeName;
		this.con1 = con1;
		this.con2 = con2;
		this.con3 = con3;
		this.con4 = con4;
	}

	// Property accessors

	public Integer getQTypeId() {
		return this.QTypeId;
	}

	public void setQTypeId(Integer QTypeId) {
		this.QTypeId = QTypeId;
	}

	public String getQTypeName() {
		return this.QTypeName;
	}

	public void setQTypeName(String QTypeName) {
		this.QTypeName = QTypeName;
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

	@Override
	public String toString() {
		return "QuestionType [QTypeId=" + QTypeId + ", QTypeName=" + QTypeName
				+ ", con1=" + con1 + ", con2=" + con2 + ", con3=" + con3
				+ ", con4=" + con4 + "]";
	}

}