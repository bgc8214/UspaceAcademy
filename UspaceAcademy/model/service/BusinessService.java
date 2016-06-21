package spring.aop.model.service;

public interface BusinessService {

	String findByName(String name);

	void register(String id, String password, String name);

	void deleteById(String id) throws Exception;

}