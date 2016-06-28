package com.uspaceacademy.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uspaceacademy.util.Constants;
import com.uspaceacademy.vo.Assignment;
import com.uspaceacademy.vo.LectureReview;


@Repository
public class AssignmentDao {

	
	private String namespace="assignmentMapper.";
	
	
	private SqlSessionTemplate session;
	
	@Autowired
	public AssignmentDao(SqlSessionTemplate session){
		this.session = session;
	}

	
	//----------------------------------------------------------
	
	
		//1. 삽입(게시물등록)ㅇ
		public int insert(Assignment assignment){
			return session.insert(namespace+"insert", assignment);
		}
		
		
		
		//2. 게시물 시퀀스 no sequence ㅇ
		public int selectNextNo(){
			return session.selectOne(namespace+"selectNextNo");
		}
		
		
		
		//3. 전제조회 ㅇ
		public List selectList(String type){
			
		return session.selectList(namespace+"select",type);
		}
/*public List<Assignment> selectList(){
			
			System.out.println("AssignmentDao  +  selectList ");
			List<Assignment> list = session.selectList(namespace+"select");
			System.out.println(list);
			return list;
		}*/
		
		
		
		//4. 수정 ㅇ
		public int update(Assignment assignment){
		return session.update(namespace+"update",assignment);
		}
		
		
		
		//5. (no로) 삭제  ㅇ
		public int deleteByNo(int assignmentNo){
		return session.delete(namespace+"deleteByNo", assignmentNo);// mapper에서 deleteByNo 로함 잘보기 *
		}
		
		
		
		//6. no로 조회 ( 세부조회할때) ㅇ
		public Assignment selectNo(int assignmentNo){
//			System.out.printf("DAO - "+session.selectOne(namespace+"selectNo", assignmentNo));
			return session.selectOne(namespace+"selectNo", assignmentNo); 
		}
		
		
		
		
		//-------------------------------------------------------------------------------------------------------------
		
	
		//7.조회수 처리
		public Assignment selectHit(Assignment assignment){
			assignment.setAssignmentHit(assignment.getAssignmentHit()+1); //dao - 여기서 1증가 시키기
			System.out.printf("DAo - "+session.selectOne(namespace+"updateHit",assignment));
			return session.selectOne(namespace+"updateHit",assignment);
		}

		
		
		
		//8.페이징 selectPaging
		public List selectPaging(int page){
			Map map = new HashMap();
			map.put("page", page);
			map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
			return session.selectList(namespace+"selectPaging", map);
		}
		
		//9.페이징 selectCount
		public int selectCount(){
			return session.selectOne(namespace+"selectCount");
		}
		
		
		
		//--------------------------------------------------------------------
	

		//10.답글 달기 dao
		public int reply(Assignment assignment){
			return (int) session.selectOne(namespace+"reply");
		}
		

		
		
		
		
		
		
		
	
	
}















