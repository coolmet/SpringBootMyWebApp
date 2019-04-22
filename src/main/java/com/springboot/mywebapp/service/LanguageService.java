package com.springboot.mywebapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import org.springframework.context.i18n.LocaleContextHolder;
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
		languages.add(new Language(0,"/custom/images/locale-TR_2.png","Turkish","tr","tr_TR",new Locale("tr_TR")));
		languages.add(new Language(0,"/custom/images/locale-EN_2.png","English","en","en_EN",new Locale("en_EN")));
		languages.add(new Language(0,"/custom/images/locale-DE_2.png","Deutsch","de","de_DE",new Locale("de_DE")));
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
	
	public String getCurrentLocaleDisplayName() // Türkçe (Türkiye)
	{
		return LocaleContextHolder.getLocale().getDisplayName();
	}
	
	public String getCurrentCountry() // TR
	{
		return LocaleContextHolder.getLocale().getCountry();
	}
	
	public String getCurrentDisplayCountry() // Türkiye
	{
		return LocaleContextHolder.getLocale().getDisplayCountry();
	}
	
	public String getCurrentDisplayLanguage() // Türkçe
	{
		return LocaleContextHolder.getLocale().getDisplayLanguage();
	}
	
	public String getCurrentISO3Country() // TUR
	{
		return LocaleContextHolder.getLocale().getISO3Country();
	}
	
	public String getCurrentISO3Language() // tur
	{
		return LocaleContextHolder.getLocale().getISO3Language();
	}
	
	public String getCurrentLanguage() // tr
	{
		return LocaleContextHolder.getLocale().getLanguage();
	}
	
	public String getCurrentLanguageTag() // tr-TR
	{
		return LocaleContextHolder.getLocale().toLanguageTag();
	}
	
}
