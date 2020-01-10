package com.attractpay.admin.service;

import com.attractpay.admin.common.base.BaseService;
import com.attractpay.admin.entity.Merchant;

import com.attractpay.admin.mapper.MerchantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantService extends BaseService<Merchant> {

    @Autowired
    MerchantMapper merchantMapper;

    public Merchant findMerchantById(String id){
        return merchantMapper.findMerchantById(id);
    }

}

