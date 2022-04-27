package a5;
//1. edge cases
//2. go through debugger

//BIG QUESTIONS:
//1. is there a better way to talk about a specific node, say the node that starts @ __ and ends @___ w/o having to do a while loop to get there?

//STEPS FOR SUCCESS
//1. test that it's not null [check!]
//2. add signatures to each impl [check!]
//3. fix delEdge in NodeImpl [check!]
//4. update other nodes in addEdge [check!] and delEdge [check lil]
//5. write numNodes and numEdges [that's just going to include putting a size variable in everything] [for numNodes]
//6. test that my functions work

import java.util.*;

//w hashmaps, we can do .get, .containsKey, .containsValue,
// .replace (doesn't do anything if it doesn't exist)
//.putIfAbsent only updates if it's not already in the map
//.remove
//if you 'put' for a key which already is in your hashmap,
    // it simply updates that key
//btw, you have to use big boy upper case classes for what you are hashing
public class GraphImpl implements Graph {
    Map<String, Node> nodes; //Do not delete.  Use this field to store your nodes.
    // key: name of node. value: a5.Node object associated with name
    int numEdges;

    public GraphImpl() {
        //constructor
        // a map hashing from string value to string value
        nodes = new HashMap<>();
        this.numEdges = 0;
        //im confused as to how/why we should be constructing a graph with no nodes?
        //okay, i guess it's bc we want the option to make a graph with no nodes
    }

    @Override
    public boolean addNode(String name) {
        //how do i know that value should be associated with my node??
        if (name == null) {
            return false;
        }
        //want to make sure we don't replace a node with lots of info already contained in it
        if (nodes.containsKey(name)) {
            return false;
        }
        Node value = new NodeImpl(name);
        nodes.put(name, value);
        return true;
    }

    @Override
    public boolean addEdge(String src, String dest, double weight) {
        //source, destination, weight
        if (src == dest) {
            return false;
        }
        if (weight < 0) {
            return false;
        }
        if (src == null || dest == null) {
            return false;
        }
        if (!nodes.containsKey(src)|| !nodes.containsKey(dest)) {
            return false;
        }
        //addEdge should return false edge already exists between the 2 nodes
        if (nodes.get(src).connected(dest)) {
            return false;
        }
        EdgeImpl toAdd = new EdgeImpl(src, dest, weight);
        //i think i just have to add this edge to the start and ends nodes?
        //from the hashmap nodes, give the node value of the src key
        //to this node, edit its edges ArrayList<EdgeImpl>
        nodes.get(src).getEdges().add(toAdd);
        //nodes.get(dest).getEdges().add(toAdd); don't need it bc it's the destination
        this.numEdges++;
        return true;
    }

    public void printer() {
        //this loops through just the nodes!!
        for (Node n : nodes.values()) {
            System.out.println(n.getName());
            for (EdgeImpl e : n.getEdges()) {
                System.out.println("\t" + n.getName() + "-->" + e.destination);
            }
        }
    }
    //For each Node n in the graph
    //Print the name of n
    //For each edge e that leaves n (i.e. edges whose source is n)
    //print("\t" + n.name + "-->" + e.dest.name)

    @Override
    public boolean deleteNode(String name) {
        if (!nodes.containsKey(name)) {
            return false;
        }
        //Hint: Do you need to remove edges when you delete a node?
        //i guess so (cries), but why wouldn't deleting the node delete it for you?
        //oh it deletes all the edges that the node itself is keeping track of
        //but other nodes have memory of the edge so must iterate through THEM
        ArrayList<EdgeImpl> toDel = new ArrayList<>();
        for (Node n : nodes.values()) {
            //for each edge in the node we will delete,
            for (EdgeImpl e : n.getEdges()) {
                if (e.destination.equals(name)) {//go to where node to delete is the DESTINATION
                    toDel.add(e);
                    //deleteEdge(e.source, e.destination);
                }
            }
        }
        for (EdgeImpl e: toDel){
            deleteEdge(e.source, e.destination);
        }
        nodes.remove(name);
        return true;
    }


    @Override
    public boolean deleteEdge(String src, String dest) {
        if (src == null || dest == null) {
            return false;
        }
        if (!nodes.containsKey(src)|| !nodes.containsKey(dest)) {
            return false;
        }
        //Removing an edge that does not exist in the graph should return false
        if (!nodes.get(src).connected(dest)) {
            return false;
        }
        //must call delEdge on node associated with the key src
        Node source = nodes.get(src);
        source.delEdge(dest);
        //how do i say weight for the edge which goes from src to dest?
        //i think i may need a for loop to find the weight
        //int index = nodes.get(src).getEdges().indexOf(source == src);
        //iterate through the edges of the source node
//        int index = 0;
//        for (int i = 0; i < nodes.get(src).getEdges().size(); i++){
//            //this is where i wonder if i should just associate a name to each edge...
//            //whatever we're doing it with a loop
//            if (nodes.get(src).getEdges().get(i).source == src){
//                index = i;
//            }
//        }
//        double weight = nodes.get(src).getEdges().get(index).weight;
//        EdgeImpl toDel = new EdgeImpl(src, dest, weight);
//        nodes.get(src).getEdges().remove(toDel);
//        nodes.get(dest).getEdges().remove(toDel);
        this.numEdges--;
        return true;
    }

