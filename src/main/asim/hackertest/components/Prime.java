package asim.hackertest.components;

import java.util.List;
import java.util.Vector;

public class Prime 
{
	
	public static void main(String[] args)
	{
		//Prime.checkPrime(2,1,3, 4, 5);
	}

	public void checkPrime(int ...number) {
		
		List<Integer> primeNumbers = new Vector<>();
		for (int i=0; i<number.length; i++)
		{
			if (!getMultiplesCount(number[i]))
				primeNumbers.add(number[i]);
		}
		
		for(int i=0; i<primeNumbers.size(); i++)
		{
			if (primeNumbers.get(i) != null)
			System.out.print(String.valueOf(primeNumbers.get(i)).concat(" "));
		}
		System.out.println();
	}

	boolean getMultiplesCount(int number)
	{
		int count = 0;
		if (number == 1) return true;
		
		for (int i=1; i<number; i++)
		{
			if (number%i == 0)
				++count;	
			if (count >=2) break;
		}
		return count >=2;
	}

}
