package com.lesco.diccionario.interceptor;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.support.MultipartFilter;

/**
 * Sets the correct char encoding to use in the application
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
public class MyMultipartFilterInterceptor extends MultipartFilter {
	
	//Log4J class logger instance
	private static final Logger logger = Logger.getLogger(MyMultipartFilterInterceptor.class);

	/**
	 * Sets the correct char encoding to use in the application
	 */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
       HttpServletResponse response, FilterChain filterChain)
       throws ServletException, IOException {
    	
    	logger.debug("MyMultipartFilter - doFilterInternal() - Start");

       request.setCharacterEncoding("ISO-8859-1");
       request.getParameterNames();

       super.doFilterInternal(request, response, filterChain);
       
       logger.debug("MyMultipartFilter - doFilterInternal() - End");
    }
}