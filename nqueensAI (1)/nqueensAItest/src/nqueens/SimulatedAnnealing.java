/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nqueens;

import java.util.ArrayList;
import java.util.Random;
import static nqueens.Nqueens.isAttacking;

/**
 *
 * @author Mike
 */
public class SimulatedAnnealing 
{

    
    static Random rand = new Random();

    public static Node search(int[] initialState) 
    {
        Node currentNode = new Node(initialState);
        currentNode.value = Nqueens.goalState(currentNode.state);
        double k = 20.0;
        double lambda = 0.045;
        int limit = 100;
        
        TSchedule sched = new TSchedule(k, lambda, limit);
        int t = 1;
        while (true) 
        {
            double T = sched.T(t);
            if (T == 0.0) 
            {
                return currentNode;
            }
            ArrayList<Node> succsessors = (ArrayList<Node>) Nqueens.successors(currentNode);
            Node nextNode = succsessors.get(rand.nextInt(succsessors.size()));
            nextNode.value = Nqueens.goalState(nextNode.state);
            int deltaE = currentNode.value - nextNode.value;
            if (deltaE > 0) 
            {
                currentNode = nextNode;
            } 
            else 
            {
                double probabilityTrue = Math.exp(((double) deltaE) / T);
                double doubleProb = rand.nextDouble();
                if (doubleProb <= probabilityTrue * 1) 
                {
                    currentNode = nextNode; 
                }
            }
            t++;
        }
    }
    
}
