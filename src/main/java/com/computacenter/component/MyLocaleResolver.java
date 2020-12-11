package com.computacenter.component;

import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String l = request.getParameter("l");

        //user didn't specify language
        if (l == null){
            //no language stored in session, so we use default German
            if (request.getSession().getAttribute("language") == null){
                return new Locale("de", "DE");

              //there's language stored in session
            } else {
                String s = (String) request.getSession().getAttribute("language");
                String[] split = s.split("_");
                return new Locale(split[0], split[1]);
            }
            //if user specifies language in param l, we store it in session
        } else {
            String[] split = l.split("_");
            request.getSession().setAttribute("language", l);
            return new Locale(split[0], split[1]);
        }
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
