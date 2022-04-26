package a5;

public class EdgeImpl implements Edge {
    String source;
    String destination;
    double weight;

    /* You will include the implementations for any edge methods you need in this file. */

    /*Hint: Make sure you update the Edge interface in Edge.java when you add a new method implementation
    in EdgeImpl.java, and vice-versa.  getName() in Node.java and NodeImpl.java is an example.  Also, files in
    previous homeworks (e.g., BST.java and BSTImpl.java in homework 3) are good examples of
    interfaces and their implementations.
     */

    /*Also, any edge fields you want to add for the object should go in this file.  */

    //An Edge represents a directed connection between two nodes.
    // Thus, it makes sense to model EdgeImpl as maintaining a source Node and a destination Node reference.
    // An edge will also have a weight associated with it, which can be modeled as a double.

    public EdgeImpl(String source, String destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
        //todo make edge go from source to destination
    }

}
