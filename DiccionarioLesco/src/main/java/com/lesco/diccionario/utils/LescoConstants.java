package com.lesco.diccionario.utils;

/**
 * Constants class for the LESCO Dictionary
 * Taken from: http://www.javapractices.com/topic/TopicAction.do?Id=2
 * 
 * @author mario
 *
 */
public final class LescoConstants {

	//Real values
	public static final int RANDOM_PASSWORD_LENGHT= 10;
	
	public static final String SUCCESS_CODE= "000";
	public static final String FAILURE_CODE= "999";
	public static final String SUCCESS_MESSAGE= "Success";
	public static final String FAILURE_MESSAGE= "Failure";
	
	// Private Section //

	//Examples
//	public static final boolean PASSES = true;
//	public static final int NOT_FOUND = -1;
//	public static final String NEW_LINE = System.getProperty("line.separator");
	

  /**
   The caller references the constants using <tt>Consts.EMPTY_STRING</tt>, 
   and so on. Thus, the caller should be prevented from constructing objects of 
   this class, by declaring this private constructor. 
  */
  private LescoConstants(){
    //this prevents even the native class from 
  //calling this constructor as well :
  throw new AssertionError();
  }
}