package ex1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.Test;


class NodeInfoTest {

    @Test
    void getKey() {
    	//init node
    	node_info n = new NodeInfo(5);
    	int val=n.getKey();
        assertEquals(5, val);
    }
    
    @Test
    void hasNi() {
    	boolean t;
    	node_info n1 = new NodeInfo(5);
    	node_info n2= new NodeInfo(10);
    	((NodeInfo)n1).addNi(10, 10, n2);
    	//check if they are neighbors
    	t=((NodeInfo)n1).hasNi(10);
    	assertEquals(t, true);
    }
    
    @Test
    void addNi() {
    	//initial two nodes
    	node_info n1 = new NodeInfo(5);
    	node_info n2= new NodeInfo(10);
    	((NodeInfo)n1).addNi(10, 10, n2);
    	//there is only 1 neighbor so it should return 1
    	Collection<node_info> l = ((NodeInfo)n1).getNi();
    	assertEquals(1, l.size());
    }
    
    @Test
    void getNi() {
    	//initial two nodes
    	node_info n1 = new NodeInfo(5);
    	node_info n2= new NodeInfo(10);
    	((NodeInfo)n1).addNi(10, 10, n2);
    	//there is only 1 neighbor so it should return 1
    	Collection<node_info> l = ((NodeInfo)n1).getNi();
    	assertEquals(1, l.size());
    }
    
    @Test
    void getEdgeSize() {
    	//initial two nodes
    	node_info n1 = new NodeInfo(5);
    	node_info n2= new NodeInfo(10);
    	((NodeInfo)n1).addNi(10, 10, n2);
    	//there is only 1 neighbor so it should return 10 edge size
    	double edgesize = ((NodeInfo)n1).getEdgeSize(10);
    	assertEquals(10, edgesize);
    }
    @Test
    void setEdgeSize() {
    	//initial two nodes
    	node_info n1 = new NodeInfo(5);
    	node_info n2= new NodeInfo(10);
    	((NodeInfo)n1).addNi(10, 10, n2);
    	//there is only 1 neighbor so it should return 10 edge size
    	double edgesize = ((NodeInfo)n1).getEdgeSize(10);
    	assertEquals(10, edgesize);
    	//changed edge that i can check after change
    	((NodeInfo)n1).setEdgeSize(10, 5);
    	edgesize = ((NodeInfo)n1).getEdgeSize(10);
    	assertEquals(5, edgesize);
    }
    @Test
    void removeNode() {
    	//initial a graph
    	weighted_graph g= new WGraph_DS();
    	//add two nodes
    	g.addNode(10);
    	g.addNode(5);
    	assertEquals(2, g.nodeSize());
    	//remove node to check if the size turn 1
    	g.removeNode(10);
    	assertEquals(1, g.nodeSize());
    }
    

}
