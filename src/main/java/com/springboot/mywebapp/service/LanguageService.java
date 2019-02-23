package com.springboot.mywebapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import com.springboot.mywebapp.model.Language;

@Service
public class LanguageService
{
	private List<Language> languages;
	
	@PostConstruct
	public void init()
	{
		System.out.println("------------------------------");
		languages=new ArrayList<>();
		languages.add(new Language(0,"/custom/images/locale-TR_2.png","Turkish","tr",new Locale("tr_TR")));
		languages.add(new Language(0,"/custom/images/locale-EN_2.png","English","en",new Locale("en_EN")));
		languages.add(new Language(0,"/custom/images/locale-DE_2.png","Deutsch","de",new Locale("de_DE")));
	}
	
	public String getLanguageImagePathByLocale(Locale locale)
	{
		for(Language lang:languages)
		{
			if(lang.getLocale().equals(locale))
			{
				return lang.getImagePath();
			}
		}
		return "";
	}
	
	public String getLanguageImagePathByLocaleName(String localeName)
	{
		for(Language lang:languages)
		{
			if(lang.getLocaleName().equals(localeName))
			{
				return lang.getImagePath();
			}
		}
		return "";
	}
	
	public List<Language> getLanguages()
	{
		return languages;
	}
	
	public void setLanguages(List<Language> languages)
	{
		this.languages=languages;
	}
}
