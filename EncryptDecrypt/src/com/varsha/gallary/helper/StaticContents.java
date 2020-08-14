package com.varsha.gallary.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class StaticContents {

	private static Map<String, String> map = new HashMap<>();
	public static final String IMG_LOCATION = "image_location";
	public static final String ENC_LOCATION = "encrypt_location";
	public static final String DEC_LOCATION = "decrypt_location";
	

	static {
		Properties prop = new Properties();
		InputStream iStream = null;
		try {
			// Loading properties file from the classpath
			iStream = ClassLoader.class.getResourceAsStream("/config.properties");
			if(iStream == null){
				throw new IOException("File not found");
			}
			prop.load(iStream);

			prop.forEach((k, v) -> {
				System.out.println("key#"+k+"  :  value##"+v);
				map.put(""+k ,""+v);
			});

		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(iStream != null){
					iStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getValue(String key) {
		return map.get(key);
	}


}
