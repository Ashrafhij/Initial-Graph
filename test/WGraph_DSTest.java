package ex1;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_DSTest {
    private static Random _rnd = null;

    @Test
    void nodeSize() {
    	//init graph
        weighted_graph g = new WGraph_DS();
        //add some node with similar key
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(2);
        //remove some nodes that doesn't exist
        g.removeNode(3);
        g.removeNode(2);
        g.removeNode(2);
        int size = g.nodeSize();
        assertEquals(2,size);

    }

    @Test
    void edgeSize() {
    	//init a graph
        weighted_graph g = new WGraph_DS();
        //add some nodes
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addNode(4);
        //add connection
        g.connect(1,2,10);
        g.connect(2,3,20);
        g.connect(3,1,20);
        double edge3 = g.getEdge(1,3);
        assertEquals(20, edge3);
        g.connect(4,3,5);
        g.connect(3,1,15);
        int edgeSize =  g.edgeSize();
        assertEquals(4, edgeSize);
        double edge1 = g.getEdge(1,4);
        assertEquals(-1, edge1);
        double edge2 = g.getEdge(1,2);
        assertEquals(10, edge2);
        //check after update edge
        edge3 = g.getEdge(1,3);
        assertEquals(15, edge3);
    }

    @Test
    void getV() {
    	//init a graph
    	weighted_graph g = new WGraph_DS();
    	//add some nodes
    	g.addNode(1);
    	g.addNode(2);
    	g.addNode(3);
    	g.addNode(4);
    	//add connection
    	g.connect(1,2,10);
    	g.connect(2,3,20);
    	g.connect(3,1,20);
    	Collection<node_info> k=  g.getV();
    	for (node_info key : k) {
    		assertNotNull(key);
    	}
    }

    @Test
    void hasEdge() {
    	
    	//init a graph
    	weighted_graph myGraph2 = new WGraph_DS();
    	myGraph2.addNode(1);
    	myGraph2.addNode(2);
    	myGraph2.addNode(3);
    	myGraph2.addNode(4);
    	//make some Connections
    	myGraph2.connect(1, 2, 10);
    	myGraph2.connect(2, 3, 20);
    	myGraph2.connect(1, 3, 20);
    	myGraph2.connect(3,4, 5);

    	boolean check = myGraph2.hasEdge(1,2);
    	//should return true
    	assertTrue(check);
    	//should return true
    	check = myGraph2.hasEdge(1,3);
    	assertTrue(check);
    	//should return false
    	check = myGraph2.hasEdge(1,4);
    	assertFalse(check);

    	
    }

    @Test
    void connect() {
    	
    	//init a graph
    	weighted_graph myGraph2 = new WGraph_DS();
    	myGraph2.addNode(1);
    	myGraph2.addNode(2);
    	myGraph2.addNode(3);
    	myGraph2.addNode(4);
    	//make some Connections
    	myGraph2.connect(1, 2, 10);
    	myGraph2.connect(2, 3, 20);
    	myGraph2.connect(1, 3, 20);
    	myGraph2.connect(3,4, 5);
    	//should return false
        assertFalse(myGraph2.hasEdge(1,4));
        myGraph2.removeEdge(2,1);
      //should return false
        assertFalse(myGraph2.hasEdge(1,2));
        myGraph2.connect(1,4,5);
        double edge = myGraph2.getEdge(1,4);
      //should return true equals
        assertEquals(edge,5);
    }


    @Test
    void removeNode() {
    	//init a graph
    	weighted_graph myGraph2 = new WGraph_DS();
    	myGraph2.addNode(1);
    	myGraph2.addNode(2);
    	myGraph2.addNode(3);
    	myGraph2.addNode(4);
    	//make some Connections
    	myGraph2.connect(1, 2, 10);
    	myGraph2.connect(2, 3, 20);
    	myGraph2.connect(1, 3, 20);
    	myGraph2.connect(3,4, 5);
    	
        myGraph2.removeNode(4);
        myGraph2.removeNode(1);
        //after remove the nodes there is no connection between 1-4
        assertFalse(myGraph2.hasEdge(1,4));
        int edgesize = myGraph2.edgeSize();
        //still 2 nodes with 1 edge
        assertEquals(1,edgesize);
    }

    @Test
    void removeEdge() {
    	//init a graph
    	weighted_graph myGraph2 = new WGraph_DS();
    	myGraph2.addNode(1);
    	myGraph2.addNode(2);
    	myGraph2.addNode(3);
    	myGraph2.addNode(4);
    	//make some Connections
    	myGraph2.connect(1, 2, 10);
    	myGraph2.connect(2, 3, 20);
    	myGraph2.connect(1, 3, 20);
    	myGraph2.connect(3,4, 5);
        double edge = myGraph2.getEdge(1,3);
        //there is an edge it is 20
    	assertEquals(edge,20);
        myGraph2.removeEdge(1,3);
        edge = myGraph2.getEdge(1,3);
        myGraph2.removeEdge(1,3);
        //there is no edge between them
        assertEquals(edge,-1);
    }
    
}