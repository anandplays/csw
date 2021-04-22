package com.csw.converter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperations {
		
	public static String readFile (File file) throws IOException {
		BufferedReader fileReader = null;
		try {
			fileReader = new BufferedReader (new FileReader (file));
		} catch (FileNotFoundException e) {
			
			throw new IOException();
		}
		StringBuilder string = new StringBuilder ();
		try {
			String line = fileReader.readLine ();
			while (line != null) {
				string.append (line);
				string.append ("\n");
				line = fileReader.readLine ();
			}
		}catch (IOException e){
			
			throw new IOException();
		} finally{
			
			fileReader.close ();
		}
		return string.toString();
	}

}
