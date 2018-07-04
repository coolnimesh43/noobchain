package org.coolnimesh.noobchain.core;

import java.util.Date;

import org.coolnimesh.noobchain.util.SecurityUtil;

public class Block {

	private String hash;
	private String previousHash;
	private String data;
	private Long timestamp;
	private Integer nonce = 0;

	public Block(String previousHash, String data) {
		super();
		this.previousHash = previousHash;
		this.data = data;
		this.timestamp = new Date().getTime();
		this.hash = this.calculateHash();
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public String calculateHash() {
		return SecurityUtil.applySHA256(this.previousHash + Long.toString(this.timestamp) + Integer.toString(nonce) + this.data);
	}

	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0');// create a string with difficulty * 0
		while (!hash.substring(0, difficulty).equals(target)) {
			nonce++;
			hash = calculateHash();
		}
		System.out.println("Block mined successfully. Hash is: " + hash);
	}
}
