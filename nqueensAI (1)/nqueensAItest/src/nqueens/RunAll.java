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
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class RunAll {
    public static void main(String[] args) {
        // Generate large number of problems
        // An NQueens board is represented as an eight-element array, one element for
        // each column. Each element contains the numbers 0-7, representing the row. 
        int numBoards;
        Node node = new Node();
        boolean doPrint;
        try {
            numBoards = Integer.valueOf(args[0]);
            doPrint = Boolean.valueOf(args[1]);
        } catch (Exception e) {
            System.out.println("Two arguments: <# boards> <print boards? true/false>");
            return;
        }

        int numSolns = 0;
        System.out.println("numBoards = " + numBoards);
        List<int[]> boards = generateRandomBoards(numBoards);
        for (int i = 0; i < numBoards; i++) {
            int[] board = boards.get(i);
            Node soln = HillClimb.searchSteepestDescent(board);
            if (soln.value == 0) {
                if (doPrint) {
                    printBoard(soln.state);
                    System.out.println();
                }
                numSolns++;
            }
        }
        int percent =  Math.round(100.0f *((float) numSolns) / numBoards);
        System.out.println("numSolns, steepest descent = " + numSolns + ", " + percent + "%");

        numSolns = 0;
        for (int i = 0; i < numBoards; i++) {
            int[] board = boards.get(i);
            Node soln = HillClimb.searchFirstChoice(board);
            if (soln.value == 0) {
                if (doPrint) {
                    printBoard(soln.state);
                    System.out.println();
                }
                numSolns++;
            }
        }
        percent = Math.round(100.0f *((float) numSolns) / numBoards);
        System.out.println("numSolns, first choice = " + numSolns + ", " + percent + "%");

        numSolns = 0;
        for (int i = 0; i < numBoards; i++) {
            int[] board = boards.get(i);
            Node soln = SimulatedAnnealing.search(board);
            if (soln.value == 0) {
                if (doPrint) {
                    printBoard(soln.state);
                    System.out.println();
                }
                numSolns++;
            }
        }
        percent = Math.round(100.0f *((float) numSolns) / numBoards);
        System.out.println("numSolns, simulated annealing = " + numSolns + ", " + percent + "%");

//         numSolns = 0;
//         for (int i = 0; i < numBoards; i++) {
//             int[] board = boards.get(i);
//             Node soln = RandomRestart.search(board, 25);
//             if (soln.value == 0) {
//                 if (doPrint) {
//                     printBoard(soln.state);
//                     System.out.println();
//                 }
//                 numSolns++;
//             }
//         }
//         System.out.println("numSolns, random-restart = " + numSolns);
    }
    
    private static List<int[]> generateRandomBoards(int numBoards) {
        List<int[]> boards = new ArrayList<int[]>(numBoards);
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < numBoards; i++) {
            // we initialize each puzzle to the same thing, but...
            int[] board = new int[8];
            for (int j = 0; j < 8; j++) {
                board[j] = rand.nextInt(8);
            }
            boards.add(board);
        }
        return boards;
    }
    
    private static void printBoard(int[] board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < board[i]; j++) {
                System.out.print("_ ");
            }
            System.out.print("Q ");
            for (int j = board[i] + 1; j < 8; j++) {
                System.out.print("_ ");
            }
            System.out.println();
        }
    }
}
