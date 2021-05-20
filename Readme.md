# Graph Implementation

	general description of the project is to implements
 	(undirectional) weighted graph , Includes Set Of Nodes :

	-Node : Each Node Include Unique Key , Set Of Neighbors type node_data
		and also another set type Hashmap that contain the key of each node
		and also the edge weight value between them and there is some methods like info , Tag
	-Graph : implemented Graph By set of nodes type of Hashmap , 
		   which can be connected together , also Graph includes
			edgeSize variable to contain total edges in the graph.

	-Graph_algo : initialization of a graph with methods which check if
			the Graph is Connected or to get the Shortest Path
			between two nodes , Save / Load - graph 

	i Used two types of Data Structure  : ArrayList and Hashmap
	* ArrayList : Used to represent the Neighbors of a specific Node
			in a graph so that gives me ease of access to each
			neighbor by key and to remove/add or to get list of
			neighbors of specific node just by key all that
			works in O(1), and also used hashmap into NodeInfo to 
			save the Weight Value Between Two Nodes

	* Hashmap : HashMap stores the node's number type of Ineteger as key ,
			the value is a Node Type of node_data , which also this
			value(node) is the vertex which contain also neighbors
			of that key.
		   used hashmap into NodeInfo to save the Weight Value Between Two Nodes

	Methods , Functions and complexity : 
		is used these structures of hashmap , Arraylist so i can get
		complexity O(1)for the majority of methods in Both Graph's methods
		and also Node's methods so that a graph of million vertices can
		be constructed.

	*isConnected , ShortestPath Methods :
		in these methods i used a BFS which it explores all of the
 		neighbor nodes at the present depth prior to moving on to 
		the nodes at the next depth level,which explores the node 
		branch as far as possible before being forced to backtrack
		and expand other nodes , The time complexity can be expressed 
		as O(|V|+|E|), since every vertex and every edge will be
		explored in the worst case , for ShortestPath i used DIJKSTRA 
		Algorithm.
			
		
