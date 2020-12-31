package hackertest.practice.components;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScrabbleGame 
{
	
	public static void main (String[] args) throws Exception
	{
		ScrabbleGame sGame = new ScrabbleGame();
		sGame.loadScrabbleWords();
		//sGame.testCase();
		
	}

	public void loadScrabbleWords() throws Exception
	{
		//      a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q,  r, s, t, u, v, w, x, y, z
		int[] letterscores = {
				1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10
		};
		
		int[] scrabbleEndDistribution = {
			    9, 2, 2, 1, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1	
		};
		
		try(Stream<String> ScrabbleWord = Files.lines(Paths.get("D:\\jworkspace\\projects-scrabble-files\\scrabble-words.csv")))
		{
			Function<String[], String> keyMap = array -> String.valueOf(array[0].trim() != null ? array[0].trim() : "");
			Function<String[], String> valueMap = array -> String.valueOf(array[1].trim() != null ? array[1].trim() : "");
			
			Map<String, String> scrabbleWordMeanings = ScrabbleWord.map(word -> word.split(",", 2)).collect(Collectors.toMap(keyMap, valueMap));
			/*for (Map.Entry<String, String> entry : scrabbleWordMeanings.entrySet()) {
			    System.out.println(entry.getKey() + ":" + entry.getValue().toString());
			}*/   
			
			// compute score of each scrabble word
			/*Function<String, String> scrabbleKey = key -> String.valueOf(key);
			Function<String, Integer> scrabbleValue = value -> value.toLowerCase().chars().map(letter -> letterscores[letter - 'a']).sum();
			Map<String, Integer> scrabbleWordScore = 
								scrabbleWordMeanings.keySet().stream().collect(Collectors.toMap(scrabbleKey, scrabbleValue));
			 for (Map.Entry<String, Integer> entry : scrabbleWordScore.entrySet()) {
		    System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}*/   
			
		Function<String, Map<Integer, Long>> wordCharactersCount = 
				word -> word.toLowerCase().chars().boxed().collect(Collectors.groupingBy(letter -> letter, Collectors.counting()));		
		Function<String, Long> nBlanks = word -> wordCharactersCount.apply(word).entrySet().stream().mapToLong(
				entry -> Long.max(entry.getValue() - (long) scrabbleEndDistribution[entry.getKey() - 'a'], 0L)
				).sum();				
		Function<String, Integer> scoreTwo = word -> wordCharactersCount.apply(word).entrySet().stream().mapToInt(
				entry -> letterscores[entry.getKey() - 'a'] * Integer.min(entry.getValue().intValue(), scrabbleEndDistribution[entry.getKey() - 'a'])).sum();
		
		
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public void testCase() {
		
		int[] letterscores = {
				1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10
		};
		
		System.out.println('c' - 'a');
		System.out.println(letterscores['c' - 'a']);
	}
}

