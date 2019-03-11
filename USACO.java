import java.util.*;
import java.io.*;


public class USACO {
  private static int[][] map;
  private static int[][] board;
  public static int bronze(String filename) throws FileNotFoundException{
    //try{
      File file = new File(filename);
      Scanner data = new Scanner(file);
      String line = data.nextLine();
      String[] given = line.split(" ");
      map = new int[Integer.parseInt(given[0])][Integer.parseInt(given[1])];
      int elevation = Integer.parseInt(given[2]);
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
        stompDig(Integer.parseInt(commands[0]), Integer.parseInt(commands[1]), Integer.parseInt(commands[2]));
      }
      finalElev(elevation);
      data.close();
      return volume();
    //}
    // catch (FileNotFoundException e) {
    //   System.out.println("File not found");
    //   System.exit(1);
    // }
  }
  private static void stompDig(int row, int col, int dig) {
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
    int max = 0;
    for (int x = 0; x < grid.length; x++) {
      if (map[ grid[x][0] - 1 ][ grid[x][1] - 1 ] > max) {
        max = map[ grid[x][0] - 1][ grid[x][1] - 1 ];
      }
    }
    int destined = max - dig;
    for (int i = 0; i < grid.length; i++){
      if (map[grid[i][0]-1][grid[i][1]-1] >= destined) {
        map[grid[i][0]-1][grid[i][1]-1] = destined;
      }
    }
  }

  private static void finalElev(int elev) {
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        map[i][j] = elev - map[i][j];
      }
    }
  }
  public static int volume() {
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
    String line = data.nextLine();
    String[] given = line.split(" ");
    board = new int[Integer.parseInt(given[0])][Integer.parseInt(given[1])];
    int rowN = 0;
    while (data.hasNextLine() && rowN < board.length) {
      String row = data.nextLine();
      String[] field = row.split(" ");
      for (int i = 0; i < field.length; i++) {
        if (field[i].equals("*")) {
          board[rowN][i] = -1;
        }
      }
      rowN++;
    }
    String lastLine = data.nextLine();
    String[] commands = lastLine.split(" ");
    solveBoard()
  }

  private static void solveBoard(int startX, int startY, int endX, int endY, int steps) {

  }
}
