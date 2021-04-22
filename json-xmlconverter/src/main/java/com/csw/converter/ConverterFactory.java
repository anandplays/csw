package com.csw.converter;

import com.csw.converter.XMLJSONConverterImpl;
import com.csw.converter.XMLJSONConverterI;

public class ConverterFactory {
	
	public static final XMLJSONConverterI createXMLJSONConverter() {
 
       return new XMLJSONConverterImpl ();
    }

}
