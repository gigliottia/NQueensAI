/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nqueens;

/**
 *
 * @author Mike
 */
public class Node {
    int[] state;
    int value;
    
    public Node() {
        state = new int[8];
        value = Integer.MAX_VALUE;
    }
    public Node(Node n) {
        this();
        System.arraycopy(n.state, 0, state, 0, 8);
    }
    public Node(int[] state) {
        this();
        System.arraycopy(state, 0, this.state, 0, 8);
    }
    // copies a state and puts the queen in the given column in the given position
    public Node(Node n, int column, int newpos) {
        this(n);
        state[column] = newpos;
    }
    
    public void print() {
    }
}
