package com.uspaceacademy.vo;

import java.io.Serializable;

public class LectureInquiry implements Serializable{
	private int advancedNo, advancedSecret;
	private String advancedTitle, advancedContent, advancedDate;
	private int advancedHit;
	private String advancedId;
	private int lectureNo2;
	
	public LectureInquiry(){}

	public LectureInquiry(int advancedNo, int advancedSecret, String advancedTitle, String advancedContent,
			String advancedDate, int advancedHit, String advancedId) {
		super();
		this.advancedNo = advancedNo;
		this.advancedSecret = advancedSecret;
		this.advancedTitle = advancedTitle;
		this.advancedContent = advancedContent;
		this.advancedDate = advancedDate;
		this.advancedHit = advancedHit;
		this.advancedId = advancedId;
	}


	public LectureInquiry(int advancedNo, int advancedSecret, String advancedTitle, String advancedContent,
			String advancedDate, int advancedHit, String advancedId, int lectureNo2) {
		super();
		this.advancedNo = advancedNo;
		this.advancedSecret = advancedSecret;
		this.advancedTitle = advancedTitle;
		this.advancedContent = advancedContent;
		this.advancedDate = advancedDate;
		this.advancedHit = advancedHit;
		this.advancedId = advancedId;
		this.lectureNo2 = lectureNo2;
	}

	public int getAdvancedNo() {
		return advancedNo;
	}

	public void setAdvancedNo(int advancedNo) {
		this.advancedNo = advancedNo;
	}

	public int getAdvancedSecret() {
		return advancedSecret;
	}

	public void setAdvancedSecret(int advancedSecret) {
		this.advancedSecret = advancedSecret;
	}

	public String getAdvancedTitle() {
		return advancedTitle;
	}

	public void setAdvancedTitle(String advancedTitle) {
		this.advancedTitle = advancedTitle;
	}

	public String getAdvancedContent() {
		return advancedContent;
	}

	public void setAdvancedContent(String advancedContent) {
		this.advancedContent = advancedContent;
	}

	public String getAdvancedDate() {
		return advancedDate;
	}

	public void setAdvancedDate(String advancedDate) {
		this.advancedDate = advancedDate;
	}

	public int getAdvancedHit() {
		return advancedHit;
	}

	public void setAdvancedHit(int advancedHit) {
		this.advancedHit = advancedHit;
	}

	public String getAdvancedId() {
		return advancedId;
	}

	public void setAdvancedId(String advancedId) {
		this.advancedId = advancedId;
	}

	public int getLectureNo2() {
		return lectureNo2;
	}

	public void setLectureNo2(int lectureNo2) {
		this.lectureNo2 = lectureNo2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((advancedContent == null) ? 0 : advancedContent.hashCode());
		result = prime * result + ((advancedDate == null) ? 0 : advancedDate.hashCode());
		result = prime * result + advancedHit;
		result = prime * result + ((advancedId == null) ? 0 : advancedId.hashCode());
		result = prime * result + advancedNo;
		result = prime * result + advancedSecret;
		result = prime * result + ((advancedTitle == null) ? 0 : advancedTitle.hashCode());
		result = prime * result + lectureNo2;
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
		LectureInquiry other = (LectureInquiry) obj;
		if (advancedContent == null) {
			if (other.advancedContent != null)
				return false;
		} else if (!advancedContent.equals(other.advancedContent))
			return false;
		if (advancedDate == null) {
			if (other.advancedDate != null)
				return false;
		} else if (!advancedDate.equals(other.advancedDate))
			return false;
		if (advancedHit != other.advancedHit)
			return false;
		if (advancedId == null) {
			if (other.advancedId != null)
				return false;
		} else if (!advancedId.equals(other.advancedId))
			return false;
		if (advancedNo != other.advancedNo)
			return false;
		if (advancedSecret != other.advancedSecret)
			return false;
		if (advancedTitle == null) {
			if (other.advancedTitle != null)
				return false;
		} else if (!advancedTitle.equals(other.advancedTitle))
			return false;
		if (lectureNo2 != other.lectureNo2)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LectureInquiry [advancedNo=" + advancedNo + ", advancedSecret=" + advancedSecret + ", advancedTitle="
				+ advancedTitle + ", advancedContent=" + advancedContent + ", advancedDate=" + advancedDate
				+ ", advancedHit=" + advancedHit + ", advancedId=" + advancedId + ", lectureNo2=" + lectureNo2 + "]";
	}	
	
}
