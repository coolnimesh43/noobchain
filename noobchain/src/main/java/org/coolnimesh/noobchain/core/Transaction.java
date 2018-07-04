package org.coolnimesh.noobchain.core;

import java.math.BigDecimal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import org.coolnimesh.noobchain.util.SecurityUtil;

public class Transaction {

	private String transactionId; // hash of the transaction
	private PublicKey sender; // sender's public key
	private PublicKey receiver; // receiver's public key
	private BigDecimal amount; // the amount to be transferred
	private byte[] signature; // the signature

	private List<TransactionInput> inputs = new ArrayList<TransactionInput>();
	private List<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

	private static int sequence = 0; // rough count of how many transactions have been created

	public Transaction(PublicKey sender, PublicKey receiver, BigDecimal amount, List<TransactionInput> inputs) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
		this.inputs = inputs;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public PublicKey getSender() {
		return sender;
	}

	public void setSender(PublicKey sender) {
		this.sender = sender;
	}

	public PublicKey getReceiver() {
		return receiver;
	}

	public void setReceiver(PublicKey receiver) {
		this.receiver = receiver;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public byte[] getSignature() {
		return signature;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

	public List<TransactionInput> getInputs() {
		return inputs;
	}

	public void setInputs(List<TransactionInput> inputs) {
		this.inputs = inputs;
	}

	public List<TransactionOutput> getOutputs() {
		return outputs;
	}

	public void setOutputs(List<TransactionOutput> outputs) {
		this.outputs = outputs;
	}

	private String calculateHash() {
		sequence++;
		return SecurityUtil.applySHA256(
		        SecurityUtil.getStringFromKey(sender) + SecurityUtil.getStringFromKey(receiver) + amount.toString() + sequence);
	}

	private String getData() {
		return SecurityUtil.getStringFromKey(sender) + SecurityUtil.getStringFromKey(receiver) + amount.toString();
	}

	/**
	 * Signs the transaction so that it can't be tampered with later on.
	 * 
	 * @param key
	 *            {@link PrivateKey} The private key used to sign
	 */
	public void generateSignature(PrivateKey key) {
		signature = SecurityUtil.applyECDSASig(key, getData());
	}

	/**
	 * Verify whether or not the transaction data has been tampered with.
	 * 
	 * @return <code>true</code> if data has been tampered, else <code>false</code>
	 * 
	 */
	public Boolean verifySignature() {
		return SecurityUtil.verifyECDSASig(sender, getData(), signature);
	}
}
