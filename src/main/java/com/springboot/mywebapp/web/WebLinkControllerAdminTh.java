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
public class WebLinkControllerAdminTh
{
	@Autowired
	private SessionRegistry sessionRegistry;
	
	@Autowired
	private LanguageService languageService;
	
	@RequestMapping("/admin/welcome")
	@ResponseBody
	public String adminWelcome()
	{
		return "Welcome to SprinBootMyWebApp!";
	}
	
	@RequestMapping(value=
	{"/admin"})
	public ModelAndView admin()
	{
		ModelAndView mav=new ModelAndView();
		mav.addObject("deflangimagepath",languageService.getLanguageImagePathByLocaleName(LocaleContextHolder.getLocale().getLanguage()));
		mav.addObject("languages",languageService.getLanguages());
		mav.setViewName("th_admin");
		return mav;
	}
	
	@RequestMapping(value=
	{"/admin/adminloggedinusers"})
	public ModelAndView adminUsers()
	{
		List<String> usersString=sessionRegistry.getAllPrincipals().stream()
		                                        .filter(u->!sessionRegistry.getAllSessions(u,false).isEmpty())
		                                        .map(Object::toString)
		                                        .collect(Collectors.toList());
		List<UserDetails> users=sessionRegistry.getAllPrincipals().stream()
		                                       .filter(u->!sessionRegistry.getAllSessions(u,false).isEmpty())
		                                       .map(u->(UserDetails)u)
		                                       .collect(Collectors.toList());
		UserDetails userDetail=(UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//
		ModelAndView mav=new ModelAndView();
		mav.addObject("usersString",usersString);
		mav.addObject("users",users);
		mav.addObject("userDetail",userDetail);
		mav.addObject("deflangimagepath",languageService.getLanguageImagePathByLocaleName(LocaleContextHolder.getLocale().getLanguage()));
		mav.addObject("languages",languageService.getLanguages());
		mav.setViewName("th_adminloggedinusers");
		return mav;
	}
	
	@RequestMapping(value=
	{"/admin/testlogin"})
	public ModelAndView adminTestLogin()
	{
		ModelAndView mav=new ModelAndView();
		mav.setViewName("th_testlogin");
		return mav;
	}
	
	@RequestMapping(value=
	{"/admin/testloginbasic"})
	public ModelAndView adminTestLoginBasic()
	{
		ModelAndView mav=new ModelAndView();
		mav.setViewName("th_testloginbasic");
		return mav;
	}
	
	@RequestMapping(value=
	{"/admin/testparticles"})
	public ModelAndView adminParticle()
	{
		ModelAndView mav=new ModelAndView();
		mav.setViewName("th_testparticles");
		return mav;
	}
	
	@RequestMapping(value=
	{"/admin/testloginparticles"})
	public ModelAndView adminTestLoginParticles()
	{
		ModelAndView mav=new ModelAndView();
		mav.addObject("deflangimagepath",languageService.getLanguageImagePathByLocaleName(LocaleContextHolder.getLocale().getLanguage()));
		mav.addObject("languages",languageService.getLanguages());
		mav.setViewName("th_testloginparticles");
		return mav;
	}
	
	@RequestMapping(value=
	{"/admin/testloginregister"})
	public ModelAndView adminTestRegister()
	{
		ModelAndView mav=new ModelAndView();
		mav.addObject("deflangimagepath",languageService.getLanguageImagePathByLocaleName(LocaleContextHolder.getLocale().getLanguage()));
		mav.addObject("languages",languageService.getLanguages());
		mav.setViewName("th_testloginregister");
		return mav;
	}
	
	@RequestMapping(value=
	{"/admin/testlocale"})
	public ModelAndView adminTestLocale()
	{
		ModelAndView mav=new ModelAndView();
		mav.addObject("deflangimagepath",languageService.getLanguageImagePathByLocaleName(LocaleContextHolder.getLocale().getLanguage()));
		mav.addObject("languages",languageService.getLanguages());
		mav.setViewName("th_testlocale");
		return mav;
	}
	
	@RequestMapping(value=
	{"/admin/testfragmentslayouts"})
	public ModelAndView adminTestFragmentsLocales()
	{
		ModelAndView mav=new ModelAndView();
		mav.setViewName("th_testfragmentslayouts");
		return mav;
	}
}
