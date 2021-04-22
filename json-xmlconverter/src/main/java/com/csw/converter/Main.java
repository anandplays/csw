package com.csw.converter;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
	
	static Logger log = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		
		if (args.length != 2)
		{
			log.log(Level.SEVERE, "Invalid Arguments");
		}
		File inputFile = new File(args[0]);
		File outputFile = new File(args[1]);
		XMLJSONConverterI converter = ConverterFactory.createXMLJSONConverter();
		try {
			converter.convertJSONtoXML(inputFile, outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
