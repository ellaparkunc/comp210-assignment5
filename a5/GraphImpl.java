package a5;

import java.util.HashMap;
import java.util.Map;

public class GraphImpl implements Graph {
    Map<String, Node> nodes; //Do not delete.  Use this field to store your nodes.
                             // key: name of node. value: a5.Node object associated with name

    public GraphImpl() {
        nodes = new HashMap<>();
    }

    @Override
    public boolean addNode(String name) {
        return false;  //Dummy return value.  Remove when you implement!
    }

    @Override
    public boolean addEdge(String src, String dest, double weight) {
        return false;  //Dummy return value.  Remove when you implement!
    }

    @Override
    public boolean deleteNode(String name) {
        //Hint: Do you need to remove edges when you delete a node?

        return false;  //Dummy return value.  Remove when you implement!
    }

    @Override
    public boolean deleteEdge(String src, String dest) {
        return false;  //Dummy return value.  Remove when you implement!
    }

    @Override
    public int numNodes() {
        return 0;  //Dummy return value.  Remove when you implement!
    }

    @Override
    public int numEdges() {
        return 0;  //Dummy return value.  Remove when you implement!
    }

    @Override
    public Map<String, Double> dijkstra(String start) {
        return null;  //Dummy return value.  Remove when you implement!
    }
}