    @Override
    public int numNodes() {
        return nodes.size();
    }

    @Override
    public int numEdges() {
//        //if i just went through each node and added all edges up,
//            //we would double count every time we add
//            //so we'd have to subtract OR only add edges where the node in question is the source hehe
//        for (Node value: nodes.values()){
//            //aparently i can't iterate through my map?
//            //AH! must use for-in loop
//            //okay aparently, you can't do that w maps
//            value.getEdges();
//
//        }
        return numEdges;
    }

    @Override
    public Map<String, Double> dijkstra(String start) {
        //if (start == null) {
        // return false; WAIT how do i protect against this when we're returning a map??
        //}
        //111. make 2 hashmaps (one to return and other visited) and dijkstra's
        //for dijkstra's you should never need to backtrack.
        //nodes.get(start).previous = null;
        //222.check your start node, then the nodes connected to it by the shortest path, mark as visited, update distance relative to INITIAL STARTING NODE
        //for each edge connected to start node, which is the smallest? mark that as visited and distance = smallest value
        //double shortest = 0;
        //String where_shortest = start;
        //if we have things queued, we can't be certain that our data is paired with its shortest path
        Comparator<ShortestPathQueueObject> compare = (o1, o2) -> Double.compare(o1.distance, o2.distance);
        //whole point of priority queue: sorts it based on shortest path to A.
        //priority queue is only FIFO if two nodes have the same priority
        PriorityQueue<ShortestPathQueueObject> queue = new PriorityQueue<ShortestPathQueueObject>(compare);
        HashMap<String, Double> sorted = new HashMap<>();
        //we don't need a hashmap for this. only need list of visited nodes.
        //maybe chance this to HashSet
        HashSet<String> visited = new HashSet<>();
        //sorted.put(start, 0.0);
        //this visited is ONLY for nodes we process from the queue, NOT those we simply see while adjacent to this node.
        //visited.add(start);
        nodes.get(start).setDist(0.0);
        //we're going to all this trouble bc it will be prioritized for us.
        ShortestPathQueueObject beginning = new ShortestPathQueueObject(start, 0.0);
        queue.add(beginning);
        while (queue.size() != 0) {
            //take highest priority out of queue
            //both q.peak & q.poll will get q(0) but poll deletes it from queue
            ShortestPathQueueObject first = queue.poll();
            Node recentNode = nodes.get(first.label);
            if (!visited.contains(first.label)) {
                //purpose: to put all adjacent nodes in queue w SHORTEST distances
                visited.add(first.label);
                for (EdgeImpl e : recentNode.getEdges()) {
                    //if previous 'shortest path' is longer than the new distance?? to node and weight
                    double newShortest = recentNode.getDist() + e.weight;
                    if (nodes.get(e.destination).getDist() > (newShortest)){
                        nodes.get(e.destination).setDist(newShortest);
                    }
                    ShortestPathQueueObject intermediate = new ShortestPathQueueObject(e.destination, nodes.get(e.destination).getDist());
                    queue.add(intermediate);
                }
                sorted.put(first.label, recentNode.getDist());
            }
            //maybe sorted has to go here...
        }
        return sorted;
            //333.compare distance
            //don't put all nodes in initially
            //only for ranking the priority queue: only use when you're revisiting a node
            //1. make a list of visited (empty) and unvisited (all nodes)
            //2. list of each node, shortest distance from A = 0, everything else = infinity or -1
            //3.  move 1st node from unvisited to visited
            //4. for current vertex, visit UNVISITED neighbors and calculate distance from vertex
            //aka the weight of the edge
            //5. if calculated distance is less than 'shortest distance' (which is infitite atm)
            //update 'shortest distance to A
            //6. visit vertex w smallest known distance to A
            // (that's not the one you just did or A=0)
            //7.  move this vertex from unvisited to visited
            //8. record 'previous vertex'
            //9. repeat visiting neighbors w this smaller one (probably use helper fn)
            //if this distance < 'shortest distance',
            //shortest dist = this distance plus current node's dist to A


            //distance from 1st vertex to itself = 0
            //dist to all others = infinity

            //1. visit unvisited vertex w smallest known dist = current
            //2. examine all unvisited neighbors of current
            //& calc distance to each of these, saving shortest dist
            //3.

        }
        public class ShortestPathQueueObject {
            public String label;
            public double distance;

            public ShortestPathQueueObject(String label, double distance) {
                this.label = label;
                this.distance = distance;
            }
        }
}

