package com.varsha.gallary;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.varsha.gallary.helper.AESEncryption;
import com.varsha.gallary.helper.StaticContents;

public class ApplicationService {
	
	static String enc_location = StaticContents.getValue(StaticContents.ENC_LOCATION);
	static String dec_location = StaticContents.getValue(StaticContents.DEC_LOCATION);

	public static void encrypt(List<String> imagesList,String folderName,String location) throws IOException {

		while(Files.exists(Paths.get(enc_location,folderName))) {
			folderName = folderName+"_1";
		}
		
		Files.createDirectory(Paths.get(enc_location,folderName));
		for(String name : imagesList) {
			byte[] inputData = Files.readAllBytes(Paths.get(name));
			
			byte[] encrypted = AESEncryption.encryption(inputData);
			File file = new File(name); 
			Files.write(Paths.get(enc_location,folderName+"/"+file.getName()+".enc"), encrypted);

			Files.delete(Paths.get(name));
		}
		Files.deleteIfExists(Paths.get(location));
	}

	public static void decrypt(List<String> imagesList,String folderName,String location) throws IOException {

		while(Files.exists(Paths.get(dec_location,folderName))) {
			folderName = folderName+"_1";
		}
		
		Files.createDirectory(Paths.get(dec_location,folderName));
		for(String name : imagesList) {
			byte[] inputData = Files.readAllBytes(Paths.get(name));
			
			byte[] encrypted = AESEncryption.decrypt(inputData);
			File file = new File(name);
			String fn = file.getName().replace(".enc", "");
			Files.write(Paths.get(dec_location,folderName+"/"+fn), encrypted);

			Files.deleteIfExists(Paths.get(name));
		}
		Files.deleteIfExists(Paths.get(location));
	}

	/*public static void main(String[] args) throws Exception {


		byte[] inputData = Files.readAllBytes(Paths.get("D:/data/original/2.jpg"));

		byte[] encrypted = AESEncryption.encryption(inputData);

		Files.write(Paths.get("D:/data/encrypted/2.jpg.enc"), encrypted);

		//Files.delete(Paths.get("D:/data/original/2.jpg"));

		byte[] inputData1 = Files.readAllBytes(Paths.get("D:/data/encrypted/2.jpg.enc"));

		byte[] encrypted1 = AESEncryption.decrypt(inputData1);

		Files.write(Paths.get("D:/data/original/2_1.jpg"), encrypted1);

	}*/

	/*public static void main(String[] args) throws IOException
	  {
		Files.walk(Paths.get("C:/Users/prashant/Pictures/Camera Roll"))
        .filter(Files::isRegularFile)
        .forEach((fileName->{
        	System.out.println(fileName);
        }));
	  }*/
}
