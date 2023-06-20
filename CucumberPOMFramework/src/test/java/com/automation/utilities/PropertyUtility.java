package com.automation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertyUtility {
	private FileInputStream stream = null;
	private Properties propFile = null;
	
	public Properties loadFile(String filename) {
		propFile=new Properties();
		String propertyFilePath=null;
		switch(filename) {
		case "applicationDataProperties":
			propertyFilePath=Constants.APPLICATION_PROPERTIES;
			break;
		case "studentRegistrationProperties":
			propertyFilePath=Constants.STUDENT_REGISTRATION_PROPERTIES;
			break;
		case "applicationDataPropertiesSF":
			propertyFilePath=Constants.APPLICATION_PROPERTIES_SF;
			break;
		}
		
		System.out.println(">>PropertyUtility>> "+propertyFilePath);
		
		try {
			stream=new FileInputStream(propertyFilePath);
			if(stream == null) {
				System.out.println(">>PropertyUtility>> stream is null");
			}
			propFile.load(stream);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return propFile;
		
	}

	public String getPropertyVAlue(String key) {
		String value = propFile.getProperty(key);
		return value;

	}

	public void writeDataToPropertyFile(File path, String key, String value) {

		Properties propFile = new Properties();
		propFile.setProperty(key, value);
		try {
			propFile.store(new FileOutputStream(path), "updating data");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
