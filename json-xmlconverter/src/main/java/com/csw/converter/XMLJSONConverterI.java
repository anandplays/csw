package com.csw.converter;

import java.io.File;
import java.io.IOException;

public interface XMLJSONConverterI {
	
	 public void convertJSONtoXML(File json, File xml) throws IOException;

}
