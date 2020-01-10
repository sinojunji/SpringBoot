package com.attractpay.admin.third.nexmo;

import java.io.IOException;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;


/**
 * 国际短信发送接口
 *
 */
public class SMSUtils {

	private static String key = "abe6cd7d";
	private static String secret = "5bb1395d8540bc8c";
	
	private static String from = "Attractpay";

	/**
	 * send massage
	 *
	 * @param dst
	 * @param msg
	 * @return
	 * @throws NexmoClientException 
	 * @throws IOException 
	 */
	
	public static boolean send(String dst, String msg) throws IOException, NexmoClientException {
		NexmoClient client = new NexmoClient.Builder().apiKey(key).apiSecret(secret).build();
		dst = dst.replaceAll("[^0-9]", "");//去除手机号里的特殊符号

		TextMessage message = new TextMessage(from, dst, msg, true);
		SmsSubmissionResponse responses = client.getSmsClient().submitMessage(message);

		for (SmsSubmissionResponseMessage response : responses.getMessages()) {
			if(response.getStatus().getMessageStatus()!=0){
				return false;
			}
		}
		return true;
	}
}
