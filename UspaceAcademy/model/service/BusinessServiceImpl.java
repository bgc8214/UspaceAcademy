package spring.aop.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.aop.model.dao.Dao;

@Service
public class BusinessServiceImpl implements BusinessService {
	@Autowired
	private Dao dao;
	@Override
	public String findByName(String name){
		
		System.out.printf("이름 %s로 조회했습니다.", name);
		return dao.selectByName(name);
	}
	@Override
	public void register(String id, String password, String name){
		dao.insert(id, password, name);
		System.out.printf("Service - 등록 처리 : ID : %s, 패스워드 : %s, 이름 : %s%n", id, password, name);
	}
	@Override
	public void deleteById(String id) throws Exception{
		throw new Exception(id+"로 삭제 도중 오류 발생");
	}
}
