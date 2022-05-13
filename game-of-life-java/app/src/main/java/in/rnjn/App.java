package in.rnjn;

import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) throws InterruptedException {
        Board board = new Board(40, 40, 300);
        System.out.println(board.serialise());
        startGameOfLife(board, 100);
    }

    private static void startGameOfLife(Board board, int iterations) throws InterruptedException {
        for (int i = 0; i < iterations; i++) {
            board.tick();
            System.out.println(board.serialise());
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
