package com.df.dao.pojo;

import java.text.SimpleDateFormat;

/**
 * Message entity. @author MyEclipse Persistence Tools
 */

public class Message implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Integer messageId;
	private String author;
	private String publishTime;
	private String messageContent;
	private String con1;//消息标题
	private String con2;
	private String con3;
	private String con4;
	private String con5;

	// Constructors

	/** default constructor */
	public Message() {
	}

	/** minimal constructor */
	public Message(String author, String publishTime, String messageContent) {
		this.author = author;
		this.publishTime = publishTime;
		this.messageContent = messageContent;
	}

	/** full constructor */
	public Message(String author, String publishTime, String messageContent,
			String con1, String con2, String con3, String con4, String con5) {
		this.author = author;
		this.publishTime = publishTime;
		this.messageContent = messageContent;
		this.con1 = con1;
		this.con2 = con2;
		this.con3 = con3;
		this.con4 = con4;
		this.con5 = con5;
	}

	// Property accessors

	public Integer getMessageId() {
		return this.messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishTime() {
		return this.publishTime;
		/*return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(this.publishTime);*/
	}

	public void setPublishTime(String publishTime) {
		this.publishTime=publishTime;
		/*this.publishTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(this.publishTime);*/
	}

	public String getMessageContent() {
		return this.messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
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

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", author=" + author
				+ ", publishTime=" + publishTime + ", messageContent="
				+ messageContent + ", con1=" + con1 + ", con2=" + con2
				+ ", con3=" + con3 + ", con4=" + con4 + ", con5=" + con5 + "]";
	}

}