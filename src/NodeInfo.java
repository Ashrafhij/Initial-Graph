package ex1;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;


public class NodeInfo implements node_info , Serializable{
	private double tag;
	private String info;
	private HashMap<Integer, Double> neighbors;
	private LinkedList<node_info> neighborSet;
	private int key=0;
	static int instanceCounter = 0;


	public NodeInfo(int k) {
		tag = Integer.MAX_VALUE;
		key = k;
		neighbors = new HashMap<Integer, Double>();
		neighborSet = new LinkedList<node_info>();
	}

	@Override
	public int getKey() {
		//return key
		return this.key;
	}

	@Override
	public String getInfo() {
		//return info
		return this.info;
	}

	@Override
	public void setInfo(String s) {
		//set info
		this.info=s;
	}

	@Override
	public double getTag() {
		//return tag
		return this.tag;
	}

	@Override
	public void setTag(double t) {
		//set tag
		this.tag=t;
	}
	
	/**
	 * return true if there is an edge between the two nodes.
	 */
	public boolean hasNi(int key) {
		//Search for This Key In The Neigbors of this specific Node
		if(this.neighbors.containsKey(key)==true)
			return true;
		return false; // else so not FOUND!
	}
	
	/*
	 * This method adds the node_data (t) to this node_data neighbors.
	 */
	public void addNi(int t , double bandwith, node_info n) {
		// add neighbor 
		this.neighbors.put(t,bandwith);
		this.neighborSet.push(n);
	}


	
	/* This method returns a collection with all the Neighbor of this node_data */
	public Collection<node_info> getNi() {
		// return the arraylist of neighbors
		return (Collection<node_info>) this.neighborSet;
	}
	

	
	/**
	 *  return the size of edge between the two vertecis
	 * @param key
	 * @return edge size
	 */
	public double getEdgeSize(int key) {
		//Search for This Key In The Neigbors of this specific Node
		if(this.neighbors.containsKey(key)==true)
			return this.neighbors.get(key);
		return -1; // else so not FOUND!
	}
	
	/**
	 * set the size of edge between two vertex
	 * @param key
	 */
	public void setEdgeSize(int key , double bandwith) {
		//Search for This Key In The Neigbors of this specific Node
		if(this.neighbors.containsKey(key)==true)
			this.neighbors.put(key, bandwith);
	}


	/**
	 * Removes the edge between this-node and node
	 */
	public void removeNode(node_info node) {
		// remove from arraylist of neighbors
		this.neighborSet.remove(node);
		this.neighbors.remove(node.getKey());
	}

}
