/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AStar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.function.Predicate;

/**
 *
 * @author dhuant
 */
public class Cluster extends ArrayList<Node> implements Serializable{
    private transient final Comparator<Node> c = new Comparator<Node>() {
        @Override
        public int compare(Node i, Node j) {
            //calculate f(n) = g(n) + h(n)
            //should make it so edge with lowest cost is chosen
            //shouldn't have to directly compare Nodes, just Edges (no g ohterwise)
            //include ISDESTINATION!!!
            int fi = i.f();
            int fj = j.f();
            if (fi > fj) {
                return 1;
            } else if (fj > fi) {
                return -1;
            } else {
                return 0;
            }
        }
    };
    transient PriorityQueue<Node> OPEN = new PriorityQueue<>(c);
    transient HashSet<Node> CLOSED = new HashSet<>();
    transient Predicate<Node> inCLOSED = new Predicate<Node>() {
            @Override
            public boolean test(Node N) {
                return CLOSED.contains(N);
            }
        };
    public Cluster(){
        
    }
    
    public Cluster(Node a){
        this.add(a);
    }
    
    public Cluster(Collection<Node> a){
        this.addAll(a);
    }
    
    public void print(ArrayList<Node> Route){
        System.out.println("Begin:");
        for (int i = 0; i < Route.size(); i++) {
            System.out.println("[" + Route.get(i).getName() + "]");
        }
        System.out.println("End");
    }
    
    public String routeString(ArrayList<Node> Route){
        String A = "Begin:\n";
        for (int i = 0; i < Route.size(); i++) {
            A = A.concat("[" + Route.get(i).getName() + "]");
        }
        A = A.concat("\nEnd");
        return A;
    }
    
    
    public void AstarB1 (Node Best, Node Dest){
        CLOSED.add(Best);
        openViable(Best, Dest);
    }
    
    public void Astar (Node Best, Node Dest){
        int counter = 0;
        AstarB1(Best, Dest);
        while(!OPEN.peek().equals(Dest)) {
            counter++;
            Node N = OPEN.poll();
            //tieBreaker(N, Dest);
            AstarB1(N, Dest);
            if (counter > 500) {
                Dest.setParent(N);
                return;
            }
        }
        CLOSED.add(OPEN.poll());
        
    }
    
    public ArrayList<Node> routeAstar (Node Start, Node Dest){
        Start.setOrigin(true);
        Start.setParent(Start);
        Astar(Start, Dest);
        ArrayList<Node> Retable = new ArrayList<Node>();
        Retable.add(Dest);
        while(!Retable.contains(Start)) {  
            Node Last = Retable.get(0);
            Retable.add(0, Last.getParent());
        }
        reset();
        return Retable;
    }
    
    public void tieBreaker(Node First, Node Dest) {
        try {
            if (First.f() < OPEN.peek().f()) {
                return;
            } else if (First.f() == OPEN.peek().f()) {
                Node N;
                N = OPEN.poll();
                AstarB1(N, Dest);
                tieBreaker(N, Dest);
            }
        } catch (NullPointerException e) {
            return;
        } catch (Exception e) {
            System.out.println("Tiebreaker broke");
        }
    }

    public void openViable(Node Curr, Node Dest) {
        ArrayList<Node> ApplicableNeighbors = Curr.getNeighborNodes();
        for (Node ClosedUpon : CLOSED) {
            ApplicableNeighbors.remove(ClosedUpon);
        }
        for (Node N : ApplicableNeighbors) {
            N.updateg();
            if (OPEN.contains(N)) {
                if (N.updateg(Curr) < N.g()) {
                    OPEN.remove(N);
                } else if (N.updateg(Curr) > N.g()) {
                    continue;
                }
            }
            N.setParent(Curr);
            N.updateg();
            N.seth(Dest);
            OPEN.add(N);
        }
    }
    
    public void connect(Node A, Node B){
        if (!A.getNeighborNodes().contains(B) && !B.getNeighborNodes().contains(A)) {
            int distance = (int) Math.round(Math.hypot(B.x() - A.x(), B.y() - A.y()));
            A.addNeighbor(new Edge(B, distance));
            B.addNeighbor(new Edge(A, distance));
        }
    }
    
    public void reset() {
        this.forEach((N) -> N.setOrigin(false));
        OPEN = new PriorityQueue<>(c);
        CLOSED = new HashSet<>();
    }
    
}