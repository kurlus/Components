package asim.hackertest.dbfileloader;

import java.net.URISyntaxException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import asim.hackertest.entities.Trader;
import asim.hackertest.entities.Transaction;

public class transactiondbfileloader
{
	
	public static void main(String[] args) throws Exception
	{
		transactiondbfileloader loader = new transactiondbfileloader();
		loader.loadTraderTransactions();
	}
	
	public void loadTraderTransactions() throws IllegalArgumentException, FileSystemNotFoundException, SecurityException, URISyntaxException
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

		Path path = Path.of(traderdbfileloader.class.getResource("/trader-transactions-data.txt").toURI());
		try(Stream<String> transactions = Files.lines(Paths.get(path.toString())))
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
