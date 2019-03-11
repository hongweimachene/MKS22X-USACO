import java.util.*;
import java.io.*;


public class USACO {
  private static int[][] map;
  private static int[][] board;
  public static int bronze(String filename) throws FileNotFoundException{
    //try{
      File file = new File(filename);
      Scanner data = new Scanner(file);
      //first line contains dimensions and final elevation of the lake
      String line = data.nextLine();
      String[] given = line.split(" ");
      map = new int[Integer.parseInt(given[0])][Integer.parseInt(given[1])];
      int elevation = Integer.parseInt(given[2]);
      //to transfer the initial elevations from the file onto a map 2d array variable
      int rowN = 0;
      while (data.hasNextLine() && rowN < map.length) {
        String row = data.nextLine();
        String[] elev = row.split(" ");
        for (int i = 0; i < elev.length; i++) {
          map[rowN][i] = Integer.parseInt(elev[i]);
        }
        rowN++;
      }
      //to call the ordered set of cow digging instructions, as the last lines of the file contain the necessary arguments
      while(data.hasNextLine()) {
        String row = data.nextLine();
        String[] commands = row.split(" ");
        stompDig(Integer.parseInt(commands[0]), Integer.parseInt(commands[1]), Integer.parseInt(commands[2]));
      }
      //to set the final elevation of the lake
      finalElev(elevation);
      data.close();
      //return volume of the lake
      return volume();
    //}
    // catch (FileNotFoundException e) {
    //   System.out.println("File not found");
    //   System.exit(1);
    // }
  }

  //digging instructions for the cows
  private static void stompDig(int row, int col, int dig) {
    //grid of 9 cows
    int[][] grid = new int[][] {
      {row,col},
      {row+1,col},
      {row+1,col+1},
      {row+1,col+2},
      {row+2,col},
      {row+2,col+1},
      {row+2,col+2},
      {row,col+1},
      {row,col+2},
    };
    //only the highest cow will be guaranteed to start digging, any cow lower will not
    int max = 0;
    for (int x = 0; x < grid.length; x++) {
      if (map[ grid[x][0] - 1 ][ grid[x][1] - 1 ] > max) {
        max = map[ grid[x][0] - 1][ grid[x][1] - 1 ];
      }
    }
    //if the final dig elevation from the highest cow reaches below any other cow, then that cow is guaranteed to dig to that amount as well
    int destined = max - dig;
    for (int i = 0; i < grid.length; i++){
      if (map[grid[i][0]-1][grid[i][1]-1] >= destined) {
        map[grid[i][0]-1][grid[i][1]-1] = destined;
      }
    }
  }

  //to set the final elevation of the lake
  private static void finalElev(int elev) {
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        map[i][j] = elev - map[i][j];
      }
    }
  }

  //to calculate volume of the lake
  public static int volume() {
    //aggregated depth is the sum of the final values on the map
    int depth = 0;
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (map[i][j] >= 0) {
          depth+=map[i][j];
        }
      }
    }
    return depth * 72 * 72;
  }

  public static int silver(String filename) throws FileNotFoundException{
    File file = new File(filename);
    Scanner data = new Scanner(file);
    //first line contains dimensions and T time
    String line = data.nextLine();
    String[] given = line.split(" ");
    board = new int[Integer.parseInt(given[0])][Integer.parseInt(given[1])];
    //to read in the trees and set them to -1 to ignore them
    int rowN = 0;
    while (data.hasNextLine() && rowN < board.length) {
      String row = data.nextLine();
      for (int i = 0; i < row.length(); i++) {
        if (row.charAt(i) == '*') {
          board[rowN][i] = -1;
        }
      }
      rowN++;
    }
    //last line of file has the end and start points
    String lastLine = data.nextLine();
    String[] commands = lastLine.split(" ");
    //offset coordinates by 1 because nature of the problem and nature of the array, problem starts at 1 while array starts at 0
    return solveBoard(Integer.parseInt(commands[0]) - 1, Integer.parseInt(commands[1]) - 1, Integer.parseInt(commands[2]) - 1, Integer.parseInt(commands[3]) - 1, Integer.parseInt(given[2]));
  }

  //the pattern for this silver problem is to set each square to the sum of its adjacent squares, for T amount of times
  private static int solveBoard(int startX, int startY, int endX, int endY, int steps) {
    //step 0, the starting coordinate
    board[startX][startY] = 1;
    //because the state of the board is constantly changing, a new 2d array must be created to store the resulting values
    int[][] state = new int[board.length][board[0].length];
    for (int i = 0; i < steps; i++) {
      for (int j = 0; j < board.length; j++) {
        for (int k = 0; k < board[j].length; k++) {
          //to ignore trees
          if (board[j][k] >= 0) {
            if (j - 1 >= 0 && board[j-1][k] > 0) {
              state[j][k] += board[j-1][k];
            }
            if (j + 1 < board.length && board[j+1][k] > 0) {
              state[j][k] += board[j+1][k];
            }
            if (k - 1 >= 0 && board[j][k-1] > 0) {
              state[j][k] += board[j][k-1];
            }
            if (k + 1 < board[j].length && board[j][k+1] > 0) {
              state[j][k] += board[j][k+1];
            }
          } else {
            //trees set to -1
            state[j][k] = -1;
          }
        }
      }
      //set the board to its new state, after making each square the sum of its adjacent squares
      board = state;
      //reset the other board into new 2d array to store the next state of the board
      state = new int[board.length][board[0].length];
    }
    //returns the number of ways to reach the ending coordinate
    return board[endX][endY];
  }
}
