package com.demo.bdd.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileUtil {

	public static String getFileContent(String filename) throws IOException {
		String FolderPath = System.getProperty("user.dir");
		File fileToRead = new File(FolderPath + "//TestData//" + filename);
		StringBuilder sb = new StringBuilder();
		String line = "";
		BufferedReader br = new BufferedReader(new FileReader(fileToRead));
		try {

			line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
		} finally {
			br.close();
		}

		return sb.toString();
	}
	
	public static  String createReportFile() {
		long unixTime=System.currentTimeMillis()/1000;
		String filepath=System.getProperty("user.dir")+"//Report"+unixTime+".html";
		File f= new File(filepath);
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return  filepath;
	}
	
	
	public static String replaceText(String text, String id) {
		return text.replace("$id", id);
	}
	// createfile
	// unixtimestamp

	public static String getProperty(String key) {
		InputStream input;
		String text="";
		try {
			String location=System.getProperty("user.dir");
			input = new FileInputStream(location+"//config.properties");
			Properties prop = new Properties();
			prop.load(input);
		text= prop.getProperty(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
return text;
		// get the property value and print it out
		 
	}

}
