package com.uspaceacademy.vo;

import java.io.Serializable;
import java.util.List;

public class FAQ implements Serializable
{
	private int basicNo;
	private String basicWriter;
	private String basicTitle;
	private String basicContent;
	private String basicDate;
	private int basicHit;
	private String basicType;
	
	public FAQ() { }
	
	public FAQ(int basicNo, String basicTitle, String basicContent, String basicDate, int basicHit) {
		super();
		this.basicNo = basicNo;
		this.basicTitle = basicTitle;
		this.basicContent = basicContent;
		this.basicDate = basicDate;
		this.basicHit = basicHit;
	}




	public FAQ(String basicTitle, String basicContent, String basicType) {
		super();
		this.basicTitle = basicTitle;
		this.basicContent = basicContent;
		this.basicType = basicType;
	}

	// 수정시
	public FAQ(int basicNo, String basicTitle, String basicContent, String basicDate) {
		super();
		this.basicNo = basicNo;
		this.basicTitle = basicTitle;
		this.basicContent = basicContent;
		this.basicDate = basicDate;
	}

	public FAQ(int basicNo, String basicWriter, String basicTitle, String basicContent, String basicDate, int basicHit,
			String basicType) {
		super();
		this.basicNo = basicNo;
		this.basicWriter = basicWriter;
		this.basicTitle = basicTitle;
		this.basicContent = basicContent;
		this.basicDate = basicDate;
		this.basicHit = basicHit;
		this.basicType = basicType;
	}

	public int getBasicNo() {
		return basicNo;
	}

	public void setBasicNo(int basicNo) {
		this.basicNo = basicNo;
	}

	public String getBasicWriter() {
		return basicWriter;
	}

	public void setBasicWriter(String basicWriter) {
		this.basicWriter = basicWriter;
	}

	public String getBasicTitle() {
		return basicTitle;
	}

	public void setBasicTitle(String basicTitle) {
		this.basicTitle = basicTitle;
	}

	public String getBasicContent() {
		return basicContent;
	}

	public void setBasicContent(String basicContent) {
		this.basicContent = basicContent;
	}

	public String getBasicDate() {
		return basicDate;
	}

	public void setBasicDate(String basicDate) {
		this.basicDate = basicDate;
	}

	public int getBasicHit() {
		return basicHit;
	}

	public void setBasicHit(int basicHit) {
		this.basicHit = basicHit;
	}

	public String getBasicType() {
		return basicType;
	}

	public void setBasicType(String basicType) {
		this.basicType = basicType;
	}

	@Override
	public String toString() {
		return "FAQ [basicNo=" + basicNo + ", basicWriter=" + basicWriter + ", basicTitle=" + basicTitle
				+ ", basicContent=" + basicContent + ", basicDate=" + basicDate + ", basicHit=" + basicHit
				+ ", basicType=" + basicType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((basicContent == null) ? 0 : basicContent.hashCode());
		result = prime * result + ((basicDate == null) ? 0 : basicDate.hashCode());
		result = prime * result + basicHit;
		result = prime * result + basicNo;
		result = prime * result + ((basicTitle == null) ? 0 : basicTitle.hashCode());
		result = prime * result + ((basicType == null) ? 0 : basicType.hashCode());
		result = prime * result + ((basicWriter == null) ? 0 : basicWriter.hashCode());
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
		FAQ other = (FAQ) obj;
		if (basicContent == null) {
			if (other.basicContent != null)
				return false;
		} else if (!basicContent.equals(other.basicContent))
			return false;
		if (basicDate == null) {
			if (other.basicDate != null)
				return false;
		} else if (!basicDate.equals(other.basicDate))
			return false;
		if (basicHit != other.basicHit)
			return false;
		if (basicNo != other.basicNo)
			return false;
		if (basicTitle == null) {
			if (other.basicTitle != null)
				return false;
		} else if (!basicTitle.equals(other.basicTitle))
			return false;
		if (basicType == null) {
			if (other.basicType != null)
				return false;
		} else if (!basicType.equals(other.basicType))
			return false;
		if (basicWriter == null) {
			if (other.basicWriter != null)
				return false;
		} else if (!basicWriter.equals(other.basicWriter))
			return false;
		return true;
	}
}
