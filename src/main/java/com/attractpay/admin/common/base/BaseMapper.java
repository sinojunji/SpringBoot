package com.attractpay.admin.common.base;

import java.util.List;
import java.util.Map;

import com.attractpay.admin.entity.Merchant;
import org.apache.ibatis.annotations.*;

import com.github.pagehelper.Page;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface BaseMapper <T> {

	@Select("select * from ${tableName}")
	List<T> findAll(@Param("tableName") String tableName);
	
	@Select("select * from ${tableName}")
	Page<T> findAllByPage(@Param("tableName") String tableName);

	@Select("select * from ${tableName} where id = #{id}")
	T getOne(@Param("tableName") String tableName, @Param("id") Long id);

	@Insert("<script>"
			+ "insert into ${tableName}"
			+ "<foreach collection=\"params\" index=\"key\" item=\"value\" open=\"(\" separator=\",\" close=\")\">${key}</foreach> "
			+ "values<foreach collection=\"params\" index=\"key\" item=\"value\" open=\"(\" separator=\",\" close=\")\">#{value}</foreach> "
			+ "</script>")
	void save(@Param("tableName") String tableName, @Param("params") Map<String, Object> params);
	
	@Update("<script>"
			+ "update ${tableName} set "
			+ "<foreach collection=\"params\" index=\"key\" item=\"value\" separator=\",\"><if test=\"value!=null\" >${key}=#{value}</if></foreach> "
			+ "where id=#{params.id}"
			+ "</script>")
	void update(@Param("tableName") String tableName, @Param("params") Map<String, Object> params);
	
	@Update("<script>"
			+ "update ${tableName} set "
			+ "<foreach collection=\"params\" index=\"key\" item=\"value\" separator=\",\">${key}=#{value}</foreach> "
			+ "where id=#{params.id}"
			+ "</script>")
	void updateWithNull(@Param("tableName") String tableName, @Param("params") Map<String, Object> params);
	
	@Delete("delete from ${tableName} where id = #{id}")
	void delete(@Param("tableName") String tableName, @Param("id") long id);
	
}
