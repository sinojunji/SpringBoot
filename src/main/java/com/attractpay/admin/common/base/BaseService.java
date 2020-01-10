package com.attractpay.admin.common.base;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.attractpay.admin.utils.EntityUtils;

import javax.annotation.Resource;

@Service
public class BaseService<T> {

    @Autowired
    BaseMapper<T> mapper;

    public List<T> findAll(){
        Type genericSuperclass = getClass().getGenericSuperclass();
        return mapper.findAll(EntityUtils.getEntityClass(genericSuperclass),EntityUtils.getTableName(genericSuperclass));
    }

    public Page<T> findAllByPage(PageInfo<T> pageable){
        PageHelper.startPage(pageable);

        return mapper.findAllByPage(EntityUtils.getTableName(getClass().getGenericSuperclass()));
    }

    public T getOne(long id){
        Type genericSuperclass = getClass().getGenericSuperclass();
        return mapper.getOne(EntityUtils.getEntityClass(genericSuperclass),EntityUtils.getTableName(genericSuperclass),id);
    }

    @Transactional
    public void save(T entity){
        mapper.save(EntityUtils.getTableName(getClass().getGenericSuperclass()),EntityUtils.entity2Map(entity));
    }

    @Transactional
    public void update(T entity){
        mapper.update(EntityUtils.getTableName(getClass().getGenericSuperclass()),EntityUtils.entity2Map(entity));
    }

    @Transactional
    public void updateWithNull(T entity){
        mapper.updateWithNull(EntityUtils.getTableName(getClass().getGenericSuperclass()),EntityUtils.entity2Map(entity));
    }

    @Transactional
    public void delete(long id){
        mapper.delete(EntityUtils.getTableName(getClass().getGenericSuperclass()),id);
    }

}