/**
 * 
 */
package com.csw.converter;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author lenovo
 *
 */
public class XMLJSONConverterImpl implements XMLJSONConverterI {
	
    Logger log = Logger.getLogger(XMLJSONConverterImpl.class.getName());
	/* (non-Javadoc)
	 * @see com.csw.converter.XMLJSONConverterI#convertJSONtoXML(java.io.File, java.io.File)
	 */
	public void convertJSONtoXML(File json, File xml) throws IOException {
		
		String jsonString = FileOperations.readFile (json);
		if (jsonString.isEmpty() || jsonString == null) {
			throw new IOException("Empty File");
		}
		log.log(Level.INFO, "jsonString :" + jsonString);
		JsonUtility utility = new JsonUtility();
		utility.convertJSONToXML(jsonString, xml);

	}

}
