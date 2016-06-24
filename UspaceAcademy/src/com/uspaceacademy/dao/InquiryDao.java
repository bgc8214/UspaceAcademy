package com.uspaceacademy.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uspaceacademy.util.Constants;
import com.uspaceacademy.vo.Inquiry;

@Repository
public class InquiryDao
{
	@Autowired
	private SqlSessionTemplate session;
	
	public InquiryDao(){}
	
	public InquiryDao(SqlSessionTemplate session) {
		this.session = session;
	}
	
	//전체 조회
	public List<Inquiry> selectAllInquirys(){
		return session.selectList("inquiryMapper.selectAllInquirys");
	}
	
	//등록하기
	public int insertInquiry(Inquiry inquiry){		
		return session.insert("inquiryMapper.insertInquiry", inquiry);
	}
	
	//상세 조회
	public Inquiry selectByAdvancedNo(int advancedNo){
		return session.selectOne("inquiryMapper.selectByAdvancedNo", advancedNo);
	}
	
	//조회수
	public int updateHit(Inquiry inquiry) {
		return session.update("inquiryMapper.updateHit", inquiry);
	}
	
	//수정하기
	public int updateInquiry(Inquiry inquiry){
		return session.update("inquiryMapper.updateInquiry", inquiry);
	}
	
	//삭제하기
	public int deleteByAdvancedNo(int advancedNo){
		return session.delete("inquiryMapper.deleteByAdvancedNo", advancedNo);
	}	
		
	//글 번호 sequence
	public int increaseAdvancedNo(){
		return session.selectOne("inquiryMapper.increaseAdvancedNo");
	}
	
	//코드 조회
	public List selectCodeName(String codeName) {
		return session.selectList("codeTable.selectCodeName", codeName);
	}
	
	//페이징 처리
	public List selectList(int page) {
		Map map = new HashMap();
		map.put("page", page);
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		return session.selectList("inquiryMapper.selectListByPaging", map);
	}
	
	//페이징 처리
	public int selectCountContents() {
		return session.selectOne("inquiryMapper.selectCountContents");
	}

}
