package com.springboot.mywebapp.web;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
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
import com.springboot.mywebapp.service.LanguageService;

@Controller
public class WebLinkControllerAnnoymusTh
{
	@Autowired
	private SessionRegistry sessionRegistry;
	
	@Autowired
	private LanguageService languageService;
	
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
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public ModelAndView registerTh(@RequestParam Map<String,String> messages,Model model)
	{
		model.asMap().clear();
		ModelAndView mav=new ModelAndView();
		mav.getModel().clear();
		mav.addObject("deflangimagepath",languageService.getLanguageImagePathByLocaleName(LocaleContextHolder.getLocale().getLanguage()));
		mav.addObject("user",new com.springboot.mywebapp.model.User());
		mav.addObject("message",""+messages.get("message"));
		mav.setViewName("th_register");
		return mav;
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public RedirectView saveTh(HttpSession session,@ModelAttribute(value="user") com.springboot.mywebapp.model.User user)
	{
		System.out.println(user.getUserId());
		System.out.println(user.getName());
		System.out.println(user.getEmail());
		String message="";
		System.out.println(user.getPassword()+":::"+user.getConfirmationtoken());
		
		if(!user.getPassword().equals(user.getConfirmationtoken()))
		{
			message="Şifreleri farklı girdiniz ...";
		}
		//
		RedirectView rv=new RedirectView();
		//rv.setContextRelative(true);
		//rv.setExposeModelAttributes(true);
		rv.addStaticAttribute("message",message);
		rv.setUrl("/register");
		return rv;
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
