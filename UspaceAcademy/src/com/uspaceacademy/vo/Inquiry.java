package com.uspaceacademy.vo;

import java.io.Serializable;
import java.util.List;

public class Inquiry implements Serializable
{
	private int advancedNo;
	private String advancedSecret, advancedTitle, advancedContent, advancedDate;
	private int advancedHit;
	private String advancedId, advancedType;
	private int lectureNo2;
	
	public Inquiry(){}
	
	public Inquiry(int advancedNo, String advancedSecret, String advancedTitle, String advancedContent,
			String advancedDate) {
		super();
		this.advancedNo = advancedNo;
		this.advancedSecret = advancedSecret;
		this.advancedTitle = advancedTitle;
		this.advancedContent = advancedContent;
		this.advancedDate = advancedDate;
	}

	public Inquiry(int advancedNo, String advancedSecret, String advancedTitle, String advancedContent,
			String advancedDate, int advancedHit, String advancedId, String advancedType) {
		super();
		this.advancedNo = advancedNo;
		this.advancedSecret = advancedSecret;
		this.advancedTitle = advancedTitle;
		this.advancedContent = advancedContent;
		this.advancedDate = advancedDate;
		this.advancedHit = advancedHit;
		this.advancedId = advancedId;
		this.advancedType = advancedType;
	}

	@Override
	public String toString() {
		return "Inquiry [advancedNo=" + advancedNo + ", advancedSecret=" + advancedSecret + ", advancedTitle="
				+ advancedTitle + ", advancedContent=" + advancedContent + ", advancedDate=" + advancedDate
				+ ", advancedHit=" + advancedHit + ", advancedId=" + advancedId + ", advancedType=" + advancedType
				+ ", lectureNo2=" + lectureNo2 + "]";
	}

	public int getAdvancedNo() {
		return advancedNo;
	}

	public void setAdvancedNo(int advancedNo) {
		this.advancedNo = advancedNo;
	}

	public String getAdvancedSecret() {
		return advancedSecret;
	}

	public void setAdvancedSecret(String advancedSecret) {
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

	public String getAdvancedType() {
		return advancedType;
	}

	public void setAdvancedType(String advancedType) {
		this.advancedType = advancedType;
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
		result = prime * result + ((advancedSecret == null) ? 0 : advancedSecret.hashCode());
		result = prime * result + ((advancedTitle == null) ? 0 : advancedTitle.hashCode());
		result = prime * result + ((advancedType == null) ? 0 : advancedType.hashCode());
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
		Inquiry other = (Inquiry) obj;
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
		if (advancedSecret == null) {
			if (other.advancedSecret != null)
				return false;
		} else if (!advancedSecret.equals(other.advancedSecret))
			return false;
		if (advancedTitle == null) {
			if (other.advancedTitle != null)
				return false;
		} else if (!advancedTitle.equals(other.advancedTitle))
			return false;
		if (advancedType == null) {
			if (other.advancedType != null)
				return false;
		} else if (!advancedType.equals(other.advancedType))
			return false;
		if (lectureNo2 != other.lectureNo2)
			return false;
		return true;
	}		
}
