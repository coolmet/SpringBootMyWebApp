package com.springboot.mywebapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import com.springboot.mywebapp.service.LanguageService;

@Controller

public class WebLinkControllerUserTh
{
	
	@Autowired
	private SessionRegistry sessionRegistry;
	
	@Autowired
	private LanguageService languageService;
	
	@RequestMapping(value=
	{"/user"})
	public ModelAndView admin()
	{
		ModelAndView mav=new ModelAndView();
		mav.addObject("deflangimagepath",languageService.getLanguageImagePathByLocaleName(LocaleContextHolder.getLocale().getLanguage()));
		mav.setViewName("th_user");
		return mav;
	}
}
