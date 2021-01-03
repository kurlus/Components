package asim.hackertest.dbfileloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
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
import asim.utility.AppUtility;

public class transactiondbfileloader {

    public static void main(String[] args) throws Exception {
        transactiondbfileloader loader = new transactiondbfileloader();
        loader.loadTraderTransactions();
    }

    public void loadTraderTransactions() throws IllegalArgumentException, FileSystemNotFoundException, SecurityException, URISyntaxException, IOException {
        traderdbfileloader traderloader = new traderdbfileloader();
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

			/*
		    copied from
		   // https://stackoverflow.com/questions/62611044/filesystemnotfoundexception-while-running-jar-fxyz3d-library
		   When embedded in a JAR file a resource's URL has the jar: protocol. This means a FileSystem from
		   the ZIP FileSystemProvider, open to the specific ZIP/JAR file, must exist in order for the conversion
		   to Path to work. This issue would not occur if the implementation simply used URL#openStream(), which
		    accesses the JAR entry via a different mechanism.

        URI uriPath = transactiondbfileloader.class.getResource("/trader-transactions-data.txt").toURI();
        try (Stream<String> transactions = Files.lines(AppUtility.getResourcePath(uriPath))) {
		 */
        InputStream iStream = getClass().getResourceAsStream("/trader-transactions-data.txt");
        BufferedReader bReader = new BufferedReader(new InputStreamReader(iStream));

        try {
            String transactions = null;
            while ((transactions = bReader.readLine()) != null) {
                List<String[]> transactionTokens = Stream.of(transactions).map(x -> x.split(",")).collect(Collectors.toList());
                List<Transaction> transacDetails = transactionTokens.stream().collect(supplier, accumulator, combiner);
                for (Transaction t : transacDetails) System.out.println(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
