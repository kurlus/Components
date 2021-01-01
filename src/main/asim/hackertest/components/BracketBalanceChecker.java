package asim.hackertest.components;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class BracketBalanceChecker 
{

	public static void main(String[] args)
	{
		BracketBalanceChecker bChecker = new BracketBalanceChecker();
		System.out.println(bChecker.isBalanced("{}(){()}((())){{{}}}{()()}{{}{}}"));
	}

	boolean isBalanced(String stringToValidate) {
		boolean isBalanced = Boolean.TRUE;
		List<Integer> indexesMarked = new Vector<Integer>();
		
		Map<String, String> brackets = getBrackets();
		char[] stringArray = stringToValidate.toCharArray();
		
		if (stringArray.length%2 != 0)
			return Boolean.FALSE;

		for (int idx=0; idx<stringArray.length; idx++)
		{
			int markedIndexesLength = indexesMarked.size();
			
			if (brackets.keySet().contains(String.valueOf(stringArray[idx])))
			{
				String closeBracket = brackets.get(String.valueOf(stringArray[idx]));
				getReverseBracket(stringArray, indexesMarked, closeBracket, 0);
				if (indexesMarked.size() == markedIndexesLength)
				{
					isBalanced = Boolean.FALSE;
					break;
				}
			}
			else if (brackets.values().contains(String.valueOf(stringArray[idx])))
			{
				String startBracket = null;
				for (Iterator<String> i=brackets.keySet().iterator(); i.hasNext();)
				{
					startBracket = i.next();
					String closeBracket = brackets.get(startBracket);
					
					if (closeBracket.trim().compareTo(String.valueOf(stringArray[idx])) == 0) break;					
				}	
				getReverseBracket(stringArray, indexesMarked, startBracket, 0);
				if (indexesMarked.size() == markedIndexesLength)
				{
					isBalanced = Boolean.FALSE;
					break;
				}
				else if (indexesMarked.get(indexesMarked.size()-1) > idx) {
					isBalanced = Boolean.FALSE;
					break;
				}
			}			
		}

		return isBalanced;		
	}
	
	Map<String, String> getBrackets()
	{
		Map<String, String> brackets = new HashMap<>();

		brackets.put("{", "}");
		brackets.put("[", "]");
		brackets.put("(", ")");

		return brackets;
	}

	void getReverseBracket(char[] stringArray, List<Integer> indexesMarked, String closeBracket, int index) {

		if (index == stringArray.length)
			return;

		if ((String.valueOf(stringArray[index]).trim().compareTo(closeBracket) == 0) && (!indexesMarked.contains(Integer.valueOf(index))))
		{
			indexesMarked.add(Integer.valueOf(index));
			return;
		}
		getReverseBracket(stringArray, indexesMarked, closeBracket, ++index);

	}


}
