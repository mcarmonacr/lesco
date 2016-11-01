/**
 * 
 */
package com.lesco.diccionario.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * 
 * 
 * @author m.carmona.dinarte
 *
 */
public class CustomerDateAndTimeDeserialize extends JsonDeserializer<Date> {
	//private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	@Override
	public Date deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) throws IOException, JsonProcessingException {
		
		String str = paramJsonParser.getText().trim();
		
		//Input parameter will be like 10/24/2000
		
		//String spplitedDate[]= str.split("/");
		
		//String finalDate
		
		try {
	        return dateFormat.parse(str);
	    } catch (ParseException e) {

	    }
	    return paramDeserializationContext.parseDate(str);
		
	}
}
