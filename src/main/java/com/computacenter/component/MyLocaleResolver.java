package com.computacenter.component;

import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String l = request.getParameter("l");
        //Locale locale = Locale.getDefault();
        Locale locale = new Locale("de", "DE"); //default German
        System.out.println("l = " + l);
        if (l != null){
            String[] split = l.split("_");
            locale = new Locale(split[0], split[1]);
            System.out.println(locale);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
