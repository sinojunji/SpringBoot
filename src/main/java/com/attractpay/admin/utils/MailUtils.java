package com.attractpay.admin.utils;


import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;



/**
 * 邮件 工具类 需要Spring Mail支持所以把本工具类交给Spring管理
 * Created by Allen on 20/05/2017.
 * coder.allen@hotmail.com
 */
@Component
public class MailUtils {
	private Logger logger =  LoggerFactory.getLogger(this.getClass());
	 /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	@Autowired
	JavaMailSender mailSender;
	
	/**
	 * 发送文本邮件
	 * @param from
	 * @param to
	 * @param subject
	 * @param content
	 */
	public void sendTextEmail(String from,String subject,String content,String... to) throws Exception{
		//单开线程发邮件
		Thread mailThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					SimpleMailMessage message = new SimpleMailMessage();
//					message.setFrom(from);
					message.setTo(to);
					message.setSubject(subject);
					message.setText(content);
					
					mailSender.send(message);
					for (int i = 0; i < to.length; i++) {
						logger.info(to[i] + "      sent success");
					}
				} catch (MailException e) {
					logger.error(e.getMessage());
				}
			}
		});
		
		mailThread.start();
	}
	
	/**
	 * 发送HTML邮件
	 * @param from
	 * @param to
	 * @param subject
	 * @param content
	 */
	public void sendHTMLEmail(String from,String[] to,String subject,String content,String[] attachedFiles) throws Exception{
		//单开线程发邮件
		Thread mailThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					MimeMessage message = mailSender.createMimeMessage();
					MimeMessageHelper helper = new MimeMessageHelper(message, true);
					helper.setFrom(from);
					helper.setTo(to);
					helper.setSubject(subject);
					helper.setText(content);
					
					if(attachedFiles != null){
						for(int i = 0 ; i < attachedFiles.length ; i ++){
							FileSystemResource file = new FileSystemResource(attachedFiles[i]);
							helper.addInline(file.getFilename(), file);
						}
					}
					mailSender.send(message);
				} catch (MailException e) {
					logger.error(e.getMessage());
				} catch (MessagingException e) {
					logger.error(e.getMessage());
				}
			}
		});
		
		mailThread.start();
	}
}
