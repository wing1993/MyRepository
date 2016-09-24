package com.df.dao.pojo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * DiscipleReply entity. @author MyEclipse Persistence Tools
 */

public class DiscipleReply  implements java.io.Serializable {


    // Fields    

     private Integer replyId;
     private DiscipleReply discipleReply;
     private DiscipleArea discipleArea;
     private String respondent;
     private Timestamp replyTime;
     private String replyContent;
     private String voice;
     private String con1;
     private String con2;
     private String con3;
     private String con4;
     private String con5;
     private String con6;
     private String con7;
     private Set discipleReplies = new HashSet(0);


    // Constructors

    /** default constructor */
    public DiscipleReply() {
    }

	/** minimal constructor */
    public DiscipleReply(DiscipleArea discipleArea, String respondent, Timestamp replyTime, String replyContent) {
        this.discipleArea = discipleArea;
        this.respondent = respondent;
        this.replyTime = replyTime;
        this.replyContent = replyContent;
    }
    
    /** full constructor */
    public DiscipleReply(DiscipleReply discipleReply, DiscipleArea discipleArea, String respondent, Timestamp replyTime, String replyContent, String voice, String con1, String con2, String con3, String con4, String con5, String con6, String con7, Set discipleReplies) {
        this.discipleReply = discipleReply;
        this.discipleArea = discipleArea;
        this.respondent = respondent;
        this.replyTime = replyTime;
        this.replyContent = replyContent;
        this.voice = voice;
        this.con1 = con1;
        this.con2 = con2;
        this.con3 = con3;
        this.con4 = con4;
        this.con5 = con5;
        this.con6 = con6;
        this.con7 = con7;
        this.discipleReplies = discipleReplies;
    }

   
    // Property accessors

    public Integer getReplyId() {
        return this.replyId;
    }
    
    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public DiscipleReply getDiscipleReply() {
        return this.discipleReply;
    }
    
    public void setDiscipleReply(DiscipleReply discipleReply) {
        this.discipleReply = discipleReply;
    }

    public DiscipleArea getDiscipleArea() {
        return this.discipleArea;
    }
    
    public void setDiscipleArea(DiscipleArea discipleArea) {
        this.discipleArea = discipleArea;
    }

    public String getRespondent() {
        return this.respondent;
    }
    
    public void setRespondent(String respondent) {
        this.respondent = respondent;
    }

    public Timestamp getReplyTime() {
        return this.replyTime;
    }
    
    public void setReplyTime(Timestamp replyTime) {
        this.replyTime = replyTime;
    }

    public String getReplyContent() {
        return this.replyContent;
    }
    
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getVoice() {
        return this.voice;
    }
    
    public void setVoice(String voice) {
        this.voice = voice;
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

    public Set getDiscipleReplies() {
        return this.discipleReplies;
    }
    
    public void setDiscipleReplies(Set discipleReplies) {
        this.discipleReplies = discipleReplies;
    }
   








}