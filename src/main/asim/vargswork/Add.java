package asim.vargswork;

public class Add 
{

	public String add(int...number) throws Exception
	{
		if ((number == null) || (number.length == zero))
			throw new Exception(" No input found for the caller method....");		
		else if ((number[zero] > six) || (number[zero] < zero))
			throw new Exception(" Not valid range for computation.........");
		else if ((number[zero] == one) || (number[zero] == four))
			throw new Exception(" Invalid number for computation.........");
		
	
		return computeAdditionString(number);
	}

	private String computeAdditionString(int...number) throws Exception
	{
		
		if (number[zero] == one)
		{
			concatStr = concatStr.concat(String.valueOf(number[zero]));
			concatSum += number[0];
			
			concatStr = new StringBuffer(concatStr).reverse().toString().concat(EqualsSign).concat(String.valueOf(concatSum));
			return concatStr;
		}	
		
		concatStr = concatStr.concat(String.valueOf(number[zero])).concat(plusSign);
		concatSum += number[0];
		computeAdditionString(--number[zero]);
		
		return concatStr;
	}
	
	
	
	private static final int zero = 0;
	private static final int one = 1;
	private static final int four = 4;
	private static final int six = 6;
	private static final String plusSign = "+";
	private static final String EqualsSign = "=";

	private String concatStr = "";
	private Integer concatSum = zero;
}
