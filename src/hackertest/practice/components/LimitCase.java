package hackertest.practice.components;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.stream.Stream;


public class LimitCase 
{
	public static void main(String[] args)
	{
		//readFromFiles();
		//linesList.stream().forEach(x -> System.out.println(x));
		
		List<String> stringList = new ArrayList<>();
		String[] strTokens = Arrays.stream("at org.springframework.boot.SpringApplication.run(SpringApplication.java:304) [spring-boot-1.5.4.RELEASE.jar:1.5.4.RELEASE]".split("\\s+"))
				.toArray(String[]::new);				
		for(String s : strTokens) stringList.add(s);
		Collections.reverse(stringList);
		stringList.stream().limit(2).forEach(x -> System.out.println(x));
		
	}
	
	/*
	 * creating custom collector
	 * *************************
	public interface Collector<T, A, R> {
	    Supplier<A> supplier();
	    BiConsumer<A, T> accumulator();
	    BinaryOperator<A> combiner();
	    Function<A, R> finisher();
	    Set<Characteristics> characteristics();
	}    
	*/
	
	void readFromFiles()
	{
		try(Stream<String> fileLines = Files.lines(Paths.get("D:\\logs\\batch\\log.txt")))
		{
			fileLines.forEach(l -> linesList.add(l));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	static List<String> linesList = new Vector<>();
}
