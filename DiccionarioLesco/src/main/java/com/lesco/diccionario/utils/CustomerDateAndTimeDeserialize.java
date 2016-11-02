/**
 * 
 */
package com.lesco.diccionario.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Custom class to deserialize the date from the user register form 
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
public class CustomerDateAndTimeDeserialize extends JsonDeserializer<Date> {
	
	//Log4J class logger instance
	private static final Logger logger = Logger.getLogger(CustomerDateAndTimeDeserialize.class);
	
	//Input parameter will be like: 10/24/2000. Usual format is "yyyy-MM-dd HH:mm:ss"
	private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	/**
	 * Deserialize Date override
	 * 
	 */
	@Override
	public Date deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) throws IOException, JsonProcessingException {
		
		logger.debug("CustomerDateAndTimeDeserialize - deserialize() - Start");
		
		String strDate = paramJsonParser.getText();

		try {
			if(strDate.length() != 0) {
				return dateFormat.parse(strDate.trim());
			}
	    } catch (ParseException e) {
	    	logger.error("CustomerDateAndTimeDeserialize - deserialize() - Error", e);
	    }
		
		logger.debug("CustomerDateAndTimeDeserialize - deserialize() - End");
		
	    return paramDeserializationContext.parseDate(strDate);
	}
}
