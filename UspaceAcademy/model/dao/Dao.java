package spring.aop.model.dao;

public interface Dao {

	String selectByName(String name);

	void insert(String id, String password, String name);

}