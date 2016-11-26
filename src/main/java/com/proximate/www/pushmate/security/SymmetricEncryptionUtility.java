package com.proximate.www.pushmate.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class SymmetricEncryptionUtility {

	private final static String __key = "c2586dd9cb456aee65edb59dcc864a3a";
	private final static String __iv = "6ea195556467faef";

	public final static Key generateKey() throws NoSuchAlgorithmException {
		Key key = new SecretKeySpec(__key.getBytes(), "AES");
		return key;
	}

	public static final String encrypt(final String message)
			throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, UnsupportedEncodingException, InvalidAlgorithmParameterException {

		Key key = SymmetricEncryptionUtility.generateKey();
		IvParameterSpec iv = new IvParameterSpec(__iv.getBytes());

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);

		byte[] stringBytes = message.getBytes("UTF8");

		byte[] raw = cipher.doFinal(stringBytes);

		return Base64.encodeBase64String(raw);
	}

	public static final String decrypt(final String encrypted)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, IOException, InvalidAlgorithmParameterException {

		Key key = SymmetricEncryptionUtility.generateKey();
		IvParameterSpec iv = new IvParameterSpec(__iv.getBytes());

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, iv);

		byte[] raw = Base64.decodeBase64(encrypted);

		byte[] stringBytes = cipher.doFinal(raw);

		String clearText = new String(stringBytes, "UTF8");
		return clearText;
	}

	public static void main(String[] args) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
			NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IOException {

		// String message = "{" +
		// "\"fecharollback\": \"2015-09-01 00:00:00\"," +
		// "\"documento\": \"1234567890\"" +
		// "}";

		// message = encrypt(message);
		// System.out.println(message);

		String message = "6ps7PVv1y0SpFs/yqWY0y0+tRGrz+kODsu6o2eYR5N/sRXjQQ9ihIXCT/yEJHxiTwutSQpDSNI5hIS/bKMOTtEB1gq5KS4+qZ9YMky8p1Xop03Eu+p2W6hMPY4txbaPvmgUtvTQjNXj1CE+74jsNeQNDPEurVwNNCSiP6KCbBOEKelaVDMBxuOxhWd2KyMfAI90iJZwi/+HNGCfl08tNhE4MAQ1VvKcfVjEBQ1hxTjI=";

		message = decrypt(message);
		System.out.println(message);
	}

}
