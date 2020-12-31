package hackertest.practice.components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagExtractor 
{
	private static final String sTagBracket = "<";
	private static final String seTagBracket = ">";
	private static final String scTagBracket = "</";

	private static final int validSBit = 1;
	private static final int InValidSBit = -1;
	private static final int validEBit = 2;
	private static final int InValidEBit = -2;


	public static void main(String[] args) throws Exception{

		String line = null;
		FileReader fileReader = new FileReader("D:\\temp\\tag-extractor.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		try {
			while((line = bufferedReader.readLine()) != null) 
			{
				if (TagExtractor.regexForHTMLXMlString(line))
				{
					TagExtractor.getTags(line, null, null);
				}
				else
				{
					System.out.println("None");
				}
			}
		}
		finally {
			if (bufferedReader != null)
				bufferedReader.close();
		}

	}

	/*
	private static final String sTagBracket = "<";
	private static final String seTagBracket = ">";
	private static final String scTagBracket = "</";
	*/
	static void getTags(String line, Tag startTag, Tag endTag) {
		
		if ((startTag == null) && (endTag == null)) {
			int sTagBracketIdx = line.indexOf(sTagBracket);		
			int seTagBracketIdx = line.indexOf(seTagBracket);
			String tagContent = line.substring(sTagBracketIdx+1, seTagBracketIdx);
			
			startTag = new Tag();
			
			startTag.setTagStartIndex(sTagBracketIdx+1);
			startTag.setTagEndIndex(seTagBracketIdx);
			startTag.setTagContent(tagContent);
		}
		
		int closingTag = line.indexOf(startTag.getTagContent(), startTag.getTagEndIndex());
		if (closingTag == -1) {
			
			int sTagBracketIdx = line.indexOf(sTagBracket, startTag.getTagStartIndex());		
			int seTagBracketIdx = line.indexOf(seTagBracket, startTag.getTagEndIndex());
			String tagContent = line.substring(sTagBracketIdx+1, seTagBracketIdx);

			Tag stTag = new Tag();
			startTag.setTagStartIndex(sTagBracketIdx+1);
			startTag.setTagEndIndex(seTagBracketIdx);
			startTag.setTagContent(tagContent);
			
			getTags(line, stTag, endTag);
		}
		else if (closingTag != -1) {
			
			Tag edTag = new Tag();
			int sIdx = line.lastIndexOf(scTagBracket, closingTag);
			
			if (sIdx != -1) {
				String tagBracket = line.substring(closingTag-2, closingTag-1);
				if (tagBracket.equals(scTagBracket))
				{
					int idx = line.lastIndexOf(startTag.getTagContent());
					String cBracket = line.substring(idx, idx+1);
					if (seTagBracket.equals(cBracket)) {
						System.out.println(":)");
						
					}
					
				}
			}
		}//-else 

	}
	
	
	static boolean regexForHTMLXMlString(String text) {

		String pattern = "<(.+)>([^<]+)</\\1>";

		Pattern patternregex = Pattern.compile(pattern);
		Matcher matcher = patternregex.matcher(text);
		return matcher.find();

	}


}
