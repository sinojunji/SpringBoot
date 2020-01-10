package com.attractpay.admin.common.base;

import com.attractpay.admin.utils.EntityUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;

import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
/**
 * @description
 * @author junji
 * @date 2020/1/8 14:58
 * @return 
 */
@Intercepts(value={
//        @Signature(type= ResultHandler.class , method="query",
//                args={Statement.class,ResultHandler.class})
        @Signature(type= ResultSetHandler.class , method="handleResultSets",
        args={Statement.class})
        })
public class MybatisInterceptor implements Interceptor {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(MybatisInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();

        MetaObject metaObject = SystemMetaObject.forObject(realTarget(invocation.getTarget()));
        MappedStatement ms = (MappedStatement) metaObject.getValue("mappedStatement");
        ParameterHandler paramHandler = (ParameterHandler)metaObject.getValue("parameterHandler");
        Map<String,Object> paramsList = (Map<String,Object>) paramHandler.getParameterObject();
        Class<?> T = null;
        for (String key : paramsList.keySet()) {
            if(key.equals("classType")) T = (Class<?>)paramsList.get(key);
        }

        // 获取到当前的Statement
        Statement stmt =  (Statement) args[0];
        // 通过Statement获得当前结果集
        ResultSet resultSet = stmt.getResultSet();

        if(ms.getId().contains("BaseMapper")) {
            return EntityUtils.resultSetToList(resultSet, T);
        }
        //如果没有进行拦截处理，则执行默认逻辑
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {

        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }


    /**
     * <p>
     * 获得真正的处理对象,可能多层代理.
     * </p>
     */
    @SuppressWarnings("unchecked")
    public static <T> T realTarget(Object target) {
        if (Proxy.isProxyClass(target.getClass())) {
            MetaObject metaObject = SystemMetaObject.forObject(target);
            return realTarget(metaObject.getValue("h.statement"));
        }
        return (T) target;
    }

}
