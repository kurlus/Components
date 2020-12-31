package hackertest.practice.dbfileloader;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import hackertest.practice.entities.Trader;
import hackertest.practice.entities.Transaction;

public class transactiondbfileloader
{
	
	public static void main(String[] args) throws Exception
	{
		transactiondbfileloader loader = new transactiondbfileloader();
		loader.loadTraderTransactions();
	}
	
	public void loadTraderTransactions() throws Exception
	{
		traderdbfileloader traderloader =  new traderdbfileloader();
		List<Trader> tradersList = traderloader.traderfile();
		
		Map<String, Trader> mappedTraders = tradersList.stream().collect(Collectors.toMap(t -> t.getName(), t -> t, (t1, t2) -> t1));
		//for (Trader t : mappedTraders.values()) System.out.println(t.toString());
		Supplier<List<Transaction>> supplier = () -> new ArrayList<Transaction>();
		BiConsumer<List<Transaction>, String[]> accumulator = (transaction, tokenArray) ->
		{
			Trader trader = mappedTraders.get(tokenArray[0].trim());
			transaction.add(new Transaction(trader, Integer.parseInt(tokenArray[1].trim()), Integer.parseInt(tokenArray[2].trim())));
		};
		
		BiConsumer<List<Transaction>, List<Transaction>> combiner = (transactions, instantiated) -> transactions.addAll(instantiated);
		
		
		try(Stream<String> transactions = Files.lines(Paths.get("D:\\jworkspace\\projects-database-files\\trader-transactions-data.txt")))
		{
			List<String[]> transactionTokens = transactions.map( x -> x.split(",")).collect(Collectors.toList());
			List<Transaction> transacDetails = transactionTokens.stream().collect(supplier, accumulator, combiner);
			for (Transaction t : transacDetails) System.out.println(t);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
