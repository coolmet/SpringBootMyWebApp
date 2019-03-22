package com.springboot.mywebapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RecaptchaService
{
	@Value("${google.recaptcha.secret}")
	String recaptchaSecret;
	
	@Value("${google.recaptcha.sitekey}")
	String recaptchaSiteKey;
	
	@Value("${google.recaptcha.url}")
	String GOOGLE_RECAPTCHA_VERIFY_URL;
	
	@Autowired
	RestTemplateBuilder restTemplateBuilder;
	
	@Autowired
	private MessageSource messageSource;
	
	public String verifyRecaptcha(String ip,String recaptchaResponse)
	{
		Map<String,String> body=new HashMap<>();
		body.put("secret",recaptchaSecret);
		body.put("response",recaptchaResponse);
		body.put("remoteip",ip);
		ResponseEntity<Map> recaptchaResponseEntity=restTemplateBuilder.build().postForEntity(GOOGLE_RECAPTCHA_VERIFY_URL+"?secret={secret}&response={response}&remoteip={remoteip}",body,Map.class,body);
		Map<String,Object> responseBody=recaptchaResponseEntity.getBody();
		boolean recaptchaSucess=(Boolean)responseBody.get("success");
		if(!recaptchaSucess)
		{
			List<String> errorCodes=(List)responseBody.get("error-codes");
			// String errorMessage=errorCodes.stream().map(s->RecaptchaUtil.RECAPTCHA_ERROR_CODE.get(s)).collect(Collectors.joining(", "));
			String errorMessage=errorCodes.get(0);// messageSource.getMessage("recaptcha.message."+errorCodes.get(0),new Object[0],LocaleContextHolder.getLocale());
			return errorMessage;
		}
		else
		{
			return "";
		}
	}
	
	public String getRecaptchaSecret()
	{
		return recaptchaSecret;
	}
	
	public void setRecaptchaSecret(String recaptchaSecret)
	{
		this.recaptchaSecret=recaptchaSecret;
	}
	
	public String getGOOGLE_RECAPTCHA_VERIFY_URL()
	{
		return GOOGLE_RECAPTCHA_VERIFY_URL;
	}
	
	public void setGOOGLE_RECAPTCHA_VERIFY_URL(String gOOGLE_RECAPTCHA_VERIFY_URL)
	{
		GOOGLE_RECAPTCHA_VERIFY_URL=gOOGLE_RECAPTCHA_VERIFY_URL;
	}
	
	public String getRecaptchaSiteKey()
	{
		return recaptchaSiteKey;
	}
	
	public void setRecaptchaSiteKey(String recaptchaSiteKey)
	{
		this.recaptchaSiteKey=recaptchaSiteKey;
	}
	
	static class RecaptchaUtil
	{
		public static final Map<String,String> RECAPTCHA_ERROR_CODE=new HashMap<>();
		
		static
		{
			RECAPTCHA_ERROR_CODE.put("missing-input-secret",
			                         "The secret parameter is missing");
			RECAPTCHA_ERROR_CODE.put("invalid-input-secret",
			                         "The secret parameter is invalid or malformed");
			RECAPTCHA_ERROR_CODE.put("missing-input-response",
			                         "The response parameter is missing");
			RECAPTCHA_ERROR_CODE.put("invalid-input-response",
			                         "The response parameter is invalid or malformed");
			RECAPTCHA_ERROR_CODE.put("bad-request",
			                         "The request is invalid or malformed");
		}
	}
	
}
