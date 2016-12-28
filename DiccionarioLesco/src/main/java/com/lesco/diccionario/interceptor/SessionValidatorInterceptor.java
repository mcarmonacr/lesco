/**
 * 
 */
package com.lesco.diccionario.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lesco.diccionario.dao.UserDAO;
import com.lesco.diccionario.model.ProfileDetail;
import com.lesco.diccionario.model.UserProfile;

/**
 * @author m.carmona.dinarte
 *
 */
public class SessionValidatorInterceptor extends HandlerInterceptorAdapter {

	//Log4J class logger instance
	private static final Logger logger = Logger.getLogger(SessionValidatorInterceptor.class);
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		long startTime = System.currentTimeMillis();
		logger.info("Request URL::" + request.getRequestURL().toString()
				+ ":: Start Time=" + System.currentTimeMillis());
		request.setAttribute("startTime", startTime);
		
		logger.info("SessionValidatorInterceptor:preHandle- JsessionID" + request.getSession().getId());
				
		//if returned false, we need to make sure 'response' is sent
		
		//TODO Work this logic
		HttpSession session = request.getSession(false);
		if (session == null) {
		    // Not created yet. Now do so yourself.
		    //session = request.getSession();
			
			//This works
			//response.sendRedirect("/DiccionarioLesco/login");
		} else {
		    // Already created.
		}
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("Request URL::" + request.getRequestURL().toString()
				+ " Sent to Handler :: Current Time=" + System.currentTimeMillis());
		
		
		if (request.getSession(false).getAttribute("userEmail") != null){
			Map model = modelAndView.getModel();
			model.put("isSessionValid", "true");
			
			ProfileDetail profileDetailQuery = userDAO.findByEmailAddress(request.getSession(false).getAttribute("userEmail").toString());
			
			//Checks the user role
			if(profileDetailQuery != null) {
					
				ProfileDetail ProfileDetailReference = userDAO.findById(profileDetailQuery.getProfileDetailId());
				
				UserProfile userProfile = ProfileDetailReference.getUserProfile();
				
				model.put("userName", userProfile.getUserName());
			}
		}
		
		//we can add attributes in the modelAndView and use that in the view page
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		logger.info("Request URL::" + request.getRequestURL().toString()
				+ ":: End Time=" + System.currentTimeMillis());
		logger.info("Request URL::" + request.getRequestURL().toString()
				+ ":: Time Taken=" + (System.currentTimeMillis() - startTime));

	}
	
}
