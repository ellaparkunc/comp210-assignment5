# A5-Dijkstra

For this assignment you will be creating your own Directed Graph and performing Dijkstra's Algorithm
on the graph. This assignment is less structured than others and gives you the freedom to implement
the graph however you choose. The graded portion of the assignment will be the GraphImpl.java class. 
The behind the scenes of your graph are not tested, as long as the methods in GraphImpl.java function correctly.
You are encouraged to create tests in Main.java to test your code. 

Steps for success:
1. Read everything [check!]
2. Brainstorm how to implement nodeimpl and edgeimpl [check!]
3. find slideshows on nodes/edges/dijkstra's 
4. ask questions ab those [check!]

## Task: Implement the methods in Graph.java
These include the following.
```
    boolean addNode(String label);
    boolean addEdge(String src, String dest, double weight);
    boolean deleteNode(String label);
    boolean deleteEdge(String src, String dest);
    int numNodes();
    int numEdges();
    Map<String, Double> dijkstra(String start);
```

> **Hint:** Try to implement and test basic functionality (e.g., creating a graph, adding nodes, adding edges, etc)
> before you implement more complex methods.
## Info and hints on files 

You are graded based on your implementation in GraphImpl.java of the Graph.java methods.  However, you 
should expect to also modify Edge.java, EdgeImpl.java, Node.java, and NodeImpl.java to define node and edge objects and 
provide helper methods as you see fit to make your implementation of the graph methods easier. 

> **Hint:** Look at the classes from previous assigments to see examples of fields and their usage.  You may need to create constructors
> for edge and node objects.   

### Edge.java

The Edge interface is empty. It is your job to come up with the methods that you think an Edge object
should have in order to create a graph and run Dijkstra. Write out the method signatures in
the Edge.java interface and implement those methods in EdgeImpl.java. 
To know what properties an Edge contains, take a look at the createEdge method in GraphImpl.java
 
 > **Hint:** What fields do you need to describe an edge?
 
### Node.java

The Node.java interface currently only has one method, getName(). This method should return the name
associated with the Node. Do not remove this method since it is used for testing. Your task is to
add the remaining methods that you think a Node needs in order to complete Dijkstra's Algorithm.
The addNode() method in GraphImpl.java shows what fields a Node has. These fields are the minimum
required, you may add any other fields that you may think are useful. 

> **Hint:** Remember that we identify nodes based on a String label.  Look at the "nodes" map in 
> GraphImpl.java and make sure you understand it.

### Graph.java

The interface Graph.java has all the methods needed for a graph. This interface is complete; do not 
change any of the methods within it. You may add helper methods if needed in GraphImpl.java. You 
must implement all the methods in this interface.  

> **Hint:** Read Graph.java carefully.  Some of the methods (e.g., addEdge) return false for several different cases.

> **Hint:** Consider writing a small helper method that prints the nodes and edges in a graph to help you debug.

# Getting Started
In previous semesters, many students have had difficulty starting with this assignment, so we wanted to devote a section to helping everyone get started. If you believe you already have an idea of what to do, then you are by no means required to structure your code as we recommend below. There are many correct ways to implement a graph, so your intuition may be easier to follow in certain cases.

You will also notice two different Gradescope assignments online. The first assignment is 9 points and tests the functionality of your code. The second assignment is 1 point and measures the efficiency of your code. The scores of the two assignments will be combined for a total possible score of 10 points.

We recommend that you start by implementing Node and Edge first.

A `Node` will want to have a name (represented as a String) and a collection of Edges leaving that node (easy to represent as a List<Edge>). It will also be helpful to implement methods to see if there is an edge between the current Node and a different Node (passed in as an argument). It will also likely be helpful to have a method that deletes an edge from the current Node to a Node specified as a parameter (make sure that there exists an edge between the two nodes before you try to delete anything!).

An `Edge` represents a directed connection between two nodes. Thus, it makes sense to model EdgeImpl as maintaining a source Node and a destination Node reference. An edge will also have a weight associated with it, which can be modeled as a double.

Once you believe you have Node and Edge correct, write some test cases to verify their behavior and then move on to GraphImpl.

Before you start working on GraphImpl, it will be useful to draw some examples of Directed Graphs and figure out how each method will need to work intuitively before writing any code.

GraphImpl has been given with a Map<String, Node>. This map should be used to access a Node instance given the name of the node. This HashMap will be responsible for storing all of the nodes in the graphh and is the primary internal representation of the graph. The rest of the methods you need to implement have already been given in the GraphImpl class. Some notes are listed below:
>**Hint:** In addNode check that we don't already have a Node with the same name before we try to create a new Node instance.

>**Hint:** In addEdge, first check that there are nodes with names specified by src and dest. Then you can construct Node instances and an Edge instance. Be sure to store the Edge instance in the src Node class.

>**Hint:** In deleteNode, it is not sufficient to simply remove the node reference from the Map<String, Node>. You must also be sure to check every other node, and if there is an edge going to the node we are deleting, we must also delete that edge.

You will also, at some point, find it useful to implement an algorithm to print out your graph for debugging purposes. Since there are many ways to implement the classes for this assignment, we can't give one print method that will work for everybody. Instead, we will give an algorithm below that will be useful for printing graphs.
```
For each Node n in the graph
    Print the name of n
    For each edge e that leaves n (i.e. edges whose source is n)
        print("\t" + n.name + "-->" + e.dest.name)
```
This will end up printing each node and then for each node printing all of the edges that leave that node.

Once you finish implementing the generic graph methods, move on to implementing Dijkstra's Algorithm. Depending on how you choose to implement your classes, you will likely need to add more fields to your NodeImpl class. Also, once you finish the graph methods, it is very important that you write test cases for the Graph methods to make sure they are fully functioning. Moving on without having this be fully correct will cause a lot of debugging issues so please be thorough with your test cases.

Before you start writing code for Dijkstra's, it will be useful to write down some examples and trace through your logic to make sure it covers every case. Once you are confident in your intuition, then go ahead and try implement the algorithm.

Dijkstra's Priority Queue and Comparator function
```
Comparator<ShortestPathQueueObject> compare = (a, b) -> Double.compare(a.distance, b.distance);
 PriorityQueue<ShortestPathQueueObject> queue = new PriorityQueue<ShortestPathQueueObject>(compare);
 ```
Above is code to create a priority queue and the function to compare elements in the priority queue in Java, which is needed for Dijkstras algorithm. This is using the Comparator interface to make a comparison function via Java lambda functions (you don't need to know this or worry about this) and pass it in as a parameter when the priority queue is initialized. Once you do this, you can use the priority queue like normal in the rest of your code and assignment and it will function like a basic minimum priority queue that you learned in class.

>**NOTE:** ShortestPathQueueObject is just an arbitrary object I made that has two fields: 1) a nodes string name, 2) the nodes distance from the source node passed into dijsktras. I recommend people make an object like this to use in the priority queue because it will make it much simpler to use the priority queue, get shortest distances, and add to the hash map, etc. If you don't want to make this object, you can simply do this with Node by adding some "distance" field in your Node class. This is the object below for reference. 
```
public class ShortestPathQueueObject {
 public String label;
 public double distance; 
 
 public ShortestPathQueueObject(String label, double distance) {
  this.label = label;
  this.distance = distance;
 }
}
```

