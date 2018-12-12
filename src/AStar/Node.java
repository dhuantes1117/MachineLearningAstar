/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AStar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 *
 * @author dhuant
 */
abstract public class Node implements State{
    private int x;
    private int y;
    private int g;
    private int h;
    private boolean origin;
    private String Name;
    private Node Parent;
    private final Comparator<Edge> c = new Comparator<Edge>() {
        @Override
        public int compare(Edge i, Edge j) {
            //calculate f(n) = g(n) + h(n)
            //should make it so edge with lowest cost is chosen
            //shouldn't have to directly compare Nodes, just Edges (no g ohterwise)
            //include ISDESTINATION!!!
            int fi = i.g();
            int fj = j.g();
            if (!(i.isParent() && j.isParent()) && (i.isParent() || j.isParent())) {
                if (i.isParent()) {
                    return -1;
                } else if (j.isParent()) {
                    return 1;
                }
            }
            if (fi > fj) {
                return 1;
            } else if (fj > fi) {
                return -1;
            } else {
                return 0;
            }
        }
    };
    private PriorityQueue<Edge> Neighbors;
    private TreeSet<Edge> NeighborsTree;
    //Building a map -> a treeset would ensure that no nodes are duplicated, as well as
    //allowing for Cluster.connect to act without checks
    //The goal is to create a package that if a class in the main method implements
    //the interface State, by adding a group of them to a Cluster (Also defined in this class),
    //which is capable of AStar. A caveat of the cluster is it must have a database structure in place
    //in order to maintain the changes and edits that are made, as well as having an easy to use addition
    //method as truly, the machine learning part should mean that this program creates and continually adds to
    //this Cluster
    public Node(){
        //Assumably for the purpose of serialization
    }
    
    public Node(int x, int y, boolean origin) {
        this.Name = "Bucharest";
        this.x = x;
        this.y = y;
        this.h = Integer.MAX_VALUE;
        this.origin = origin;
    }

    public Node(String Name, int x, int y, boolean origin) {
        this.x = x;
        this.y = y;
        this.h = Integer.MAX_VALUE;
        this.origin = origin;
    }

    public Node(String Name, int x, int y) {
        this.Name = Name;
        this.x = x;
        this.y = y;
        this.h = Integer.MAX_VALUE;
        this.origin = false;
    }

    public Node(int x, int y) {
        this.Name = "Bucharest";
        this.x = x;
        this.y = y;
        this.h = Integer.MAX_VALUE;
        this.origin = false;
    }
    
    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @return the Neighbors
     */
    public PriorityQueue<Edge> getNeighbors() {
        return Neighbors;
    }
    
    public ArrayList<Node> getNeighborNodes(){
        ArrayList<Node> Retable = new ArrayList<>();
        for (Edge E : Neighbors) {
            Retable.add(E.getConnection());
        }
        return Retable;
    }

    /**
     * @return the h
     */
    public int h() {
        return h;
    }

    /**
     * @param h the h to set
     */
    private void seth(int h) {
        this.h = h;
    }

    /**
     * @return the origin
     */
    public boolean isOrigin() {
        return origin;
    }

    public void seth(Node Dest) {
        setHueristic(Dest);
    }

    /**
     * @return the x
     */
    public int x() {
        return x;
    }

    /**
     * @return the y
     */
    public int y() {
        return y;
    }
    
    public void move(int i, int j){
        this.x = i;
        this.y = j;
    }
    
    /**
     * @return the g cost
     */
    public int g() {
        return this.g;
    }
    
    /**
     * @return the f cost
     */
    public int f() {
        return this.g + this.h;
    }

    /**
     * @param Neighbors the Neighbors to set
     */
    public void setNeighbors(ArrayList<Edge> Neighbors) {
        this.Neighbors = new PriorityQueue<Edge>(c);
        this.Neighbors.addAll(Neighbors);
    }

    public void addNeighbor(Edge Neighbor) {
        this.Neighbors.add(Neighbor);
    }
    /**
     * @param RoomName the RoomName to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }
    //So State extends< Node. Cluster holds States, Astar operates on states, so I can visualize by
    //having states have a class variable regarding the bufferedimage of their visualization, or
    //in the case of quadratics their a and b and c. good practice is to keep seperate,
    //do not hardcode images into Node

    public void updateg() {
        //isOrigin add? or use isDestination to get around
        //the beginning not being able to be it's own parent
        //bc it's not in it's own neighbor's queue
        //isOrigin applicable how? for search only, which IS still required.
        //Means neighbors must be a priorityQueue -> Could make tree set
        if (origin) {
            this.setg(0);
            return;
        }
        Edge ParentEdge = this.Neighbors.peek();
        this.setg(ParentEdge.getConnection().g + ParentEdge.g());
    }
    
    public int updateg(Node N) {
        //give g if parameter was parent
        //return N.g + Neighbors.peek().g();
        for (Edge Neighbor : Neighbors) {
            if (Neighbor.getConnection().equals(N)) {
                return N.g + Neighbor.g();
            }
        }
        return -1;
    }
    
    public void connect(Node B){
        if (!this.getNeighborNodes().contains(B) && !B.getNeighborNodes().contains(this)) {
            int distance = (int) Math.round(Math.hypot(B.x() - this.x(), B.y() - this.y()));
            this.addNeighbor(new Edge(B, distance));
            B.addNeighbor(new Edge(this, distance));
        }
    }
    public void connect(Node B, int distance){
        if (!this.getNeighborNodes().contains(B) && !B.getNeighborNodes().contains(this)) {
            this.addNeighbor(new Edge(B, distance));
            B.addNeighbor(new Edge(this, distance));
        }
    }
    //Watch Forever Amazon Prime
    //t(t-4)^(1/3)

    /**
     * @return the Parent
     */
    public Node getParent() {
        return Parent;
    }

    /**
     * @param Parent the Parent to set
     */
    public void setParent(Node Parent) {
        this.Parent = Parent;
    }

    /**
     * @param g the g to set
     */
    public void setg(int g) {
        this.g = g;
    }
    
    /**
     * @param origin the origin to set
     */
    public void setOrigin(boolean origin) {
        this.origin = origin;
    }
    
    
}
