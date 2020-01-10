package com.attractpay.admin.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;

@WebFilter(urlPatterns = "/*")
@Order(Integer.MAX_VALUE-1)
public class AtpFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String requestURI = req.getRequestURI();
		HttpServletResponse res = (HttpServletResponse) response;
		String requestURL = req.getRequestURL().toString();
		if(requestURL.equals("http://pay.attractpay.co.nz/")||requestURL.equals("http://pay.attractpay.co.nz/login")){
			res.sendRedirect("http://pay.attractpay.co.nz:8223/index");
		}else if(requestURI.contains("alipayNotification")){  
			request.setCharacterEncoding("GBK"); 
			response.setCharacterEncoding("GBK");
			chain.doFilter(request, response);
        }else{
        	chain.doFilter(request, response);
        }
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
