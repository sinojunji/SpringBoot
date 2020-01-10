package com.attractpay.admin.test;


import com.attractpay.admin.Application;
import com.attractpay.admin.common.base.BaseService;
import com.attractpay.admin.entity.Merchant;
import com.attractpay.admin.entity.Store;
import com.attractpay.admin.entity.User;
import com.attractpay.admin.service.MerchantService;
import com.attractpay.admin.service.StoreService;
import com.attractpay.admin.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Serializable;
import java.util.List;

import static org.junit.Assert.assertTrue;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseServiceTest {
	
	@Autowired
	StoreService storeService;

	@Autowired
	private RedisTemplate<String, Serializable> redisTemplate;

	@Test
	public void findAll() {
		PageInfo<Store> pageable = new PageInfo<Store>();
		pageable.setPageSize(5);
		Store result = storeService.getOne(1);
		assertTrue(result!=null);
	}

//	@Test
//	public void redisTest(){
//
//		Merchant merchant = new Merchant();
//		merchant.setId(11l);
//		merchant.setShow_name("Test Merchant");
//		merchant.setRegistration_name("Test Merchnat Group Limited");
//
//		redisTemplate.opsForValue().set("user:1", merchant);
//		Merchant m = (Merchant) redisTemplate.opsForValue().get("user:1");
//		System.out.println(m.getShow_name()+":  "+m.getRegistration_name());
//	}


}
