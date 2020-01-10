package com.attractpay.admin.mapper;

import com.attractpay.admin.common.base.BaseMapper;
import com.attractpay.admin.entity.Merchant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface MerchantMapper{

    @Select("select * from t_merchant where id=${id}")
    Merchant findMerchantById(@Param("id") String id);

}
