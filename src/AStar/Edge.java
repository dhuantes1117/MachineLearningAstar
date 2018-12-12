/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AStar;

import java.io.Serializable;

public class Edge implements Serializable{

    private final int g;
    private final Node connection;
    private boolean parent;
    
    
    public Edge(Node connection, int g) {
        this.connection = connection;
        this.g = g;
    }

    /**
     * @return the g
     */
    public int g() {
        return g;
    }

    /**
     * @return the connection
     */
    public Node getConnection() {
        return connection;
    }

    /**
     * @return the parent
     */
    public boolean isParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(boolean parent) {
        this.parent = parent;
    }

}
