package org.coolnimesh.noobchain.core;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.GsonBuilder;

/**
 * Bloch chain class
 * 
 * @author Nimesh Mishra coolnimesh43@gmail.com
 *
 */
public class NoobChain {

	public static List<Block> blockChain = new ArrayList<Block>();
	public static Integer difficulty = 5;

	public static void main(String[] args) {

		blockChain.add(new Block("0", "I am the genesis block"));

		System.out.println("Trying to mine block 1");
		blockChain.get(blockChain.size() - 1).mineBlock(difficulty);

		blockChain.add(new Block(blockChain.get(blockChain.size() - 1).getHash(), "This is the second block"));
		System.out.println("Trying to mine block 2");
		blockChain.get(blockChain.size() - 1).mineBlock(difficulty);

		blockChain.add(new Block(blockChain.get(blockChain.size() - 1).getHash(), "This is the third block"));
		System.out.println("Trying to mine blcok 3");
		blockChain.get(blockChain.size() - 1).mineBlock(difficulty);

		System.out.println("Is block chain valid: " + isChainValid());

		String json = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);

		System.out.println(json);

	}

	public static Boolean isChainValid() {
		Block previousBlock;
		Block currentBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');

		for (int i = 1; i < blockChain.size(); i++) {
			currentBlock = blockChain.get(i);
			previousBlock = blockChain.get(i - 1);

			if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
				System.out.println("Current hashes not equal");
				return Boolean.FALSE;
			}
			if (!previousBlock.getHash().equals(previousBlock.calculateHash())) {
				System.out.println("Previous hashes not equal");
				return Boolean.FALSE;
			}

			if (!currentBlock.getHash().substring(0, difficulty).equals(hashTarget)) {
				System.out.println("The current block hasn't been mined." + currentBlock);
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}
}
