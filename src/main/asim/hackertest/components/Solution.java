package asim.hackertest.components;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Solution {

	public static void main (String[] args)
	{
		BufferedReader bufferedReader = null;
		try {
			String line = null;
			PerformOperation op;
			boolean ret = false;
			String ans = null;

			MyMath ob = new MyMath();
			FileReader fileReader = new FileReader("D:\\temp\\input\\input00.txt");
			bufferedReader = new BufferedReader(fileReader);

			while((line = bufferedReader.readLine()) != null) {
				System.out.println(line);

				StringTokenizer st = new StringTokenizer(line);
				int ch = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				if (ch == 1) {
					op = ob.isOdd();
					ret = ob.checker(op, num);
					ans = (ret) ? "ODD" : "EVEN";
				} else if (ch == 2) {
					op = ob.isPrime();
					ret = ob.checker(op, num);
					ans = (ret) ? "PRIME" : "COMPOSITE";
				} else if (ch == 3) {
					op = ob.isPalindrome();
					ret = ob.checker(op, num);
					ans = (ret) ? "PALINDROME" : "NOT PALINDROME";
				} 
				System.out.println(ans);
			}
			
		}
		catch(FileNotFoundException ex) {
			ex.printStackTrace();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		finally
		{
			// Always close files.
			try {
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}         

		}
	}

}
