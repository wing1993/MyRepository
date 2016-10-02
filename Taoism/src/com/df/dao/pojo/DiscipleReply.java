package com.df.dao.pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * DiscipleReply entity. @author MyEclipse Persistence Tools
 */

public class DiscipleReply  implements Comparable<DiscipleReply>,java.io.Serializable {


    // Fields    

     
	private static final long serialVersionUID = 1L;
	private Integer replyId;
     private DiscipleReply discipleReply;
     private Question question;
     private String respondent;
     private String replyTime;
     private String replyContent;
     private String voice;
     private String con1;
     private String con2;
     private String con3;
     private String con4;
     private String con5;
     private String con6;
     private String con7;
     private Set<DiscipleReply> discipleReplies = new TreeSet<DiscipleReply>();


    // ConstructorsString

    /** default constructor */
    public DiscipleReply() {
    }

	/** minimal constructor */
    public DiscipleReply(Question question, String respondent, String replyTime, String replyContent) {
        this.question = question;
        this.respondent = respondent;
        this.replyTime = replyTime;
        this.replyContent = replyContent;
    }
    
    /** full constructor */
    public DiscipleReply(DiscipleReply discipleReply, Question question, String respondent, String replyTime, String replyContent, String voice, String con1, String con2, String con3, String con4, String con5, String con6, String con7, Set discipleReplies) {
        this.discipleReply = discipleReply;
        this.question = question;
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

    public Question getQuestion() {
        return this.question;
    }
    
    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getRespondent() {
        return this.respondent;
    }
    
    public void setRespondent(String respondent) {
        this.respondent = respondent;
    }

    public String getReplyTime() {
        return this.replyTime;
    }
    
    public void setReplyTime(String replyTime) {
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

    public Set<DiscipleReply> getDiscipleReplies() {
        return this.discipleReplies;
    }
    
    public void setDiscipleReplies(Set<DiscipleReply> discipleReplies) {
    	List<DiscipleReply> dList = new ArrayList<DiscipleReply>(discipleReplies);
        Collections.sort(dList,new Comparator<DiscipleReply>(){

			@Override
			public int compare(DiscipleReply o1, DiscipleReply o2) {
				// TODO Auto-generated method stub
				return o1.replyId.compareTo(o2.replyId);
			}
       
        });
        discipleReplies = new LinkedHashSet<DiscipleReply>(dList);
        this.discipleReplies = discipleReplies;
    }

	@Override
	public String toString() {
		return "DiscipleReply [replyId=" + replyId + ", question=" + question
				+ ", respondent=" + respondent + ", replyTime=" + replyTime
				+ ", replyContent=" + replyContent + ", voice=" + voice
				+ ", con1=" + con1 + ", con2=" + con2 + ", con3=" + con3
				+ ", con4=" + con4 + ", con5=" + con5 + ", con6=" + con6
				+ ", con7=" + con7 + ", discipleReplies=" + discipleReplies
				+ "]";
	}

	@Override
	public int compareTo(DiscipleReply o) {
		int result = this.replyTime.compareTo(o.replyTime);
		if (result == 0){
			return this.replyId.compareTo(o.replyId);
		}
		return result;
	}

	 
}