package com.springboot.mywebapp.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.springboot.mywebapp.service.AuthService;
import com.springboot.mywebapp.service.LanguageService;
import com.springboot.mywebapp.service.RecaptchaService;
import com.springboot.mywebapp.service.UtilService;

@Controller
public class WebLinkControllerAnnoymusTh
{
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private UtilService utilService;
	
	@Autowired
	RecaptchaService captchaService;
	
	@RequestMapping(value=
	{"/","/index"})
	public ModelAndView indexTh()
	{
		ModelAndView mav=new ModelAndView();
		mav.addObject("deflangimagepath",languageService.getLanguageImagePathByLocaleName(LocaleContextHolder.getLocale().getLanguage()));
		mav.setViewName("th_index");
		return mav;
	}
	
	@RequestMapping(value=
	{"/login"})
	public ModelAndView loginTh()
	{
		ModelAndView mav=new ModelAndView();
		mav.addObject("deflangimagepath",languageService.getLanguageImagePathByLocaleName(LocaleContextHolder.getLocale().getLanguage()));
		if((""+SecurityContextHolder.getContext().getAuthentication().getPrincipal()).equals("anonymousUser"))
		{
			mav.setViewName("th_login");
		}
		else
		{
			mav.setViewName("redirect:/default");
		}
		return mav;
	}
	
	@RequestMapping(value=
	{"/logoutpage"})
	public ModelAndView logoutPage()
	{
		ModelAndView mav=new ModelAndView();
		if((""+SecurityContextHolder.getContext().getAuthentication().getPrincipal()).equals("anonymousUser"))
		{
			mav.setViewName("redirect:/default");
		}
		else
		{
			mav.setViewName("th_logoutPage");
		}
		return mav;
	}
	
	@RequestMapping(value="/myweb/register",method=RequestMethod.GET) // register page
	public ModelAndView registerTh(HttpSession session)
	{
		ModelAndView mav=new ModelAndView();
		mav.addObject("deflangimagepath",languageService.getLanguageImagePathByLocaleName(LocaleContextHolder.getLocale().getLanguage()));
		mav.addObject("user",session.getAttribute("registeruser"+session.getId())==null?new com.springboot.mywebapp.model.User():session.getAttribute("registeruser"+session.getId()));
		mav.addObject("message",session.getAttribute("registermessage"+session.getId()));
		mav.addObject("status",session.getAttribute("registerstatus"+session.getId()));
		mav.setViewName("th_register");
		session.removeAttribute("registermessage"+session.getId());
		session.removeAttribute("registerstatus"+session.getId());
		session.removeAttribute("registeruser"+session.getId());
		return mav;
	}
	
	@RequestMapping(value="/myweb/registersave",method=RequestMethod.POST) // register page save
	public String registerSaveTh(HttpSession session,@ModelAttribute(value="user") com.springboot.mywebapp.model.User user)
	{
		Map<String,String> checkUser=utilService.registerUser(user);
		session.setAttribute("registermessage"+session.getId(),checkUser.get("registermessage"));
		session.setAttribute("registerstatus"+session.getId(),checkUser.get("registerstatus"));
		session.setAttribute("registeruser"+session.getId(),user);
		return "redirect:/myweb/register";
	}
	
	@RequestMapping(value="/myweb/activateacoount",method=RequestMethod.POST)
	public ModelAndView registerConfirmTh(@RequestParam(name="g-recaptcha-response") String recaptchaResponse,
	                                      @RequestParam(value="token",required=false) String token,
	                                      HttpServletRequest request)
	{
		ModelAndView mav=new ModelAndView();
		String captchaVerifyMessage=captchaService.verifyRecaptcha(request.getRemoteAddr(),recaptchaResponse);
		if(!captchaVerifyMessage.equals(""))
		{
			mav.addObject("recaptchamessage",captchaVerifyMessage);
			mav.addObject("token",token);
			mav.setViewName("redirect:/myweb/confirm-account");
		}
		else
		{
			Map<String,Object> activateUser=utilService.activateUser(token);
			if(activateUser.get("activatestatus").equals("ERROR"))
			{
				mav.addObject("recaptchamessage",activateUser.get("activatemessage"));
				mav.addObject("token",token);
				mav.setViewName("redirect:/myweb/confirm-account");
			}
			else
			{
				utilService.authenticateUser(request,token);
				mav.setViewName("redirect:/");
			}
		}
		return mav;
	}
	
	@RequestMapping(value="/myweb/confirm-account") // mailden gelen
	public ModelAndView registerConfirmAccountTh(HttpSession session,@RequestParam(value="token",required=false) String token,
	                                             @RequestParam(value="recaptchamessage",required=false) String recaptchamessage)
	{
		ModelAndView mav=new ModelAndView();
		if(token!=null)
		{
			session.setAttribute("confirmaccounttoken"+session.getId(),token);
			session.setAttribute("confirmaccountmessage"+session.getId(),recaptchamessage);
			mav.setViewName("redirect:/myweb/confirm-account");
		}
		else
		{
			mav.addObject("deflangimagepath",languageService.getLanguageImagePathByLocaleName(LocaleContextHolder.getLocale().getLanguage()));
			mav.addObject("token",session.getAttribute("confirmaccounttoken"+session.getId()));
			mav.addObject("recaptchamessage",session.getAttribute("confirmaccountmessage"+session.getId()));
			mav.setViewName("th_confirmaccount");
			session.removeAttribute("confirmaccounttoken"+session.getId());
			session.removeAttribute("confirmaccountmessage"+session.getId());
		}
		return mav;
	}
	
	@RequestMapping("/default")
	public String defaultAfterLoginTh(HttpServletRequest request)
	{
		if(request.isUserInRole("ROLE_ADMIN"))
		{
			return "redirect:/admin/";
		}
		else if(request.isUserInRole("ROLE_USER"))
		{
			return "redirect:/user/";
		}
		return "redirect:/";
	}
	
}
