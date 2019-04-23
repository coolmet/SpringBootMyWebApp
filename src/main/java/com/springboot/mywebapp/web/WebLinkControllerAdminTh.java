package com.springboot.mywebapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.springboot.mywebapp.service.LanguageService;

@Controller
public class WebLinkControllerAdminTh
{
	
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
	{"/admin/userstableedit"})
	public ModelAndView adminTestUserTable2()
	{
		ModelAndView mav=new ModelAndView();
		mav.addObject("deflangimagepath",languageService.getLanguageImagePathByLocaleName(LocaleContextHolder.getLocale().getLanguage()));
		mav.setViewName("th_userstableedit");
		return mav;
	}
	
}
