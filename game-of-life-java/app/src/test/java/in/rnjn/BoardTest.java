package in.rnjn;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {
    @Test
    void shouldCreateBoard() {
        Board board = new Board(10, 10, 1);
        int[][] latestBoard = board.getLatestBoardCopy();
        assertEquals(10, latestBoard.length);
        assertEquals(10, latestBoard[0].length);
    }

    @Test
    void shouldSeedBoard() {
        ArrayList<int[]> liveCells = new ArrayList<>();
        liveCells.add(new int[]{1, 2});
        liveCells.add(new int[]{2, 3});
        liveCells.add(new int[]{4, 4});
        liveCells.add(new int[]{9, 8});
        int[][] latestBoard = new Board(10, 10, liveCells).getLatestBoardCopy();
        assertEquals(latestBoard[1][2], 1);
        assertEquals(latestBoard[2][3], 1);
        assertEquals(latestBoard[4][4], 1);
        assertEquals(latestBoard[9][8], 1);
        assertEquals(latestBoard[9][9], 0);
    }

    @Test
    void shouldSeedBoardRandomly() {
        Board board = new Board(10, 10, 1);
        int[][] latestBoard = board.getLatestBoardCopy();
        int sum = getNumberOfAliveCells(latestBoard);
        assertEquals(1, sum);

        board = new Board(10, 10, 50);
        assertEquals(50, getNumberOfAliveCells(board.getLatestBoardCopy()));
    }

    @Test
    void shouldTickAndCellComesAliveWith3LiveNeighbours() {
        ArrayList<int[]> liveCells = new ArrayList<>();
        liveCells.add(new int[]{0, 3});
        liveCells.add(new int[]{0, 4});
        liveCells.add(new int[]{0, 5});
        Board board = new Board(10, 10, liveCells);
        board = board.tick();
        assertEquals(1, board.getLatestBoardCopy()[1][4]);
    }

    @Test
    void shouldTickAndAllDeadTheNextTickIfOnly2Alive() {
        ArrayList<int[]> liveCells = new ArrayList<>();
        liveCells.add(new int[]{1, 2});
        liveCells.add(new int[]{2, 3});
        Board board = new Board(10, 10, liveCells);
        board = board.tick();
        assertEquals(0, getNumberOfAliveCells(board.getLatestBoardCopy()));
    }

    @Test
    void shouldTickAndCellDiesIfItHasMoreThan3Neighbours() {
        ArrayList<int[]> liveCells = new ArrayList<>();
        liveCells.add(new int[]{1, 4});
        liveCells.add(new int[]{0, 3});
        liveCells.add(new int[]{0, 4});
        liveCells.add(new int[]{0, 5});
        liveCells.add(new int[]{1, 5});
        Board board = new Board(10, 10, liveCells);
        board = board.tick();
        assertEquals(0, board.getLatestBoardCopy()[1][4]);
    }

    @Test
    void shouldTickAndCellDoesntComeAliveIfItHas2Neighbours() {
        ArrayList<int[]> liveCells = new ArrayList<>();
        liveCells.add(new int[]{0, 3});
        liveCells.add(new int[]{0, 4});
        Board board = new Board(10, 10, liveCells);
        board = board.tick();
        assertEquals(0, board.getLatestBoardCopy()[1][4]);
    }

    @Test
    void shouldTickAndStillBlock() {
        ArrayList<int[]> liveCells = new ArrayList<>();
        liveCells.add(new int[]{3, 6});
        liveCells.add(new int[]{2, 6});
        liveCells.add(new int[]{2, 7});
        Board board = new Board(4, 8, liveCells);
        board = board.tick();
        assertEquals(1, board.getLatestBoardCopy()[3][7]);
        assertEquals(1, board.getLatestBoardCopy()[3][6]);
        assertEquals(1, board.getLatestBoardCopy()[2][6]);
        assertEquals(1, board.getLatestBoardCopy()[2][7]);
        board = board.tick();
        assertEquals(1, board.getLatestBoardCopy()[3][7]);
        assertEquals(1, board.getLatestBoardCopy()[3][6]);
        assertEquals(1, board.getLatestBoardCopy()[2][6]);
        assertEquals(1, board.getLatestBoardCopy()[2][7]);
        board = board.tick();
        assertEquals(1, board.getLatestBoardCopy()[3][7]);
        assertEquals(1, board.getLatestBoardCopy()[3][6]);
        assertEquals(1, board.getLatestBoardCopy()[2][6]);
        assertEquals(1, board.getLatestBoardCopy()[2][7]);
    }

    @Test
    void shouldTickABlinker() {
        ArrayList<int[]> liveCells = new ArrayList<>();
        liveCells.add(new int[]{1, 2});
        liveCells.add(new int[]{2, 2});
        liveCells.add(new int[]{3, 2});
        Board board = new Board(5, 5, liveCells);
        board = board.tick();
        assertEquals(1, board.getLatestBoardCopy()[2][1]);
        assertEquals(1, board.getLatestBoardCopy()[2][2]);
        assertEquals(1, board.getLatestBoardCopy()[2][3]);
        board = board.tick();
        assertEquals(1, board.getLatestBoardCopy()[1][2]);
        assertEquals(1, board.getLatestBoardCopy()[2][2]);
        assertEquals(1, board.getLatestBoardCopy()[3][2]);
        board = board.tick();
        assertEquals(1, board.getLatestBoardCopy()[2][1]);
        assertEquals(1, board.getLatestBoardCopy()[2][2]);
        assertEquals(1, board.getLatestBoardCopy()[2][3]);
    }

    @Test
    void shouldTickAndIfAllAliveThenDeadIn2Steps() {
        Board board = new Board(5, 5, 25);
        assertEquals(25, getNumberOfAliveCells(board.getLatestBoardCopy()));
        board.tick();
        assertEquals(4, getNumberOfAliveCells(board.getLatestBoardCopy()));
        board.tick();
        assertEquals(0, getNumberOfAliveCells(board.getLatestBoardCopy()));
    }

    private int getNumberOfAliveCells(int[][] latestBoard) {
        return Arrays.stream(latestBoard).flatMapToInt(Arrays::stream).sum();
    }
}