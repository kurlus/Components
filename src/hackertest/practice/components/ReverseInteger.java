package hackertest.practice.components;

public class ReverseInteger 
{

	public static void main (String[] args) {

		ReverseInteger revInteger = new ReverseInteger();
		System.out.println(revInteger.reverse(352));
		
	}	
	
	public int reverse(int x) 
	{
		int rev = 0;
		while (x != 0) 
		{
			int pop = x % 10;
			x /= 10;
			if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
			if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
			rev = rev * 10 + pop;
		}

		return rev;
	}

}
