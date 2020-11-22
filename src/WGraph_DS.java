package ex1;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class WGraph_DS implements weighted_graph , Serializable{
	
	// We use Hashmap to store the edges in the graph 
	private HashMap<Integer, node_info> g;
	int edgeSize;
	int MC;

    public WGraph_DS() {
  	  g = new HashMap<Integer, node_info>();
  	  this.edgeSize =0;
  	  this.MC=0;
  }
    
	@Override
	public node_info getNode(int key) {
		//return the node that got key value = key from the graph
		return this.g.get(key);
	}

	@Override
	public boolean hasEdge(int node1, int node2) {
		// check if these nodes are even exist in hashmap
		if(this.g.containsKey(node1)==false || this.g.containsKey(node2)==false)
			return false;
		if(((NodeInfo)g.get(node1)).hasNi(node2) || ((NodeInfo)g.get(node2)).hasNi(node1))
			return true;
		return false;
	}

	@Override
	public double getEdge(int node1, int node2) {
		node_info n = getNode(node1);
		//check if there is even a connection between them
		if(hasEdge(node1, node2) == false)
			return -1;
		return ((NodeInfo)(n)).getEdgeSize(node2);
	}

	@Override
	public void addNode(int key) {
		NodeInfo n = new NodeInfo(key);
		//check if this Node already exist in our HashMap
		if (!(this.g.containsKey(key))){
			//add node
			this.g.put(key, n);
			MC+=1;
		}
	}
	
	@Override
	public void connect(int node1, int node2, double w) {
		//check if the nodes are exist in hashmap
		if (!(!this.g.containsKey(node1) || !this.g.containsKey(node2) || node1 == node2)) {
			//check if they are already neighbors
			if((((NodeInfo)(this.g.get(node1))).hasNi(node2) || ((NodeInfo)(this.g.get(node2))).hasNi(node1))) {
				((NodeInfo)(this.g.get(node1))).setEdgeSize(node2, w);
				((NodeInfo)(this.g.get(node2))).setEdgeSize(node1, w);
				MC+=1;
			}
			else {
				node_info n1 = getNode(node1);
				node_info n2 = getNode(node2);
				//if not , connect from both sides
			((NodeInfo)(this.g.get(node1))).addNi(node2,w,n2);
			((NodeInfo)(this.g.get(node2))).addNi(node1,w,n1);
				MC+=1;
				edgeSize +=1;
			}
		}
	}

	@Override
	public Collection<node_info> getV() {
		//return all nodes from the graph
		return this.g.values();
	}

	@Override
	public Collection<node_info> getV(int node_id) {
		node_info n = getNode(node_id);
		//check if node even exist in hashmap
		if(n == null) 
			return null;
		//return node
		return ((NodeInfo)(this.g.get(node_id))).getNi();
	}

	@Override
	public node_info removeNode(int key) {
		node_info node = getNode(key);
		Collection<node_info> lneighbors =new LinkedList<node_info>();
		//check if there is neighbors of this node
		if(getV(key) !=null) {
			for(node_info n:getV(key)) {
				lneighbors.add(n);
			}
			//check if node even exist
			if(node!=null) {
				for(node_info n:lneighbors) {
					removeEdge(key,n.getKey());
				}
			}
		}
		//after delete all neighbors ' delete the node
		this.g.remove(key);

		return node;
	}

	@Override
	public void removeEdge(int node1, int node2) {
		//check if nodes even exist
		if (!(!this.g.containsKey(node1) || !this.g.containsKey(node2))) {
			//Remove edge for both nodes
			if(hasEdge(node1, node2)==true) {
				((NodeInfo)(this.g.get(node1))).removeNode(this.g.get(node2));
				((NodeInfo)(this.g.get(node2))).removeNode(this.g.get(node1));
				edgeSize-=1;
				MC+=1;
			}
		}
	}

	@Override
	public int nodeSize() {
		//return the size of the nodes in the graph
		return this.g.size();
	}

	@Override
	public int edgeSize() {
		// return the edges size in the graph
		return edgeSize;
	}

	@Override
	public int getMC() {
		//return mc
		return this.MC;
	}

}
