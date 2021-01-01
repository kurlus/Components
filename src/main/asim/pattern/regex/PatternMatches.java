package asim.pattern.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatches 
{
	public static void main(String[] args) {
		
		//PatternMatches pm = new PatternMatches();
		
		//String text = "This is the text to be searched for occurances of the pattern.";
		//String pattern = ".*is.*";
	
		//Pattern patternregex = Pattern.compile(pattern);		
		//Matcher matcher = patternregex.matcher(text);
		
		// System.out.println(matcher.matches());
		// System.out.println(matcher.lookingAt());
		// System.out.println(matcher.regionStart()+" "+matcher.regionEnd());
		
		//PatternMatches.testCaseRegexGroup();
		//PatternMatches.testCaseRegexMultipleGrp();
		
		PatternMatches.regexForHTMLXMlString();
		
	}
	
	/*
	 *  ** reference ** 
	 * 
	 *  - groups are marked with parenthesis in the regular expression (John)
	 *   
	 */
	static void testCaseRegexGroup() {
		
		String text = "John writes about this and John writes about that and John writes about everything. ";
		String pattern = "(John)";
		
		Pattern patternregex = Pattern.compile(pattern);		
		Matcher matcher = patternregex.matcher(text);
		
		while (matcher.find()) {
			System.out.println("found : "+ matcher.group(1));
		}

	}
	
	/*
	 * - "." means any character except a new line
	 * - "+" means one or more times 
	 * - "?" means match a small a number of characters as possible
	 */
	static void testCaseRegexMultipleGrp() {
		
		String text = "John writes about about this and John writes about about that and John John writes about everything.";
		String pattern = "\\b(\\w+)(\\s+\\1\\b)+";
		
		Pattern patternregex = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);		
		Matcher matcher = patternregex.matcher(text);
		
		
		  while (matcher.find()) 
		  { 
			  text = text.replaceAll(matcher.group(), matcher.group(1)); 
		  }
		 System.out.println(text);
	}
	
	static void testCaseConstraints() {
		
		String text = "1Julia_33";
		System.out.println(text.length());
		String pattern ="^[[A-Z]|[a-z]][[A-Z]|[a-z]|\\d|[_]]{7,29}$";
		
		Pattern patternregex = Pattern.compile(pattern);		
		Matcher matcher = patternregex.matcher(text);
		
		System.out.println(matcher.find());
	}

	static void regexForIPValidation() {
		
		String text = "250.222.222.1111";
		System.out.println(text.length());
		String pattern ="^([01]?[0-9][0-9]?|2[0-4][0-9]|25[0-5])\\.([01]?[0-9][0-9]?|2[0-4][0-9]|25[0-5])\\.([01]?[0-9][0-9]?|2[0-4][0-9]|25[0-5])\\.([01]?[0-9][0-9]?|2[0-4][0-9]|25[0-5])$";
		Pattern patternregex = Pattern.compile(pattern);
		Matcher matcher = patternregex.matcher(text);
		System.out.println(matcher.find());
	}
	
	static void regexForHTMLXMlString() {
		
		String text = "<h1><h1>Sanjay has no watch</h1></h1><par>So wait for a while</par>";
		String pattern = "<(.+)>([^<]+)</\\1>";
		
		Pattern patternregex = Pattern.compile(pattern);
		Matcher matcher = patternregex.matcher(text);
		System.out.println(matcher.find());
		
	}
	

	
}
