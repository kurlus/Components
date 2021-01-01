package asim.hackertest.components;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class QueueStack
{
	
	private Queue<Character> queue = new LinkedList<>();
	private Stack<Character> stack = new Stack<>();
	
	/*
	 * pushes a character onto a stack
	 */
	void pushCharacter(char ch){
		stack.push(ch);
	} 
	
	/*
	 * enqueues a character in the queue instance variable. 
	 */
	void enqueueCharacter(char ch) {
		queue.add(ch);
	}
	
	/*
	 * pops and returns the character at the top of the stack instance variable
	 */
	char popCharacter()
	{
		return stack.pop();
	}
	
	/*
	 * dequeues and returns the first character in the queueu instance variable.
	 */
	char dequeueCharacter()
	{
		return queue.poll();
	}

}
