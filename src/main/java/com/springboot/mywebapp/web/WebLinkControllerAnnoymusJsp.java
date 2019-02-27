package com.springboot.mywebapp.web;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.springboot.mywebapp.service.LanguageService;

@Controller
public class WebLinkControllerAnnoymusJsp
{
	@Autowired
	private SessionRegistry sessionRegistry;
	
	@Autowired
	private LanguageService languageService;
	
	@RequestMapping(value=
	{"/J","/index/J"})
	public ModelAndView index()
	{
		ModelAndView mav=new ModelAndView();
		mav.setViewName("j_index");
		return mav;
	}
	
	@RequestMapping(value=
	{"/login/J"})
	public ModelAndView login()
	{
		ModelAndView mav=new ModelAndView();
		if((""+SecurityContextHolder.getContext().getAuthentication().getPrincipal()).equals("anonymousUser"))
		{
			mav.setViewName("j_login");
		}
		else
		{
			mav.setViewName("redirect:/default/J");
		}
		return mav;
	}
	
	@RequestMapping("/default/J")
	public String defaultAfterLogin(HttpServletRequest request)
	{
		if(request.isUserInRole("ROLE_ADMIN"))
		{
			return "redirect:/admin/J";
		}
		
		return "redirect:/J";
	}
	
}
