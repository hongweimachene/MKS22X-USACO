import java.util.*;
import java.io.*;


public class USACO {
  private static int[][] map;
  public static int bronze(String filename){
    try{
      File file = new File(filename);
      Scanner data = new Scanner(file);
      String line = data.nextLine();
      String[] given = line.split(" ");
      map = new int[Integer.parseInt(given[0])][Integer.parseInt(given[1])];
      int rowN = 0;
      while (data.hasNextLine() && rowN < map.length) {
        String row = data.nextLine();
        String[] elev = row.split(" ");
        for (int i = 0; i < elev.length; i++) {
          map[rowN][i] = Integer.parseInt(elev[i]);
        }
        rowN++;
      }
      while(data.hasNextLine()) {
        String row = data.nextLine();
        String[] commands = row.split(" ");
        stompDig(Interger.parseInt(commands[0]), Integer.parseInt(commands[1]), Integer.parseInt(commands[2]));
      }

    }
    catch (FileNotFoundException e) {

    }

  }
  private static void stompDig(int row, int col, int dig) {
    int[][] grid = new int[][] {
      {row+1,col},
      {row+1,col+1},
      {row+1,col+2},
      {row+2,col},
      {row+2,col+1},
      {row+2,col+2},
      {row,col+1},
      {row,col+2},
    };
    int count = dig;
    while (count > 0) {
      map[row][col] = map[row][col] - 1;
      for (int i = 0; i < grid.length; i++){
        if (map[grid[i][0]][grid[i][1]] >= map[row][col]) {
          map[grid[i][0]][grid[i][1]] = map[grid[i][0]][grid[i][1]] - 1;
        }
      }
      count--;
    }
  }
}
