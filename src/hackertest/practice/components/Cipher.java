package hackertest.practice.components;

import java.util.Scanner;
import java.security.MessageDigest;
import java.util.function.Function;

public class Cipher {

	public static void main(String[] args) throws Exception
	{
		Cipher cipher = new Cipher();
		Scanner scanner = new Scanner(System.in);
		String str = scanner.next();
		//cipher.encryptApplyingMD5Algorithm(str);
		cipher.encryptApplyingSHA256Algorithm(str);
	}

	public void cipherAlgo(String code)
	{
		/*
		Integer[] intArray = IntStream.range(97, 123).boxed().toArray(x -> new Integer[x]);
		Stream.of(intArray).mapToInt(x -> Integer.valueOf(x)).forEach(System.out::println);
		 */

		Function<Character, byte[]> charToBytes = x -> String.valueOf(new Character(x)).getBytes();		
		Function<byte[], Character> bytesToChar = x -> {
			byte number = x[0];
			return new Character((char) (number+5));
		};

		char[] array = code.toCharArray();
		for (int i=0; i< array.length; i++)
		{
			Character ch = array[i];
			byte[] by = charToBytes.apply(ch);
			ch = bytesToChar.apply(by);
			System.out.print(ch);
		}		
		System.out.println();		
	}
	
	/*
	 * SHA-256 is a 256 bit (32 byte) hashing algorithm which can 
	 * calculate hashcode for an input upto 264-1 bits 
	 */
	void encryptApplyingSHA256Algorithm(String str) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] hashBytes = md.digest(str.getBytes("UTF-8"));
		String bt = convertByteArrayToHexString(hashBytes);
		System.out.println(bt);
	}

	void encryptApplyingMD5Algorithm(String str) throws Exception {

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] hashBytes = md.digest(str.getBytes("UTF-8"));
		String bt = convertByteArrayToHexString(hashBytes);
		System.out.println(bt);
	}

	private String convertByteArrayToHexString(byte[] arrayBytes) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < arrayBytes.length; i++) {
			stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return stringBuffer.toString();
	}

}
