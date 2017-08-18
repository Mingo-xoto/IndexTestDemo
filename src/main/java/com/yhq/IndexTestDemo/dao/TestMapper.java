package com.yhq.IndexTestDemo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import com.yhq.IndexTestDemo.pojo.Human;

/**
 * @author HuaQi.Yang
 * @date 2017年8月17日
 */
public interface TestMapper {

	@Insert("<script>" 
			+ "insert into human(name,age,sex,occupation,education,birthday,city,adress,town,village,province,district) values"
			+ "<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">"
			+ "(#{item.name},#{item.age},#{item.sex},#{item.occupation},#{item.education},#{item.birthday},#{item.city},#{item.adress},#{item.town},#{item.village},#{item.province},#{item.district})"
			+ " </foreach>" 
			+ "</script>")
	void batchInsertList(List<Human> list);

	@Insert("<script>" 
			+ "insert into human(name,age,sex,occupation,education,birthday,city,adress,town,village,province,district) values"
			+ "<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">" 
			+ "(" 
			+ "<foreach collection=\"item\" item=\"e\" index=\"index\" separator=\",\">"
			+ "#{e}" 
			+ " </foreach>" 
			+ ")" 
			+ " </foreach>" 
			+ "</script>")
	void batchInsertArray(List<List<Object>> array);

	@Insert("<script>" 
			+ "insert into human(name,age,sex,occupation,education,birthday,city,adress,town,village,province,district) values"
			+ "<foreach collection=\"array\" item=\"item\" index=\"index\" separator=\",\">" 
			+ "(" 
			+ "<foreach collection=\"item\" item=\"e\" index=\"index\" separator=\",\">"
			+ "#{e}" 
			+ " </foreach>" 
			+ ")" 
			+ " </foreach>" 
			+ "</script>")
	void batchInsertArray2(Object[][] array);
}
