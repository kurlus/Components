package asim.hackertest.components;

public class MyMath {

	public static boolean checker(PerformOperation p, int num) {
		return p.check(num);
	}

	enum numEnum  {ODD, EVEN, PRIME, COMPOSITE, PALINDROME, NOT_PALINDROME}
	PerformOperation isOddNumber = x -> checkNumberOddEvenPalindrome(numEnum.ODD, x);
	PerformOperation isPrimeNumber = x -> checkNumberOddEvenPalindrome(numEnum.PRIME, x);
	PerformOperation isPalindromeNumber = x -> checkNumberOddEvenPalindrome(numEnum.PALINDROME, x);

	PerformOperation isOdd() {
		return isOddNumber;
	}

	PerformOperation isPrime() {
		return isPrimeNumber;
	}

	PerformOperation isPalindrome(){
		return isPalindromeNumber;
	}

	boolean checkNumberOddEvenPalindrome(MyMath.numEnum numberType, Integer number) 
	{
		try
		{
			if ((numberType == null) || !(number instanceof Integer))throw new Exception(" Invalid number type ...");

			if ((numberType.name().trim().compareTo("ODD") == 0) && (number%2 != 0))
				return true;
			else if ((numberType.name().trim().compareTo("PRIME") == 0) && (getFactorsCount(number) == 2))
				return true;
			else if ((numberType.name().trim().compareTo("PALINDROME") == 0) && (isPalindrome(number) == true))
				return true;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return false;
	}

	int getFactorsCount(int number) {
		int count = 1;

		for (int cntIdx=2; cntIdx<=number; cntIdx++) {
			if (number%cntIdx == 0) ++count;
		}
		return count;		
	}

	boolean isPalindrome(int number) {
		boolean isPalindrome = false;
		String strNumber = String.valueOf(number).trim();
		String reversedString = new StringBuilder(strNumber).reverse().toString().trim();

		if (Integer.valueOf(reversedString).intValue() == number)
			isPalindrome = true;

		return isPalindrome;
	}
}
