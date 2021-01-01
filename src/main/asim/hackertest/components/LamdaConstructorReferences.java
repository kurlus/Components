package asim.hackertest.components;

import java.util.function.BiFunction;

import asim.hackertest.entities.Trader;
import asim.hackertest.functionalInterfaces.CreateTrader;

public class LamdaConstructorReferences {

	public static void main (String[] args)
	{
		LamdaConstructorReferences consReferences = new LamdaConstructorReferences();
		consReferences.testBiFunctionConstructor();
	}
	
	public void testBiFunctionConstructor() {
		BiFunction<String, String, Trader> trader = Trader::new;
		CreateTrader newTrader = trader.apply("Asim", "Milton");
		System.out.println(newTrader.toString());		
	}
	
}
