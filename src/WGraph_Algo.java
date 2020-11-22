package ex1;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WGraph_Algo implements weighted_graph_algorithms , Serializable{
	
	private weighted_graph gr;
	
	public WGraph_Algo() {
		 gr= new WGraph_DS();
	}

	@Override
	public void init(weighted_graph g) {
		//Set Graph
		this.gr=g;
	}

	@Override
	public weighted_graph getGraph() {
		// return the graph that object graph_algo got
		return this.gr;
	}

	@Override
	public weighted_graph copy() {
		weighted_graph grC= new WGraph_DS();

		//Define Copy of All node from This graph to Grpah grC
		for (node_info key : this.gr.getV()) {
			NodeInfo k = new NodeInfo(key.getKey());
			grC.addNode(k.getKey());
		}
		//Connect All The Connected Node like Original Graph
		for (node_info key : grC.getV()) {
			for (node_info k : this.gr.getV(key.getKey())) {
				double w = this.gr.getEdge(key.getKey(), k.getKey());
				grC.connect(key.getKey(), k.getKey(),w);
			}
		}
		return grC;
	}

	@Override
	public boolean isConnected() {
		//graph is connected by definition
		if(this.gr.nodeSize()==0)
			return true;
		//				not enough edges, must be disconnected
		if(this.gr.nodeSize() -1 > this.gr.edgeSize())
			return false;
		Iterator<node_info> it = this.gr.getV().iterator();
		//function returns size of vertex in the graph
		int sizebefore = bfsIsConnected(it.next());
		if(sizebefore != this.gr.nodeSize())
			return false;
		return true;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		// predecessor[i] array stores predecessor of
		// i and distance array stores distance of i
		int max = maxvalue(gr);
		//check if there is a path between these two nodes
		if(this.gr.getNode(src) == null || this.gr.getNode(dest) == null)
			return -1;
		double prev[] = new double[max+1];
		double dist[] = new double[max+1];
		//bfs algorithm that check the shortest path between these two nodes
		if (BFS(this.gr, src,dest,max+1, prev, dist) == false) {
			return -1;
		}
		return dist[dest];
	}

	@Override
	public List<node_info> shortestPath(int src, int dest) {
		// predecessor[i] array stores predecessor of
		// i and distance array stores distance of i
		int max = maxvalue(gr);
		//check if there is a path between these two nodes
		if(this.gr.getNode(src) == null || this.gr.getNode(dest) == null)
			return null;
		double prev[] = new double[max+1];
		double dist[] = new double[max+1];
		//bfs algorithm that check the shortest path between these two nodes
		List<node_info> s =BFSPrev(this.gr, src,dest,max+1, prev, dist);
		
		return s;
	}

	/**
	 * Save the graph into file.
	 * @param file - save into "file"
	 */
	public boolean save(String file) {
		try { //open file
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			//write int oos
			oos.writeObject(this.gr);
			oos.flush();
			//close both { oos , fos }
			oos.close();
			fos.close();
			return true;
		}
		catch (Exception e) {
			System.out.println("Exception in Save");
			return false;
		}
	}


	/**
	 * initial a graph from file
	 * @param file - load into file
	 */
	public boolean load(String file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			weighted_graph g = (weighted_graph)ois.readObject();
			init(g);
			return true;
		}
		catch (Exception e) {
			System.out.println("Exception in Load");
			return false;
		}
	}
	
	
	
	
	private int bfsIsConnected(node_info start) {
		//initial queue
		Queue<node_info> queue= new LinkedList<node_info>();
		//initial array of set
		Set<node_info> seen = new HashSet<node_info>();
		queue.add(start);
		//still exist nodes
		while(!queue.isEmpty()) {
			//add to queue
			node_info curr = queue.poll();
			//check if nodes already seen
			if(!seen.contains(curr)) {
				seen.add(curr);
			}// loop over the neighbors of the node
			if(((NodeInfo)curr).getNi() !=null) {
				for(node_info adjacent : ((NodeInfo)curr).getNi()) {
					//check if already node seen
					if(!seen.contains(adjacent)) {
						queue.add(adjacent);
					}
				}
			}
		}
		return seen.size();
	}
	
	
	
	
	// a modified version of BFS that stores predecessor
	// of each vertex in array pred
	// and its distance from source in array dist
	private boolean BFS(weighted_graph adj, int src, int dest, int v, double prev[], double dist[])
	{
		int i=0;
		double alt=0;
		// a queue to maintain queue of vertices whose
		//  graph is to be scanned as per normal
		// BFS algorithm using LinkedList of node_data type
		LinkedList<node_info> queue = new LinkedList<node_info>();
		for(i=0;i<v;i++) {
			dist[i] = Integer.MAX_VALUE;
			prev[i] = -1;
		}
		// initially all vertices are unvisited
		// so v[i] for all i is unvisited
		// and as no path is yet constructed
		// dist[i] for all i set to infinity
		for(node_info n : adj.getV()) {
			queue.add(n);
		}

		// now source is first to be visited and
		// distance from source to itself should be 0
		dist[src] = 0;

		while (!queue.isEmpty()) {
			int index = getIndex(dist,queue);
			node_info u =gr.getNode(index);
			queue.remove(u);
			for (node_info n : adj.getV(u.getKey())) {
				if (queue.contains(n)) {
					alt = dist[index] + adj.getEdge(n.getKey(), u.getKey());
					if(alt < dist[n.getKey()]) {
						dist[n.getKey()]= alt;
						prev[n.getKey()] = u.getKey();
					}
				}
			}
		}
		return true;
	}
	
	
	// a modified version of BFS that stores predecessor
	// of each vertex in array pred
	// and its distance from source in array dist
	private List<node_info> BFSPrev(weighted_graph adj, int src, int dest, int v, double prev[], double dist[])
	{
		double alt=0;
		int i=0;
		// a queue to maintain queue of vertices whose
		//  graph is to be scanned as per normal
		// BFS algorithm using LinkedList of node_data type
		LinkedList<node_info> queue = new LinkedList<node_info>();
		//		List<node_info> list = new LinkedList();
		List<node_info> list = new LinkedList();
		node_info[] listarray = new node_info[v];
		for(i=0;i<v;i++) {
			dist[i] = Integer.MAX_VALUE;
			prev[i] = -1;
		}
		// initially all vertices are unvisited
		// so v[i] for all i is unvisited
		// and as no path is yet constructed
		// dist[i] for all i set to infinity
		for(node_info n : adj.getV()) {
			queue.add(n);
		}

		// now source is first to be visited and
		// distance from source to itself should be 0
		dist[src] = 0;

		while (!queue.isEmpty()) {
			int index = getIndex(dist,queue);
			node_info u =gr.getNode(index);
			queue.remove(u);
			for (node_info n : adj.getV(u.getKey())) {
				if (queue.contains(n)) {
					alt = dist[index] + adj.getEdge(n.getKey(), u.getKey());
					if(alt < dist[n.getKey()]) {
						dist[n.getKey()]= alt;
						prev[n.getKey()] = u.getKey();
					}
				}
			}
		}
		i=0;
		node_info u=gr.getNode(dest);
		if(prev[u.getKey()] != -1 || u.getKey()==src)
			while(u != null) {
				listarray[i++]=u;
				u = gr.getNode((int)prev[u.getKey()]);
			}
		if(i==1)
			return null;
		while(--i>=0) {
			list.add(listarray[i]);
		}
		return list;
	}
	
	
	
	
	
	//function to get the max value of nodes in a graph
	private int getIndex(double dist[],LinkedList<node_info> list) {
		int index=0,i;
		double min = Integer.MAX_VALUE;
		//loop over all the dist array
		for(i=0;i<dist.length;i++) {
			if(dist[i]<min && list.contains(this.gr.getNode(i))) {
				//if there is < min , save the value , and the index
				min=dist[i];
				index =i;
			}
		}
		return index;
			
	}
	
	
	
	
	
	//function to get the max value of nodes in a graph
	private int maxvalue(weighted_graph gg) {
		int max=0;
		for(node_info e : gg.getV()) {
			if(e.getKey() > max)
				max= e.getKey();
		}
		return max;	
	}
	
	//function to compare between two graphs
	public boolean equalsGraph(weighted_graph obj)   
	{
		boolean flag; // to tell if i found a specific node into the other graph
		//check size if not equals there is no need to go to loop
		if(obj.nodeSize() != this.gr.nodeSize())
			return false;
		//loop to get over all the nodes
		for(node_info it1: obj.getV()) {
			flag=false;
			for(node_info it2: this.gr.getV()) {
				if(it1.getKey() == it2.getKey())
					flag=true;
			}// check if the graph contain it1
			if(flag==false)
				return false;
		}//else return true
		return true;
	}

	
	

}
