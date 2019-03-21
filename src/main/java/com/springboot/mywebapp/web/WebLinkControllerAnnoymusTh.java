package com.springboot.mywebapp.web;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import com.springboot.mywebapp.service.UtilService;
import com.springboot.mywebapp.service.LanguageService;
import com.springboot.mywebapp.service.RecaptchaService;

@Controller
public class WebLinkControllerAnnoymusTh
{
	@Autowired
	private SessionRegistry sessionRegistry;
	
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
	
	@RequestMapping(value="/myweb/register",method=RequestMethod.GET)
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
	
	@RequestMapping(value="/myweb/registersave",method=RequestMethod.POST)
	public String registerSaveTh(HttpSession session,@ModelAttribute(value="user") com.springboot.mywebapp.model.User user)
	{
		Map<String,String> checkUser=utilService.registerUser(user);
		session.setAttribute("registermessage"+session.getId(),checkUser.get("registermessage"));
		session.setAttribute("registerstatus"+session.getId(),checkUser.get("registerstatus"));
		session.setAttribute("registeruser"+session.getId(),user);
		return "redirect:/myweb/register";
	}
	
	@RequestMapping(value="/myweb/registerconfirm",method=RequestMethod.POST)
	public ResponseEntity<?> registerConfirmTh(@RequestParam(name="g-recaptcha-response") String recaptchaResponse,HttpServletRequest request)
	{
		String ip=request.getRemoteAddr();
		String captchaVerifyMessage=captchaService.verifyRecaptcha(ip,recaptchaResponse);
		if(!captchaVerifyMessage.equals(""))
		{
			Map<String,Object> response=new HashMap<>();
			response.put("message",captchaVerifyMessage);
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value="/myweb/confirm-account")
	public ModelAndView registerConfirmAccountTh(HttpSession session,@RequestParam(value="token",required=false) String token)
	{
		ModelAndView mav=new ModelAndView();
		if(token!=null)
		{
			session.setAttribute("confirmaccounttoken"+session.getId(),token);
			mav.setViewName("redirect:/myweb/confirm-account");
		}
		else
		{
			mav.addObject("token",session.getAttribute("confirmaccounttoken"+session.getId()));
			mav.setViewName("th_confirmaccount");
			session.removeAttribute("confirmaccounttoken"+session.getId());
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
