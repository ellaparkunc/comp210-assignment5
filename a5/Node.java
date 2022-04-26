package a5;

import java.util.ArrayList;

public interface Node {

     //1. A Node will want to have a name (represented as a String)
     // and a collection of Edges leaving that node (easy to represent as a List).
     // It will also be helpful to implement methods to see
     //         if there is an edge between the current Node and a different Node (passed in as an argument).
     //It's helpful to have a method that deletes an edge from the current Node to a Node specified as a parameter
     //        (make sure that there exists an edge between the two nodes before you try to delete anything!).

     /* You will include the method signatures (return type, name, and arg types & more!) for any node methods you
    need in this file. */

    /*Hint: Make sure you update the Node interface in Node.java when you add a new method implementation
    in NodeImpl.java, and vice-versa.  getName() in Node.java and NodeImpl.java is an example.  Also, files in
    previous homeworks (e.g., BST.java and BSTImpl.java in homework 3) are good examples of
    interfaces and their implementations.
     */

     /**
      * @return the name of the node
      */
     String getName();
     ArrayList<EdgeImpl> getEdges();
     boolean connected(String diff);
     void delEdge(String diff);
     public double getDist();
     public void setDist(double dist);



}
