package com.df.dao.pojo;

import java.util.HashSet;
import java.util.Set;


/**
 * Question entity. @author MyEclipse Persistence Tools
 */

public class Question  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer QId;
     private String username;
     private String QTime;
     private String QTypeName;
     private String askWho;
     private String QTitle;
     private String QContent;
     private Integer state;
     private Integer visits;
     private String sharezone;
     private Integer shareState;
     private String con1;    //最后回复的人
     private String con2;	 //最后回复的时间
     private String con3;	 //回复的人数
     private String con4;
     private String con5;    
     private String con6; 
     private String con7;
     private Set publicReplies = new HashSet(0);
     private Set replies = new HashSet(0);
     private Set discipleReplies = new HashSet(0);
     private Set myquestionReplies = new HashSet(0);
     private Set studentReplies = new HashSet(0);


    // Constructors

    /** default constructor */
    public Question() {
    }

	/** minimal constructor */
    public Question(String username, String QTime, String QTypeName, String QTitle, String QContent, Integer state, Integer visits, String sharezone, Integer shareState) {
        this.username = username;
        this.QTime = QTime;
        this.QTypeName = QTypeName;
        this.QTitle = QTitle;
        this.QContent = QContent;
        this.state = state;
        this.visits = visits;
        this.sharezone = sharezone;
        this.shareState = shareState;
    }
    
    /** full constructor */
    public Question(String username, String QTime, String QTypeName, String askWho, String QTitle, String QContent, Integer state, Integer visits, String sharezone, Integer shareState, String con1, String con2, String con3, String con4, String con5, String con6, String con7, Set publicReplies, Set replies, Set discipleReplies, Set myquestionReplies, Set studentReplies) {
        this.username = username;
        this.QTime = QTime;
        this.QTypeName = QTypeName;
        this.askWho = askWho;
        this.QTitle = QTitle;
        this.QContent = QContent;
        this.state = state;
        this.visits = visits;
        this.sharezone = sharezone;
        this.shareState = shareState;
        this.con1 = con1;
        this.con2 = con2;
        this.con3 = con3;
        this.con4 = con4;
        this.con5 = con5;
        this.con6 = con6;
        this.con7 = con7;
        this.publicReplies = publicReplies;
        this.replies = replies;
        this.discipleReplies = discipleReplies;
        this.myquestionReplies = myquestionReplies;
        this.studentReplies = studentReplies;
    }

   
    // Property accessors

    public Integer getQId() {
        return this.QId;
    }
    
    public void setQId(Integer QId) {
        this.QId = QId;
    }

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getQTime() {
        return this.QTime;
    }
    
    public void setQTime(String QTime) {
        this.QTime = QTime;
    }

    public String getQTypeName() {
        return this.QTypeName;
    }
    
    public void setQTypeName(String QTypeName) {
        this.QTypeName = QTypeName;
    }

    public String getAskWho() {
        return this.askWho;
    }
    
    public void setAskWho(String askWho) {
        this.askWho = askWho;
    }

    public String getQTitle() {
        return this.QTitle;
    }
    
    public void setQTitle(String QTitle) {
        this.QTitle = QTitle;
    }

    public String getQContent() {
        return this.QContent;
    }
    
    public void setQContent(String QContent) {
        this.QContent = QContent;
    }

    public Integer getState() {
        return this.state;
    }
    
    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getVisits() {
        return this.visits;
    }
    
    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public String getSharezone() {
        return this.sharezone;
    }
    
    public void setSharezone(String sharezone) {
        this.sharezone = sharezone;
    }

    public Integer getShareState() {
        return this.shareState;
    }
    
    public void setShareState(Integer shareState) {
        this.shareState = shareState;
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

    public Set getPublicReplies() {
        return this.publicReplies;
    }
    
    public void setPublicReplies(Set publicReplies) {
        this.publicReplies = publicReplies;
    }

    public Set getReplies() {
        return this.replies;
    }
    
    public void setReplies(Set replies) {
        this.replies = replies;
    }

    public Set getDiscipleReplies() {
        return this.discipleReplies;
    }
    
    public void setDiscipleReplies(Set discipleReplies) {
        this.discipleReplies = discipleReplies;
    }

    public Set getMyquestionReplies() {
        return this.myquestionReplies;
    }
    
    public void setMyquestionReplies(Set myquestionReplies) {
        this.myquestionReplies = myquestionReplies;
    }

    public Set getStudentReplies() {
        return this.studentReplies;
    }
    
    public void setStudentReplies(Set studentReplies) {
        this.studentReplies = studentReplies;
    }

	@Override
	public String toString() {
		return "Question [QId=" + QId + ", username=" + username + ", QTime="
				+ QTime + ", QTypeName=" + QTypeName + ", askWho=" + askWho
				+ ", QTitle=" + QTitle + ", QContent=" + QContent + ", state="
				+ state + ", visits=" + visits + ", sharezone=" + sharezone
				+ ", shareState=" + shareState + ", con1=" + con1 + ", con2="
				+ con2 + ", con3=" + con3 + ", con4=" + con4 + ", con5=" + con5
				+ ", con6=" + con6 + ", con7=" + con7 +  "]";
	}
   








}