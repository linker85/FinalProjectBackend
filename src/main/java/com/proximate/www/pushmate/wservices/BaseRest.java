package com.proximate.www.pushmate.wservices;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.proximate.www.pushmate.security.EncryptException;
import com.proximate.www.pushmate.security.SymmetricEncryptionUtility;

public class BaseRest {
	
	private final String MENSAJE_DECRYPT_ERROR = "Bad Format encrypt, maybe bad key or iv."; 
	private final String MENSAJE_ENCRYPT_ERROR = "Error on encrypt response.";
	
	protected String decryptJSON(String message) throws EncryptException {
		String decrypJsonParam = null;
		
		try{
			decrypJsonParam = SymmetricEncryptionUtility.decrypt(message);
		} catch (IllegalBlockSizeException e){
			throw new EncryptException(MENSAJE_DECRYPT_ERROR);
		} catch (InvalidKeyException e) {
			throw new EncryptException(MENSAJE_DECRYPT_ERROR);
		} catch (NoSuchAlgorithmException e) {
			throw new EncryptException(MENSAJE_DECRYPT_ERROR);
		} catch (NoSuchPaddingException e) {
			throw new EncryptException(MENSAJE_DECRYPT_ERROR);
		} catch (BadPaddingException e) {
			throw new EncryptException(MENSAJE_DECRYPT_ERROR);
		} catch (InvalidAlgorithmParameterException e) {
			throw new EncryptException(MENSAJE_DECRYPT_ERROR);
		} catch (IOException e) {
			throw new EncryptException(MENSAJE_DECRYPT_ERROR);
		}
		
		return decrypJsonParam;
		
	}
	
	protected String encryptJSON(String message) throws EncryptException {
		String encrypJsonParam = null;
		try {
			encrypJsonParam = SymmetricEncryptionUtility.encrypt(message);
		} catch (InvalidKeyException e) {
			throw new EncryptException(MENSAJE_ENCRYPT_ERROR);
		} catch (IllegalBlockSizeException e) {
			throw new EncryptException(MENSAJE_ENCRYPT_ERROR);
		} catch (BadPaddingException e) {
			throw new EncryptException(MENSAJE_ENCRYPT_ERROR);
		} catch (NoSuchAlgorithmException e) {
			throw new EncryptException(MENSAJE_ENCRYPT_ERROR);
		} catch (NoSuchPaddingException e) {
			throw new EncryptException(MENSAJE_ENCRYPT_ERROR);
		} catch (UnsupportedEncodingException e) {
			throw new EncryptException(MENSAJE_ENCRYPT_ERROR);
		} catch (InvalidAlgorithmParameterException e) {
			throw new EncryptException(MENSAJE_ENCRYPT_ERROR);
		}
		return encrypJsonParam;
	}
}
