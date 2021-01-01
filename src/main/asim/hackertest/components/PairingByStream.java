package asim.hackertest.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PairingByStream {
	
	static int[] cooidnatesA = {1, 2, 3};
	static int[] cooidnatesB = {3, 4};
	
	/*
	 given a list [1, 2, 3] and a list [3,4], return [(1,3), (1,4), (2,3), (2,4), (3, 3), (3,4)]
	*/ 
	public static void main(String[] args) {
		PairingByStream pairing = new PairingByStream();
		pairing.intCooridnates(PairingByStream.cooidnatesA, PairingByStream.cooidnatesB);
	}
	
	public void intCooridnates(int[] cooidnatesA, int[] cooidnatesB)
	{
		ArrayList<Integer> arrayA = new ArrayList<>();
		ArrayList<Integer> arrayB = new ArrayList<>();
		
		Integer[] cooridnatesA = Arrays.stream(cooidnatesA).boxed().toArray(Integer[] ::new);
		Integer[] cooridnatesB = Arrays.stream(cooidnatesB).boxed().toArray(Integer[] ::new);
		
		 Collections.addAll(arrayA, cooridnatesA);
		 Collections.addAll(arrayB, cooridnatesB);
		 
		 List<int[]> pairs = arrayA.stream().flatMap(x -> arrayB.stream().map( y-> new int[] {x,y})).collect(Collectors.toList());
		 //for (int[] pair : pairs) System.out.println(String.valueOf(pair[0]).concat(",").concat(String.valueOf(pair[1])));
		 //pairs.stream().forEach(pair ->  System.out.println(String.valueOf(pair[0]).concat(",").concat(String.valueOf(pair[1]))));
		 
		 pairs.stream().map(pair -> String.valueOf(pair[0]).concat(",").concat(String.valueOf(pair[1]))).forEach(System.out::println);
		 
	}

}
