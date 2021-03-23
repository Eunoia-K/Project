package com.tasks.demo.seed;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Seed {

	private static final byte[] pbszUserKey = "SeedTestKey!@#12".getBytes();	//사용자가 지정하는 입력 키(16 bytes)
	private static final byte[] pbszIV = "ClearTestKey*&^5".getBytes(); 		//초기화 벡터 (16 bytes)
	
	public static String encrypt(String rawMessage) { 
		
		Encoder encoder = Base64.getEncoder();
		
		byte[] message = rawMessage.getBytes(StandardCharsets.UTF_8); 
		byte[] encryptedMessage = KISA_SEED_CBC.SEED_CBC_Encrypt(pbszUserKey, pbszIV, message, 0, message.length); 
		
		return new String(encoder.encode(encryptedMessage)); 
	} 
	
	
	public static String decrypt(String encryptedMessage) { 
		
		Decoder decoder = Base64.getDecoder();
		
		byte[] message = decoder.decode(encryptedMessage);
		byte[] decryptedMessage = KISA_SEED_CBC.SEED_CBC_Decrypt(pbszUserKey, pbszIV, message, 0, message.length); 
		
		return new String(decryptedMessage, StandardCharsets.UTF_8);
	}

	
}
