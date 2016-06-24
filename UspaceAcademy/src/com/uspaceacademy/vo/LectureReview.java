package com.uspaceacademy.vo;

import org.hibernate.validator.constraints.NotEmpty;

public class LectureReview{
	
	private int reviewNo;//글번호
	private String reviewWriter;//글쓴이
	
	private String lectureTitle;//강의명
	
	private String lectureSubject;//강의과목
	
	@NotEmpty(message="제목을 입력해주세요")
	private String reviewTitle;//글제목
	
	@NotEmpty(message="내용을 입력해주세요")
	private String reviewContent;//글내용
	
	private String reviewDate;//글등록일
	private int reviewHit;//글조회
	
	
	
	
	public LectureReview() {
	}
	
	//객체생성 밸리 (lectureReview_register.jsp 에서 컨트롤러로 보내는  4개의 값에 대한 생성자)*
	public LectureReview(String lectureTitle, String lectureSubject, String reviewTitle, String reviewContent) {
		super();
		this.lectureTitle = lectureTitle;
		this.lectureSubject = lectureSubject;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
	}


	//date없는 생성자
	public LectureReview(int reviewNo, String reviewWriter, String lectureTitle, String lectureSubject,
			String reviewTitle, String reviewContent, int reviewHit) {
		super();
		this.reviewNo = reviewNo;
		this.reviewWriter = reviewWriter;
		this.lectureTitle = lectureTitle;
		this.lectureSubject = lectureSubject;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
		this.reviewHit = reviewHit;
	}



	public LectureReview(int reviewNo, String reviewWriter, String lectureTitle, String lectureSubject,
			String reviewTitle, String reviewContent, String reviewDate, int reviewHit) {
		this.reviewNo = reviewNo;
		this.reviewWriter = reviewWriter;
		this.lectureTitle = lectureTitle;
		this.lectureSubject = lectureSubject;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
		this.reviewDate = reviewDate;
		this.reviewHit = reviewHit;
	}
	
	
	
	//수정시  (6개)
	public LectureReview(int reviewNo, String reviewWriter, String lectureTitle, String lectureSubject, String reviewTitle, String reviewContent) {
		super();
		this.reviewNo=reviewNo; //오류:이거빠져있었음....
		this.reviewWriter=reviewWriter;
		this.lectureTitle = lectureTitle;
		this.lectureSubject = lectureSubject;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
	}

	@Override
	public String toString() {
		return "LectureReview [reviewNo=" + reviewNo + ", reviewWriter=" + reviewWriter + ", lectureTitle="
				+ lectureTitle + ", lectureSubject=" + lectureSubject + ", reviewTitle=" + reviewTitle
				+ ", reviewContent=" + reviewContent + ", reviewDate=" + reviewDate + ", reviewHit=" + reviewHit + "]";
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getReviewWriter() {
		return reviewWriter;
	}

	public void setReviewWriter(String reviewWriter) {
		this.reviewWriter = reviewWriter;
	}

	public String getLectureTitle() {
		return lectureTitle;
	}

	public void setLectureTitle(String lectureTitle) {
		this.lectureTitle = lectureTitle;
	}

	public String getLectureSubject() {
		return lectureSubject;
	}

	public void setLectureSubject(String lectureSubject) {
		this.lectureSubject = lectureSubject;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	public int getReviewHit() {
		return reviewHit;
	}

	public void setReviewHit(int reviewHit) {
		this.reviewHit = reviewHit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lectureSubject == null) ? 0 : lectureSubject.hashCode());
		result = prime * result + ((lectureTitle == null) ? 0 : lectureTitle.hashCode());
		result = prime * result + ((reviewContent == null) ? 0 : reviewContent.hashCode());
		result = prime * result + ((reviewDate == null) ? 0 : reviewDate.hashCode());
		result = prime * result + reviewHit;
		result = prime * result + reviewNo;
		result = prime * result + ((reviewTitle == null) ? 0 : reviewTitle.hashCode());
		result = prime * result + ((reviewWriter == null) ? 0 : reviewWriter.hashCode());
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
		LectureReview other = (LectureReview) obj;
		if (lectureSubject == null) {
			if (other.lectureSubject != null)
				return false;
		} else if (!lectureSubject.equals(other.lectureSubject))
			return false;
		if (lectureTitle == null) {
			if (other.lectureTitle != null)
				return false;
		} else if (!lectureTitle.equals(other.lectureTitle))
			return false;
		if (reviewContent == null) {
			if (other.reviewContent != null)
				return false;
		} else if (!reviewContent.equals(other.reviewContent))
			return false;
		if (reviewDate == null) {
			if (other.reviewDate != null)
				return false;
		} else if (!reviewDate.equals(other.reviewDate))
			return false;
		if (reviewHit != other.reviewHit)
			return false;
		if (reviewNo != other.reviewNo)
			return false;
		if (reviewTitle == null) {
			if (other.reviewTitle != null)
				return false;
		} else if (!reviewTitle.equals(other.reviewTitle))
			return false;
		if (reviewWriter == null) {
			if (other.reviewWriter != null)
				return false;
		} else if (!reviewWriter.equals(other.reviewWriter))
			return false;
		return true;
	}
	
	
	
}
