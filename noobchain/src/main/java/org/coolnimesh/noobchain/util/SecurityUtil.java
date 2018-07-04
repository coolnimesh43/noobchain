package org.coolnimesh.noobchain.util;

import org.apache.commons.codec.digest.DigestUtils;

public class SecurityUtil {

	public static String applySHA256(String input) {
		return DigestUtils.sha256Hex(input);
	}
	// public static String applySHA256(String input) {
	// try {
	//
	// MessageDigest digest = MessageDigest.getInstance("SHA-256");
	// byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
	// String hexHash = bytesToHex(hash);
	// return hexHash;
	// } catch (Exception e) {
	// throw new RuntimeException(e);
	// }
	// }
	//
	// private static String bytesToHex(byte[] hash) {
	// StringBuffer hexString = new StringBuffer();// this will contain hash as hexdecimal
	// String hex = "";
	// for (int i = 0; i < hash.length; i++) {
	// hex = Integer.toHexString(0xff & hash[i]);
	// if (hex.length() == 1) {
	// hexString.append(0);
	// }
	// hexString.append(hex);
	// }
	// return hexString.toString();
	// }
}
