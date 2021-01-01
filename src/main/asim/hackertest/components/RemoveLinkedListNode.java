package asim.hackertest.components;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RemoveLinkedListNode 
{
	
	public static void main(String[] args)
	{
		RemoveLinkedListNode nodeList = new RemoveLinkedListNode(); 
		
		Node one = nodeList.insert(null, 20);
		Node two = nodeList.insert(one, 3);		
		Node three = nodeList.insert(two, 9);
		Node four = nodeList.insert(three, 9);
		Node five = nodeList.insert(four, 11);
		Node six =  nodeList.insert(five, 11);
		Node seven =  nodeList.insert(six, 11);		
		Node eight =  nodeList.insert(seven, 89);
		Node nine =  nodeList.insert(eight, 89);
		Node ten =  nodeList.insert(nine, 100);
		Node eleven =  nodeList.insert(ten, 100);
		Node twelve =  nodeList.insert(eleven, 101);
		Node thirteen =  nodeList.insert(twelve, 102);
		Node fourteen =  nodeList.insert(thirteen, 103);		
		Node fifteen =  nodeList.insert(fourteen, 108);
		Node sixteen =  nodeList.insert(fifteen, 200);
		Node seventeen =  nodeList.insert(sixteen, 250);
		Node eighteen =  nodeList.insert(seventeen, 250);
		Node nineteen =  nodeList.insert(eighteen, 250);
		Node twenty =  nodeList.insert(nineteen, 250);
		
		nodeList.display(one);
		System.out.println();
		
		Node removed = nodeList.removeDuplicates(one);
		nodeList.display(removed);
	}
	
	public static Node removeDuplicates(Node head) 
	{
		if (head == null) return null;
		Map <Integer, Integer> data =  new HashMap<>();

		Node temp = null;
		Node headNode = head;
		data.put(headNode.data, new Integer(1));
		Node nextNode = head.next;

		while(nextNode != null)
		{
			Integer nData = nextNode.data;

			if (data.containsKey(nData))
			{
				Integer kValue = data.get(nData);
				kValue = kValue + 1;
				data.put(nData, kValue);
			}
			else if (!data.containsKey(nData))
			{
				data.put(nData, new Integer(1));
			}	
				
			nextNode = nextNode.next; 
		}	

		Node currentPointer = head;
		Node prevNode = head;
		nextNode = head.next;		

		if (data.size() > 0)
		{
			for (Iterator<Integer> i=data.keySet().iterator(); i.hasNext();)
			{
				Integer key = i.next();
				Integer keyValue = data.get(key);

				if (keyValue > 1)
				{
					currentPointer = headNode;
					int removalCounter = keyValue; 
					do
					{
						if (currentPointer.data == key.intValue())
						{
							Node tempNode = currentPointer != null ? currentPointer.next : null;

							if (prevNode == currentPointer)
							{	
								prevNode = tempNode;
								headNode = tempNode;
							}	
							else
							{	
								prevNode.next =  tempNode;
							}
							
							currentPointer = null;
							currentPointer = tempNode;				
							nextNode = currentPointer != null ? currentPointer.next : null;
							-- removalCounter;
							if (removalCounter == 1)break;
						}
						else if (currentPointer.data != key.intValue())
						{
							Node tempNode = currentPointer != null ? currentPointer.next : null;
							prevNode = currentPointer;
							currentPointer = tempNode;				
							nextNode = currentPointer != null ? currentPointer.next : null;
						}	

					}
					while(currentPointer != null);
				}
			}//for
		}//if	

		return headNode;
	}

	public Node insert(Node head, int data)
	{
		Node p=new Node(data);			
		if(head==null)
			head=p;
		else if(head.next==null)
			head.next=p;
		else
		{
			Node start=head;
			while(start.next!=null)
				start=start.next;
			start.next=p;

		}
		return head;
	}

	public void display(Node head)
	{
		Node start=head;
		while(start!=null)
		{
			System.out.print(start.data+" ");
			start=start.next;
		}
	}

	/*
    static LinkedListNode removeNodes(LinkedListNode list, int x) {

    	if (list == null) return list;
    	LinkedListNode nextLinkedListNode = null;

    	if (list.next != null)
    		nextLinkedListNode = list.next;

    	if (list.val > x)
    		list.next = null;

    	do
    	{
    		LinkedListNode tempNode = nextLinkedListNode;

    		if (nextLinkedListNode != null)
    		{
    			if (nextLinkedListNode.val > x)
    			{
    				nextLinkedListNode = nextLinkedListNode.next != null ? nextLinkedListNode : null;
    				tempNode.next = null;
    			}	
    		}	
    	}	
    	while (nextLinkedListNode.next != null);

    	return list;
    }
	 */

}
