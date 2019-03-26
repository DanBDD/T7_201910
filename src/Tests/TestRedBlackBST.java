//Este codigo fue tomado de la pagina de Algoritms 4th Edition

package Tests;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import model.data_structures.ArregloDinamico;
import model.data_structures.Cola;
import model.data_structures.RedBlackBST;
import model.data_structures.RedBlackBST.Node;
import model.data_structures.StdOut;

/******************************************************************************
 *  Compilation:  javac TestBST.java
 *  Execution:    java TestBST
 *  Dependencies: BST.java
 *
 *  A test client for BST.java.
 * 
 *  % java -ea TestBST
 *
 ******************************************************************************/

public class TestRedBlackBST extends TestCase{
	protected RedBlackBST<String, Integer> st;

	@Before
	public  void setUp() { 
		String test = "R E D B L A C K B S T"; 
		String[] keys = test.split(" "); 
		int n = keys.length; 
		st = new RedBlackBST<String, Integer>();
		assertEquals(st.size(), 0);
		assertTrue(st.isEmpty());
		for (int i = 0; i < n; i++) 
			st.put(keys[i], i); 
	}
	@Test
	public void tests()
	{
		assertEquals(10, st.size());
		assertFalse(st.isEmpty());
		//get
		assertEquals((int)st.get("R"), 0);
		assertEquals((int)st.get("A"), 5);
		assertEquals((int)st.get("T"), 10);
		//getHeight
		RedBlackBST<String, Integer>.Node nodo=st. new Node("R", 0, st.BLACK, 3);
		assertEquals(st.getHeight(nodo), 0);
		//height
		assertEquals(st.height(), 3);
		//contains
		assertTrue(st.contains("B"));
		assertTrue(st.contains("A"));
		assertTrue(st.contains("E"));
		assertFalse(st.contains("Q"));
		assertFalse(st.contains("O"));
		//min
		assertEquals(st.min(), "A");
		//max
		assertEquals(st.max(), "T");
		//check
		assertTrue(st.check());
		//keys
		int [] nums={5,8,6,2,1,7,4,0,9,10};
		Iterable<String> c=st.keys();
		Iterator<String> i=c.iterator();
		int pos=0;
		while(i.hasNext())
		{
			String n = (String) i.next();
			if(nums[pos]==st.get(n))
			{
				pos++;
			}
			else
				fail();
		}
		//keysInRange
		String [] let={"C","D","E","K","L","R"};
		Iterable<String> c2=st.keys("C","R");
		Iterator<String> i2=c2.iterator();
		int pos2=0;
		while(i2.hasNext())
		{
			String n = (String) i2.next();
			if(let[pos2].equals(n))
			{
				pos2++;
			}
			else
				fail();
		}
		//valuesInRange
		int [] nums3={6,2,1,7,4,0};
		Iterable<Integer>  c3=st.values("C","R");
		Iterator<Integer> i3=c3.iterator();
		int pos3=0;
		while(i3.hasNext())
		{
			int n = i3.next();
			if(nums3[pos3]==n)
			{
				pos3++;
			}
			else
				fail();
		}
	}
}


