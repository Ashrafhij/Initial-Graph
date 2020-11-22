package ex1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_AlgoTest {

    @Test
    void isConnected() {
    	//init a graph
    	weighted_graph myGraph2 = new WGraph_DS();
    	myGraph2.addNode(10);
    	myGraph2.addNode(15);
    	myGraph2.addNode(20);
    	myGraph2.addNode(42);
    	//make some Connections
    	myGraph2.connect(10, 15, 10);
    	myGraph2.connect(10, 20, 20);
    	myGraph2.connect(15, 20, 20);
    	myGraph2.connect(20, 42, 5);
        weighted_graph_algorithms ag1 = new WGraph_Algo();
        ag1.init(myGraph2);
        //should be connected graph
        assertTrue(ag1.isConnected());
        myGraph2.removeEdge(10,15);
        //even i removed an edge still should be connected
        assertTrue(ag1.isConnected());
        myGraph2.removeNode(20);
        //should be disconnected
        assertFalse(ag1.isConnected());
    }

    @Test
    void shortestPathDist() {
    	//init a graph
    	weighted_graph myGraph2 = new WGraph_DS();
    	myGraph2.addNode(10);
    	myGraph2.addNode(15);
    	myGraph2.addNode(20);
    	myGraph2.addNode(42);
    	//make some Connections
    	myGraph2.connect(10, 15, 10);
    	myGraph2.connect(10, 20, 20);
    	myGraph2.connect(15, 20, 20);
    	myGraph2.connect(20, 42, 5);
        weighted_graph_algorithms ag1 = new WGraph_Algo();
        ag1.init(myGraph2);
        //should return true
        assertTrue(ag1.isConnected());
        double d = ag1.shortestPathDist(10,42);
        assertEquals(d, 25);
        //changed weight to check if it return also true
        myGraph2.connect(10, 20, 5.5);
        d = ag1.shortestPathDist(10,42);
        assertEquals(d, 10.5);
        //changed for a higher weight so he should return another path
        myGraph2.connect(10, 20, 55);
        d = ag1.shortestPathDist(10,42);
        assertEquals(d, 35);
    }

    @Test
    void shortestPath() {
    	
    	//init a graph
    	weighted_graph myGraph2 = new WGraph_DS();
    	myGraph2.addNode(10);
    	myGraph2.addNode(15);
    	myGraph2.addNode(20);
    	myGraph2.addNode(42);
    	//make some Connections
    	myGraph2.connect(10, 15, 10);
    	myGraph2.connect(10, 20, 20);
    	myGraph2.connect(15, 20, 20);
    	myGraph2.connect(20, 42, 5);
        weighted_graph_algorithms ag1 = new WGraph_Algo();
        ag1.init(myGraph2);
    	//check 
        List<node_info> l1 = ag1.shortestPath(10,42);
        int[] check = {10, 20,42};
        int i = 0;
        for(node_info n: l1) {
        	//assertEquals(n.getTag(), check[i]);
        	assertEquals(n.getKey(), check[i]);
        	i++;
        }
        List<node_info> l1swap = ag1.shortestPath(42,10);
        int[] checkswap = {42, 20,10};
        i = 0;
        for(node_info n: l1swap) {
        	//assertEquals(n.getTag(), checkswap[i]);
        	assertEquals(n.getKey(), checkswap[i]);
        	i++;
        }
    }
    
    @Test
    void save_load() {
    	boolean check;
    	WGraph_DS myGraph = new WGraph_DS();
    	WGraph_DS myGraph2 = new WGraph_DS();
    	myGraph2.addNode(10);
    	myGraph2.addNode(15);
    	myGraph2.addNode(20);
    	myGraph2.addNode(42);
    	
    	myGraph.addNode(1);
    	myGraph.addNode(2);
    	myGraph.addNode(3);
    	myGraph.addNode(4);
    	WGraph_Algo g1 = new WGraph_Algo();
    	g1.init(myGraph);
        String str = "myGraph.obj";
        g1.save(str);
        g1.init(myGraph2);
        check = ((WGraph_Algo) g1).equalsGraph(myGraph2);
        assertEquals(check,true);
        g1.load(str);
        check = ((WGraph_Algo) g1).equalsGraph(myGraph);
        assertEquals(check,true);
        myGraph.removeNode(1);
        check = ((WGraph_Algo) g1).equalsGraph(myGraph);
        assertEquals(check,false);
        
    }
}