package a5;

//import jdk.internal.access.JavaIOFileDescriptorAccess;

import java.util.ArrayList;
import java.util.List;

public class NodeImpl implements Node {
    String name;
    ArrayList<EdgeImpl> edges; //edges only include those that leave this node.
    //any instance variables if not otherwise specified are automatically 'protected' kind of like private except classes can inherit
    public double distance; //from source node to this one by the shortest path
    //public boolean visited;
    //Node previous;
    //1. A way to track the distance from the source node of a shortest path
    //2. A flag to tell if we have already visited this node or not
    //3. previous/adjacent vertex

    //private JavaIOFileDescriptorAccess nodes;
    /* You will include the method signatures (return type, name, and arg types) for any node methods you
    need in this file. */

    /*Hint: Make sure you update the Node interface in Node.java when you add a new method implementation
    in NodeImpl.java, and vice-versa.  getName() in Node.java and NodeImpl.java is an example.  Also, files in
    previous homeworks (e.g., BST.java and BSTImpl.java in homework 3) are good examples of
    interfaces and their implementations.
     */

    //add the remaining methods that you think a Node needs in order to complete Dijkstra's Algorithm

    /*Also, any node fields you want to add for the object should go in this file.  */

    //it already has [String name]


    //1. A Node will want to have a name (represented as a String) [getName]
    //2.  and a collection of Edges leaving that node (easy to represent as a List). [edges]
    //3. It will also be helpful to implement methods to see
    //         if there is an edge between the current Node and a different Node (passed in as an argument).
    //4. It's helpful to have a method that deletes an edge from the current Node to a Node specified as a parameter
    //        (make sure that there exists an edge between the two nodes before you try to delete anything!).

    //@Override
    public NodeImpl(String name) {
        this.name = name;
        this.edges = new ArrayList<EdgeImpl>();
        this.distance = 1000000000;
        //this.visited = false;
    }

    public String getName() {
        return this.name;  //Dummy return value.  Remove when you implement!
    }
    public ArrayList<EdgeImpl> getEdges() {
        return this.edges;
    }

    public boolean connected(String diff) {
        //edgeimpl: class of edges
        //edges: all our edges
        //edges.get(i): the specific edge we are looking at which has a source, destination, and weight
        //i is the index of each edge from our edgeimpl
        if (diff == null) {
            return false;
        }
        for (int i = 0; i< this.edges.size(); i++) {
            if (edges.get(i).destination.equals(diff)) {
                return true;
            }
        }
        //do i need to also check the edges coming from diff to 'this'?
        //no! you call this fn on the
        //for (int i = 0; i< this.edges.size(); i++) {
            //if (edges.get(i).destination == diff) {
              //  return true;
           // }
        //}
        return false;
    }

    public void delEdge(String diff) {
        //honestly, i don't know why i made this into a NodeImpl helper fn instead of keeping it in graph.java
        if (diff == null) {
            return;
        }
        if (connected(diff)) {
            //i have a node
            //how do I subtract something from a list?
            //loop through edges? ask if dest node matches the diff name
            //when you find the correct edge, THAT'S the one you remove
            for (int i = 0; i < this.edges.size(); i++) {
                if (edges.get(i).destination.equals(diff)) {
                    //we've found the specific edge we want to delete!
                    this.getEdges().remove(i);
                    //todo: remove specific from the 'edge' ArrayList in both this.edges & the value corresponding to diff
                    //QUESTION: how do i talk about the value of the key diff?? they both are in my HashMap
                    //it should be hashmap.get(key)
                    //my hashmap is named nodes!
                    //AAAAH it added a lot of stuff and now nodes doesn't refer to what i want it to :...(
                    //do i need to create a get fn in graphimpl that gives me nodes???????????????????
                    //this.nodes.get(diff).remove(specific);
                    //nodes.get(diff).getEdges().remove(specific);
                }
            }
        }
    }
    ///public void addEdge(String diff) {
    public double getDist(){
        return this.distance;
    }

    public void setDist(double dist){
        this.distance = dist;
    }

    }

