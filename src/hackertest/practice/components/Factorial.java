package hackertest.practice.components;

public class Factorial 
{
	public static void main(String[] args)
	{
		System.out.println(Factorial.factorial(10));
	}

	static int factNumber = 1;
	static int factorial(int n) {
			if (n == 1) return 1;			
			factNumber = factNumber*n;			
			factorial(n-1);
			System.out.println(factNumber);
			return factNumber;
	}

}
