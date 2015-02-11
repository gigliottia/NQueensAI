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
public class HillClimb {
    static Random rand = new Random(System.currentTimeMillis());
    static ArrayList<Node> successors;
    public static Node bestNode(List<Node> successors) 
    {
        int currentNode = Integer.MAX_VALUE; // Set the current node to a very high number so we can thoroughly test the neighbor successors
        Node bestNode = null; // Set bestNode to null since we plan on returning it and we have to initialize it in order to use it
        for (int i = 0; i < successors.size(); i++) // Use a for loop in order to test each successor
        {
            Node neighborNode = successors.get(i); // Get the position i from the successor array and set it as the neighbor node
            neighborNode.value = Nqueens.goalState(neighborNode.state); // Set a value for the neighbor node
            //System.out.println(neighborNode.value); // Test to make sure the proper neighbor node is being used
            if (neighborNode.value < currentNode) // If statement that will only be used if the neighbor node is a lower (and in this case better) value than the current node
            {
                currentNode = neighborNode.value; // Since the neighbor node is a lower value and better than the current node, set the current node to that neighbor node.
                bestNode = neighborNode; // Set the bestNode to the neighbor node since that is the best value we have found thus far.
                
            }
        }
       // System.out.println(bestNode.value); // Test to make sure the proper best node is being used
        return bestNode; // Return the best node found through out the succesor arraylist.
    }
    
    public static Node firstChoice(List<Node> successors, Node currentNode) {
        Node firstChoice = null;
        for (int i = 0; i < 100; i ++) 
        {
            int randomSuccessor = rand.nextInt(successors.size());
            Node randomNode = successors.get(randomSuccessor);
            randomNode.value = Nqueens.goalState(randomNode.state);
            if (randomNode.value < currentNode.value) 
            {
                firstChoice = randomNode;
                break;
            }
        }
        return firstChoice;
    }
    
    public static Node searchFirstChoice(int[] state) 
    {
        Node currentState = new Node(state);
        currentState.value = Nqueens.goalState(state);
        while(true)
        {
            List<Node> successors = Nqueens.successors(currentState);
            Node neighborState = firstChoice(successors, currentState);
            //System.out.println(neighborState.value);
            if (neighborState == null) 
            {
                return currentState;                
            }
            currentState = neighborState; 
        } 
    }
    
    public static Node searchSteepestDescent(int[] state) {
        Node currentState = new Node(state);
        currentState.value = Nqueens.goalState(state);
        while(true)
        {
            List<Node> successors = Nqueens.successors(currentState);
            Node neighborState = bestNode(successors);
            //System.out.println(neighborState.value);
            if (neighborState.value >= currentState.value) 
            {
                return currentState;                
            }
            currentState = neighborState; 
        } 
    }
    
}

