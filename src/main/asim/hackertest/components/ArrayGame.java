package asim.hackertest.components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class ArrayGame 
{
	final static int zero = 0;

	public static void main(String[] args) throws Exception{

		int counter = 1;
		String line = null;
		String[] gameBits = null;
		String[] gameCredentials = null;

		FileReader fileReader = new FileReader("D:\\temp\\array-game.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		while((line = bufferedReader.readLine()) != null) 
		{
			if (counter == 1) {
				gameCredentials = line.trim().split(" ");
				++counter;
			}	
			else if (counter == 2) {
				gameBits = line.trim().split(" ");
				canWin(gameCredentials, gameBits);
				++counter;
			}	
			if (counter > 2)
				counter = 1;
		}

		if (bufferedReader != null)
			bufferedReader.close();
	}

	/*
	 * referenceBits = [incrementbit, leapbit, movebit, successbit]		
	 */
	static void init(int[] referenceBits, int leap) {
		referenceBits[0] = 0;
		referenceBits[1] = leap;
		referenceBits[2] = 0;
		referenceBits[3] = 0;
	}

	static void canWin(String[] gameCredentials, String[] gameBits) {

		int leap = Integer.valueOf(gameCredentials[1].trim());
		int[] game = gameBitsToInteger(gameBits);

		boolean result = canWin(leap, game);
		System.out.println(result);
	} 

	static boolean canWin(int leap, int[] game) {

		int[] successBit = new int[1];
		int[] referenceBits = new int [4];

		if ((game.length == 1) && (game[0] == 0)) {
			successBit[0] = 1;
			return (successBit[0] == 1 ? true : false);
		}	
		else {
			successBit[0] = 0;
		}	

		init(referenceBits, leap);		
		List<Integer> zeroIndexes = getZeroIndexesInArray(game, leap);
		Set<Integer> movedMoves = new HashSet();

		getSuccessPath(game, leap, referenceBits, zeroIndexes, movedMoves);
		return (referenceBits[3] == 1 ? true : false);
	}

	static List<Integer> getZeroIndexesInArray(int[] arr, int leap)
	{
		List<Integer> zeroIndexes = new Vector<>();
		for (int i=0; i<arr.length; i++) {
			if (arr[i] == 0)
				zeroIndexes.add(i);	
		}
		return zeroIndexes;
	}

	/*
	 *  referenceBits = [incrementbit, leapbit, movebit, successbit]
	 */
	static void getSuccessPath(int[] arr, int leap, int[] referenceBits, List<Integer> zeroIndexes, Set<Integer> movedMoves) {

		if (referenceBits[0] >= arr.length) 
			return;

		if ((referenceBits[0] < arr.length) && (arr[referenceBits[0]] == 0) && (referenceBits[0] == (arr.length-1))) {
			referenceBits[3] = 1;
			return;
		}

		int nPlusLeap = (referenceBits[0]+referenceBits[1]);		
		if ((referenceBits[0] < arr.length) && (arr[referenceBits[0]] == 0) && (nPlusLeap >= arr.length)) {
			referenceBits[3] = 1;
			return;
		}

		int nOutOfBound = referenceBits[1];
		if ((nOutOfBound < arr.length) && (arr[nOutOfBound] == 0) && ((nOutOfBound+1) >= arr.length)) {
			referenceBits[3] = 1;
			return;
		}

		List<Integer> possibleMoves = getNextPossibleCombinations(arr, referenceBits, zeroIndexes);		

		if (possibleMoves.size() > 0) {
			for (Integer moveBit : possibleMoves) {
				if (!movedMoves.contains(moveBit)) {
					referenceBits[0] = moveBit;
					movedMoves.add(moveBit);
					getSuccessPath(arr, leap, referenceBits, zeroIndexes, movedMoves);
					if (referenceBits[3] == 1)
						break;
				}
			}
		}

	}

	static List<Integer> getNextPossibleCombinations(int[] arr, int[] referenceBits, List<Integer> zeroIndexes) {

		List<Integer> possibleMoves = new Vector<>();

		int currentIndex = referenceBits[0];
		int leapIndex = currentIndex+ referenceBits[1];

		int nextIndex = currentIndex+1;

		if ((nextIndex < arr.length) && (arr[nextIndex] == 0) && (zeroIndexes.contains(nextIndex))) {
			if (!possibleMoves.contains(nextIndex))
				possibleMoves.add(nextIndex);
		}
		if ((leapIndex < arr.length) && (arr[leapIndex] == 0) && (zeroIndexes.contains(leapIndex))) {
			if (!possibleMoves.contains(leapIndex))
				possibleMoves.add(leapIndex);
		}
		if( (currentIndex-1 > 0) && (currentIndex-1 < arr.length) && (arr[currentIndex-1] == 0) && (zeroIndexes.contains(currentIndex-1))) {
			if (!possibleMoves.contains(currentIndex-1))
				possibleMoves.add(currentIndex-1);
		}

		return possibleMoves;

	}

	static int[] gameBitsToInteger(String[] bits) {

		int[] gBits = new int[bits.length];
		for (int i=0; i<bits.length; i++) {
			gBits[i] = Integer.valueOf(bits[i].trim());
		}
		return gBits;
	}

}
