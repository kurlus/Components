package asim.hackertest.dbfileloader;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.print.URIException;

import asim.hackertest.entities.Trader;

public class traderdbfileloader
{
	
	public static void main(String[] args) throws Exception
	{
		traderdbfileloader maincall = new traderdbfileloader();
		// maincall.traderfile();
		maincall.javaLamdaExpressions();
	}
	
	public List<Trader> traderfile() throws IllegalArgumentException, FileSystemNotFoundException, SecurityException, URISyntaxException
	{
		List<Trader> tradersList = null;
		
		Supplier<List<Trader>> supplierList = () -> new ArrayList<Trader>();
		BiConsumer<List<Trader>, List<String[]>> biConsumerAccumulator = (traders, tokens) -> {			
			tokens.forEach(x -> traders.add(new Trader(x[0].toString().trim(), x[1].toString().trim())));
			return;
		};
		
		BiConsumer<List<Trader>, List<Trader>> combiner = (trader, traders) -> trader.addAll(traders);

		Path path = Path.of(traderdbfileloader.class.getResource("/trader-data.txt").toURI());
		try(Stream<String> traderFileLines = Files.lines(Paths.get(path.toString())))
		{
			//List<String> tokens = traderFileLines.map(line -> line.split(",")).flatMap(Arrays::stream).collect(Collectors.toList());
			//for (String s : tokens) System.out.println(s.trim());
			
			List<String[]> tokens = traderFileLines.map(line -> line.split(",")).collect(Collectors.toList());
			tradersList = Stream.of(tokens).collect(supplierList, biConsumerAccumulator, combiner);
			//for (Trader trd : traders) System.out.println(trd.toString());
		}
		catch(Exception e) {e.printStackTrace();}
		return tradersList;
	}
	
	public void javaLamdaExpressions() throws IllegalArgumentException, FileSystemNotFoundException, SecurityException, URISyntaxException {
		
		Predicate<Integer> isEven = even -> even%2 == 0;
		Predicate<Integer> isOdd = odd -> odd%2 != 0;
		Predicate<Integer> isPrime = prime -> sPrime(prime);
		Predicate<Integer> isPalindrome = number -> isPalindrome(number);

		Path path = Path.of(traderdbfileloader.class.getResource("/isPrimeValidation.txt").toURI());
		try(Stream<String> traderFileLines = Files.lines(Paths.get(path.toString())))
		//try(Stream<String> traderFileLines = traderdbfileloader.class.getResourceAsStream("isPrimeValidation.txt"))
		{
			//List<String[]> tokens = traderFileLines.map(line -> line.split(" ")).collect(Collectors.toList());
			Stream<String> numbers = traderFileLines.flatMap(line -> Stream.of(line.split(" ")));
			
			for (Iterator<String> i= numbers.iterator();i.hasNext();)
			{
				Integer value = Integer.valueOf(i.next());
				System.out.print(value+"\t");
				System.out.println(isPalindrome.test(value.intValue()));
			}	
			
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public Boolean sPrime(int number) {		
	 Boolean primeFlag = true;	
	 
		 for (int i=2; i<=number/2; i++)
		 {	 
			 if (number%i == 0)
			 {
				 primeFlag = false;
				 break;
			 }	 
		 }	 
		return primeFlag;
	}
	
	public boolean isPalindrome(int number) {
		
		boolean isItPalindrome = Boolean.FALSE;
		StringBuffer stringBuffer = new StringBuffer();
		String beforeReversed = String.valueOf(number).trim();
		
		if (beforeReversed.length() > 1)
		{
			stringBuffer.delete(0, stringBuffer.length());
			for (int j=beforeReversed.length(); j>0; j--)
				stringBuffer.append(beforeReversed.charAt(j-1));	
			
			isItPalindrome = stringBuffer.toString().trim().compareTo(beforeReversed) == 0;
		}
		return isItPalindrome;
	}
	

}
