package hackertest.practice.components;

import java.util.List;
import java.util.Vector;

public class FizzBuzzNumbers {

	static void fizzBuzz(int n) 
	{
		if (n >= 1)
		{
			List<Integer> fizzNumbers = isFizz(n);
			List<Integer> buzzNumbers = isBuzz(n);
			List<Integer> fizzbuzzNumbers = isFizzBuzz(n);
			List<Integer> nizzBuzz = nietherFizzBuzz(n);

			for (int i=1; i<=n; i++)
			{
				if (fizzNumbers.contains(i))
					System.out.println("Fizz");
				else if (buzzNumbers.contains(i))
					System.out.println("Buzz");
				else if (fizzbuzzNumbers.contains(i))
					System.out.println("FizzBuzz");
				else if (nizzBuzz.contains(i))
					System.out.println(String.valueOf(i));
			}	

		}	
	}

	static List<Integer> isFizzBuzz(int number)
	{
		List<Integer> numbers = new Vector<>();
		if (number < 5) return numbers;

		for (int idx=5; idx<=number; idx++)
		{
			if ((idx%3 == 0) && (idx%5 == 0))
				numbers.add(idx);
		}	
		return numbers;
	}

	static List<Integer> isFizz(int number)
	{
		List<Integer> numbers = new Vector<>();
		if (number < 3) return numbers;

		for (int idx=3; idx<=number; idx++)
		{
			if ((idx%3 == 0) && (idx%5 != 0))
				numbers.add(idx);
		}	
		return numbers;
	}

	static List<Integer> isBuzz(int number)
	{
		List<Integer> numbers = new Vector<>();
		if (number < 5) return numbers;

		for (int idx=5; idx<=number; idx++)
		{
			if ((idx%3 != 0) && (idx%5 == 0))
				numbers.add(idx);
		}	
		return numbers;
	}

	static List<Integer> nietherFizzBuzz(int number)
	{
		List<Integer> numbers = new Vector<>();

		for (int idx=1; idx<=number; idx++)
		{
			if ((idx%3 != 0) && (idx%5 != 0))
				numbers.add(idx);
		}	
		return numbers;
	}

}
