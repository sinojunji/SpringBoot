package com.attractpay.admin.test;


import com.attractpay.admin.Application;
import com.attractpay.admin.entity.Merchant;
import com.attractpay.admin.service.MerchantService;
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
	MerchantService merchantService;

	@Autowired
	private RedisTemplate<String, Serializable> redisTemplate;

	@Test
	public void findAll() {
		PageInfo<Merchant> pageable = new PageInfo<Merchant>();
		pageable.setPageSize(5);
		Merchant result = merchantService.getOne(1);
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
