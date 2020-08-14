package com.varsha.gallary.helper;


import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

public class AESEncryption {

	private static final String characterEncoding       = "UTF-8";
	private static final String cipherTransformation    = "AES";
	private static final String aesEncryptionAlgorithem = "AES";
	private static final String AES_KEY = "R7QVHOMDUWU2AFFPNX3JPO7QKDZ25KWT";

	public static byte[] decrypt(byte[] encrypted) {
		try {
			
			Security.addProvider(new BouncyCastleProvider());
			SecretKeySpec skeySpec = new SecretKeySpec(AES_KEY.getBytes(characterEncoding), cipherTransformation);
			Cipher cipher = Cipher.getInstance(aesEncryptionAlgorithem);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] original = cipher.doFinal((encrypted));
			return (original);
		} catch (Exception ex) {
			System.err.println("Exception while decrypting msg##"+ex);
		}
		return null;
	}
	public static byte[] encryption(byte[] msg) {
		try {
			Security.addProvider(new BouncyCastleProvider());
			SecretKeySpec skeySpec = new SecretKeySpec(AES_KEY.getBytes(characterEncoding), "AES");
			Cipher cipher = Cipher.getInstance(aesEncryptionAlgorithem);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] original = cipher.doFinal(msg);
			return original;
		} catch (Exception ex) {
			System.err.println("Exception while encrypting msg##"+ex);
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		byte[] bytedata = Files.readAllBytes(Paths.get("D:/gallery-icon.png"));
		
		byte[] encrypted = encryption(bytedata);
		
		Files.write(Paths.get("D:/gallery-icon.png.enc"), encrypted);
		
		byte[] encbytedata = Files.readAllBytes(Paths.get("D:/gallery-icon.png.enc"));
		
		byte[] decrypted = decrypt(encbytedata);
		
		Files.write(Paths.get("D:/gallery-icon_1.png"), decrypted);
		
		
		
		
	}
	
}


