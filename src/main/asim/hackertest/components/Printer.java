package asim.hackertest.components;

public class Printer<T> {

	   /**
	    *    Method Name: printArray
	    *    Print each element of the generic array on a new line. Do not return anything.
	    *    @param A generic array
	    **/
	    
	    void printArray(T arr[])
	    {
	        for (Object obj : arr)
	        System.out.println(obj.toString());    
	    }
	
}
