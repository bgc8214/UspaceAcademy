package com.uspaceacademy.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class Comment implements Serializable{
	private int commentNo;
	
	@NotEmpty(message="내용을 입력하세요.")
	private String commentContent;
	
	private String commentDate, commentWriter, commentType;
	private int advancedNo2;
	
	private Inquiry inquiry;
	
	public Comment(){}
	
	public Comment(int commentNo, String commentContent, String commentDate, String commentType, int advancedNo2) {
		super();
		this.commentNo = commentNo;
		this.commentContent = commentContent;
		this.commentDate = commentDate;
		this.commentType = commentType;
		this.advancedNo2 = advancedNo2;
	}
	
	public Comment(int commentNo, String commentContent, String commentDate) {
		super();
		this.commentNo = commentNo;
		this.commentContent = commentContent;
		this.commentDate = commentDate;
	}
	
	public Comment(int commentNo, String commentContent, String commentDate, int advancedNo2) {
		super();
		this.commentNo = commentNo;
		this.commentContent = commentContent;
		this.commentDate = commentDate;
		this.advancedNo2 = advancedNo2;
	}

	public Comment(int commentNo, String commentContent, String commentDate, String commentWriter, String commentType,
			int advancedNo2, Inquiry inquiry) {
		super();
		this.commentNo = commentNo;
		this.commentContent = commentContent;
		this.commentDate = commentDate;
		this.commentWriter = commentWriter;
		this.commentType = commentType;
		this.advancedNo2 = advancedNo2;
		this.inquiry = inquiry;
	}
	
	public Comment(int commentNo, String commentContent, String commentDate, String commentWriter, String commentType,
			int advancedNo2) {
		super();
		this.commentNo = commentNo;
		this.commentContent = commentContent;
		this.commentDate = commentDate;
		this.commentWriter = commentWriter;
		this.commentType = commentType;
		this.advancedNo2 = advancedNo2;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	public String getCommentWriter() {
		return commentWriter;
	}

	public void setCommentWriter(String commentWriter) {
		this.commentWriter = commentWriter;
	}

	public String getCommentType() {
		return commentType;
	}

	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}

	public int getAdvancedNo2() {
		return advancedNo2;
	}

	public void setAdvancedNo2(int advancedNo2) {
		this.advancedNo2 = advancedNo2;
	}

	public Inquiry getInquiry() {
		return inquiry;
	}

	public void setInquiry(Inquiry inquiry) {
		this.inquiry = inquiry;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + advancedNo2;
		result = prime * result + ((commentContent == null) ? 0 : commentContent.hashCode());
		result = prime * result + ((commentDate == null) ? 0 : commentDate.hashCode());
		result = prime * result + commentNo;
		result = prime * result + ((commentType == null) ? 0 : commentType.hashCode());
		result = prime * result + ((commentWriter == null) ? 0 : commentWriter.hashCode());
		result = prime * result + ((inquiry == null) ? 0 : inquiry.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (advancedNo2 != other.advancedNo2)
			return false;
		if (commentContent == null) {
			if (other.commentContent != null)
				return false;
		} else if (!commentContent.equals(other.commentContent))
			return false;
		if (commentDate == null) {
			if (other.commentDate != null)
				return false;
		} else if (!commentDate.equals(other.commentDate))
			return false;
		if (commentNo != other.commentNo)
			return false;
		if (commentType == null) {
			if (other.commentType != null)
				return false;
		} else if (!commentType.equals(other.commentType))
			return false;
		if (commentWriter == null) {
			if (other.commentWriter != null)
				return false;
		} else if (!commentWriter.equals(other.commentWriter))
			return false;
		if (inquiry == null) {
			if (other.inquiry != null)
				return false;
		} else if (!inquiry.equals(other.inquiry))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [commentNo=" + commentNo + ", commentContent=" + commentContent + ", commentDate=" + commentDate
				+ ", commentWriter=" + commentWriter + ", commentType=" + commentType + ", advancedNo2=" + advancedNo2
				+ ", inquiry=" + inquiry + "]";
	}
	
	
}
