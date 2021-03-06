package com.uspaceacademy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uspaceacademy.dao.AssignmentDao;
import com.uspaceacademy.util.PagingBean;
import com.uspaceacademy.vo.Assignment;
import com.uspaceacademy.vo.LectureReview;

@Service
public class AssignmentService {

	
		@Autowired
		private AssignmentDao dao;
		
		
		
		//------join--------------------------------------------------------------------------------------------------------------
		
		
		
/*		//조인
		public List selectAssignmentLectureJoin(int lectureNo){
			return dao.selectAssignmetLecture(lectureNo);
		}
		*/
		
		//--------------------------------------------------------------------------------------------------------------------
		
		
		//1.ㄷ조회  select id="replyGetList"
		public List replyGetList(){
			
			return dao.replyGetList();
		}
		
		
		
		//2.ㄷ답글등록  insert id="replyReply" 3.ㄷ업데이트 update id="replyAddStep"
		public void replyReplyReplyAddStep(Assignment assignment){
			 dao.replyAddStep(assignment);
			 dao.replyReply(assignment);
		}
		
		/*아래처럼하면 "assignment"가 두개라 아래것만 리턴됨
		 * 이렇게해서 오류났었음  
		 2.ㄷ답글등록  insert id="replyReply" 3.ㄷ업데이트 update id="replyAddStep"
		public HashMap<String,Object> replyReplyReplyAddStep(Assignment assignment){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("assignment",dao.replyAddStep(assignment);
			map.put("assignment",dao.replyReply(assignment);  
			return map;
		}*/
		
		
		
		
		
		
/*		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("assignment", dao.selectPaging(page));															
		map.put("paging", new PagingBean(dao.selectCount(), page));//PagingBean 임폴트.				
		return map;*/
		
		
		
		
		
		
		//--------------------------------------------------------------------------------------------------------------------	
		
		
		//1.게시물등록 ㅇ
		public int insert(Assignment assignment){
			return dao.insert(assignment);
		}
		
		//2.게시물 LectureReview sequence처리 ㅇ
		public int selectNextNo(){
			return dao.selectNextNo();
		}
		
		//3. 전체 리스트ㅇ
		public List selectList(String type){
			return dao.selectList(type);
		}
		
		//4.수정 ㅇ
		public int update(Assignment assignment){
			return dao.update(assignment);
		}
		
		//5.삭제ㅇ
		public int delete(int assignmentNo,int lectureNo){
			return dao.deleteByNo(assignmentNo,lectureNo);
		}
		
		//6.세부조회ㅇ
		public Assignment selectNo(int assignmentNo, int lectureNo){
			return dao.selectNo(assignmentNo,lectureNo);
		}

		
/*		//코드 영주1
		public List selectCodeName(String code){
			System.out.println("렉쳐리뷰 서비스" + code);
			return dao.selectCodeName(code);
		}*/
		
		
		//7.조회수 처리
		public Assignment selectHit(Assignment assignment){
			return dao.selectHit(assignment);
		}

		
		
		// 페이징처리   8.paging + 9.count
		public Map selectPagingCount(int page,int lectureNo){
			Map map = new HashMap();
			map.put("assignment", dao.selectPaging(page, lectureNo));															
			map.put("paging", new PagingBean(dao.selectCount(lectureNo), page));//PagingBean 임폴트.			
			return map;
		}
		
		
		
		//--------------------------------------------------------------------------------------------------------------------	
		

		
		
	
}
