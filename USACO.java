import java.util.*;
import java.io.*;


public class USACO {
  public static int bronze(String filename) throws FileNotFoundException {
    File file = new File(filename);
    Scanner data = new Scanner(file);
    ArrayList<String[]> lines = new ArrayList<String[]>();
    while (data.hasNextLine()){
      String line = data.nextLine();
      lines.add(line.split(" "));
    }

  }
  private static void stompDig(int row, int col, int dig) {

  }
}
