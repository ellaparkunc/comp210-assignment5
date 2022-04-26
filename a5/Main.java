package a5;

public class Main {


    public static void main(String[] args) {

        //You are encouraged (but not required) to include your testing code here.

        //Hint: Try to test basic operations (e.g., adding a few nodes and edges to graphs)
        //before you implement more complex methods)
    GraphImpl test = new GraphImpl();
    test.addNode("a");
    test.addNode("b");
    test.addNode("c");
    test.addEdge("a", "b", 2);
    test.deleteEdge("a","b");
    test.printer();
    }

}
