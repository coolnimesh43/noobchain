package org.coolnimesh.noobchain.core;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

public class Wallet {

	private PrivateKey privateKey;
	private PublicKey publicKey;

	public Wallet() {
		super();
		this.generateKey();
	}

	public void generateKey() {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			ECGenParameterSpec spec = new ECGenParameterSpec("prime192v1");
			keyGen.initialize(spec, random);
			KeyPair keyPair = keyGen.generateKeyPair();

			this.privateKey = keyPair.getPrivate();
			this.publicKey = keyPair.getPublic();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

}
