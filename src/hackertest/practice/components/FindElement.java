package hackertest.practice.components;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class FindElement 
{
	public static void main(String[] args)
	{
		List<Integer> arr = new Vector<>(); 
		arr.add(1);		
		arr.add(-1);
		arr.add(-2);
		arr.add(3);
		arr.add(1);
		arr.add(3);

		System.out.println(FindElement.maximumSum(arr));
	}

	static long maximumSum(List<Integer> arr) {
		Long maxNumber = 0L;
		if (arr.size() == 0) return maxNumber;

		Collections.sort(arr);
		maxNumber = calculateSum(arr);

		return maxNumber;	
	}

	static Long calculateSum(List<Integer> arr)
	{
		int counter = 0;
		Long sum = 0L;

		do
		{
			sum = arr.get(counter) > 0 ? sum+arr.get(counter) : sum; 
			++counter;
		}	
		while(counter < arr.size());

		return sum;
	}

}
