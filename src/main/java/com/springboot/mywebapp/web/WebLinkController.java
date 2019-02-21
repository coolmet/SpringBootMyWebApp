package com.springboot.mywebapp.web;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class WebLinkController
{
	@Autowired
	private SessionRegistry sessionRegistry;
	
	@RequestMapping("admin/welcome")
	@ResponseBody
	public String adminWelcome()
	{
		return "Welcome to SprinBootMyWebApp!";
	}
	
	@RequestMapping("admin")
	public ModelAndView admin()
	{
		ModelAndView mav=new ModelAndView();
		mav.setViewName("j_admin");
		return mav;
	}
	
	@RequestMapping(value=
	{"admin/users"})
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
		mav.setViewName("j_users");
		return mav;
	}
	
	@RequestMapping(value=
	{"/","index"})
	public ModelAndView index()
	{
		ModelAndView mav=new ModelAndView();
		mav.setViewName("j_index");
		return mav;
	}
	
	@RequestMapping(value=
	{"login"})
	public ModelAndView login()
	{
		ModelAndView mav=new ModelAndView();
		if((""+SecurityContextHolder.getContext().getAuthentication().getPrincipal()).equals("anonymousUser"))
		{
			mav.setViewName("j_login");
		}
		else
		{
			mav.setViewName("redirect:default");
		}
		return mav;
	}
	
	@RequestMapping("/default")
	public String defaultAfterLogin(HttpServletRequest request)
	{
		if(request.isUserInRole("ROLE_ADMIN"))
		{
			return "redirect:/admin/";
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value=
	{"admin/th/users"})
	public ModelAndView adminTHUsers()
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
		mav.setViewName("th_users");
		return mav;
	}
	
	@RequestMapping(value=
	{"admin/test"})
	public ModelAndView adminTest()
	{
		ModelAndView mav=new ModelAndView();
		mav.setViewName("j_test");
		return mav;
	}
	
	@RequestMapping(value=
	{"admin/testlogin"})
	public ModelAndView adminTestLogin()
	{
		ModelAndView mav=new ModelAndView();
		mav.setViewName("j_testlogin");
		return mav;
	}
	
	@RequestMapping(value=
	{"admin/particle"})
	public ModelAndView adminParticle()
	{
		ModelAndView mav=new ModelAndView();
		mav.setViewName("j_particle");
		return mav;
	}
	
	@RequestMapping(value=
	{"admin/register"})
	public ModelAndView adminRegister()
	{
		ModelAndView mav=new ModelAndView();
		mav.setViewName("j_register");
		return mav;
	}
}
