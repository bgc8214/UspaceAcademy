package spring.aop.model.dao;

import org.springframework.stereotype.Repository;

@Repository
public class DaoImpl implements Dao {
	@Override
	public String selectByName(String name){
		return String.format("Dao - 조회결과 : %s", name);
	}
	@Override
	public void insert(String id, String password, String name){
		System.out.printf("Dao - insert : ID : %s, 패스워드 : %s, 이름 : %s%n", id, password, name);
	}
}
