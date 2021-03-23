import java.util.Scanner;
import java.awt.Point;

public class Sudoku {
   private int[][] table = new int[9][9];
   private Scanner scan = new Scanner(System.in);

   public static void main(String[] args){
      Sudoku puzzle = new Sudoku();
   }

   public Sudoku() {
      buildTable();
      solve();
      printTable();
   }

   private void buildTable() {
      String input;
      for (int i = 0; i < table.length; i++) {
         System.out.println("Enter row " + (i + 1));
         input = scan.nextLine();
         for (int j = 0; j < table.length; j++) {
            table[j][i] = Character.getNumericValue(input.charAt(j));
         }
      }
   }

   private boolean solve() {
      Point cur = findFirstZero();
      if (cur == null)
         return true;

      int x = (int) cur.getX();
      int y = (int) cur.getY();
      for (table[x][y] = 9; table[x][y] > 0; table[x][y]--) {
         if (checkAll(x, y) && solve()) {
            return true;
         }
      }
      return false;
   }

   private Point findFirstZero() {
      for (int y = 0; y < table.length; y++) {
         for (int x = 0; x < table.length; x++) {
            if (table[x][y] == 0) {
               return new Point(x, y);
            }
         }
      }
      return null;
   }

   private boolean checkAll(int x, int y) {
      return (checkRow(x, y) && checkColumn(x, y) && checkBox(x, y));
   }

   private boolean checkRow(int x, int y) {
      for (int i = 0; i < table.length; i++) {
         if (i != x && table[i][y] == table[x][y]) {
            return false;
         }
      }
      return true;
   }

   private boolean checkColumn(int x, int y) {

      for (int i = 0; i < table.length; i++) {
         if (i != y && table[x][i] == table[x][y]) {
            return false;
         }
      }
      return true;
   }

   private boolean checkBox(int x, int y) {
      int yOffset = y / 3 * 3;
      int xOffset = x / 3 * 3;
      for (int i = yOffset; i < yOffset + 3; i++) {
         for (int j = xOffset; j < xOffset + 3; j++) {
            if (!(j == x && i == y) && table[j][i] == table[x][y])
               return false;
         }
      }
      return true;
   }

   private void printTable() {
      System.out.println("-------------------");
      for (int i = 0; i < table.length; i++) {
         System.out.print("|");
         for (int j = 0; j < table.length; j++) {
            System.out.print(table[j][i] + "|");
         }
         System.out.println();
         System.out.println("-------------------");
      }
   }
}