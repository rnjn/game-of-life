package in.rnjn;

import in.rnjn.Direct.Board;
import in.rnjn.Modeled.TwoDimensionalBoard;

import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) throws InterruptedException {
      if(0 < args.length && args[0].equals("0")) {
          System.out.println("Starting with procedural design");
          Board board = new Board(40, 40, 200);
          System.out.println(board);
          startGameOfLife(board, 1);
      }
      else {
          System.out.println("Starting with modeled design");
          TwoDimensionalBoard modeledBoard = new TwoDimensionalBoard(40, 40);
          modeledBoard.start();
          System.out.println(modeledBoard);
          startGameOfLife(modeledBoard, 1);
      }
    }

    private static void startGameOfLife(GameOfLifeBoard board, int iterations) throws InterruptedException {
        for (int i = 0; i < iterations; i++) {
            System.out.println("Iteration No. : " + i );
            board.tick();
            System.out.println(board);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
