package com.attractpay.admin.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.attractpay.admin.common.annotation.TableName;
import com.attractpay.admin.entity.Merchant;


public class EntityUtils {
	
	/**
	 * 实体转MAP
	 * @param entity
	 * @return
	 */
	public static Map<String,Object> entity2Map(Object entity){
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			Field[] fields = entity.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				Object object = field.get(entity);
				if(object==null){
					continue;
				}
				result.put(field.getName(),object);
			}
			
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Map转实体类
	 * @param map 需要初始化的数据，key字段必须与实体类的成员名字一样，否则赋值为空
	 * @param entity  需要转化成的实体类
	 * @return
	 */
	public static <T> T mapToEntity(Map<String, Object> map, Class<T> entity) {
		T t = null;
		try {
			t = entity.newInstance();
			for (Field field : entity.getDeclaredFields()) {
				if (map.containsKey(field.getName())) {
					boolean flag = field.isAccessible();
					field.setAccessible(true);
					Object object = map.get(field.getName());
					if (object != null && field.getType().isAssignableFrom(object.getClass())) {
						field.set(t, object);
					}
					field.setAccessible(flag);
				}
			}
			return t;
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

	public static Class<?> getEntityClass(Type genericSuperClass){
		ParameterizedType parametrizedType = null;
		while (parametrizedType == null) {
			if ((genericSuperClass instanceof ParameterizedType)) {
				parametrizedType = (ParameterizedType) genericSuperClass;
			} else {
				genericSuperClass = ((Class<?>) genericSuperClass).getGenericSuperclass();
			}
		}

		return (Class<?>)parametrizedType.getActualTypeArguments()[0];
	}

	public static String getTableName(Type genericSuperClass){
//	    ParameterizedType parametrizedType = null;
//	    while (parametrizedType == null) {
//	        if ((genericSuperClass instanceof ParameterizedType)) {
//	            parametrizedType = (ParameterizedType) genericSuperClass;
//	        } else {
//	            genericSuperClass = ((Class<?>) genericSuperClass).getGenericSuperclass();
//	        }
//	    }
	    Class<?> entityClass = getEntityClass(genericSuperClass);
	    for(Annotation annotation : entityClass.getAnnotations()){
	    	if(annotation.annotationType().getName().equals("com.attractpay.admin.common.annotation.TableName")){
	    		return ((TableName)annotation).value();
	    	}
	    }
		return null;
	}


	//定义静态方法，并根据泛型和反射，实现转换
	//注意：要求数据库的列名必须和JAVA实体类的属性名、类型完全一致
	public static <T> List<T> resultSetToList(ResultSet rs, Class<T> cls) {
		//定义接收的集合
		List<T> list = new ArrayList<T>();
		//创建一个对象，方便后续反射赋值
		Object obj=null;
		try {
			while (rs.next()) {
				//利用反射获取，执行类的实例化对象
				obj = cls.newInstance();
				//利用反射，获取对象类信息中的所有属性
				Field [] fields = cls.getDeclaredFields();

				for(Field fd:fields){
					if(fd.getName().equals("serialVersionUID")) continue;
					//屏蔽权限
					fd.setAccessible(true);
					//为对象属性赋值
					fd.set(obj,rs.getObject(fd.getName()));
				}
			}
			//返回转换后的集合
			list.add((T)obj);
		} catch (SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		//返回集合
        return list;
	}

}