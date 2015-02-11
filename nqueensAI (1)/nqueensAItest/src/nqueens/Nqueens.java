/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nqueens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Mike
 */
public class Nqueens {
    
     

    public static int goalState(int[] state) 
    {
        int goal = 0;
        for (int i = 0; i < state.length; i++) 
        {
            for (int l = i + 1; l < state.length; l++) 
           {
                goal += isAttacking(i, state[i], l, state[l]) ? 1 : 0;
           }


        }
        return goal;
    }
    
       public static boolean isAttacking(int row1, int column1, int row2, int column2) 
    {
        if (row1 == row2 || Math.abs(column2 - column1) == Math.abs(row2 - row1))
            return true;
        else
        return false;
    }
      
    public static List<Node> successors(Node node) {
        List<Node> succ = new ArrayList<Node>(56);
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++) {
                if(j == node.state[i]) continue;
                Node n = new Node(node, i, j);
                succ.add(n);
            }
        }
        return succ;
    }
}
