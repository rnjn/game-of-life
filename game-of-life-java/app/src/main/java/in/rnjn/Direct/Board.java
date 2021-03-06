package in.rnjn.Direct;

import in.rnjn.GameOfLifeBoard;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Board implements GameOfLifeBoard {
    private int[][] board;

    public Board(int length, int breadth, int numberOfAliveCells) {
        board = new int[length][breadth];
        setAliveCellsRandomly(numberOfAliveCells);
    }

    public Board(int length, int breadth, List<int[]> liveCells) {
        board = new int[length][breadth];
        liveCells.forEach(indexes -> board[indexes[0]][indexes[1]] = 1);
    }

    private void setAliveCellsRandomly(int numberOfAliveCells) {
        Random random = new Random();
        random.ints(numberOfAliveCells * 10L,
                0, board.length * board[0].length)
                .distinct()
                .limit(numberOfAliveCells)
                .forEach(index -> board[index / board.length][index % board.length] = 1);
    }

    public int[][] getLatestBoardCopy() {
        int[][] copyOfBoard = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            copyOfBoard[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return copyOfBoard;
    }

    @Override
    public Board tick() {
        int[][] newBoard = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int numberOfNeighbours = countNeighbours(i, j);
                if (numberOfNeighbours == 3 || (board[i][j] == 1 && numberOfNeighbours == 2)) {
                    newBoard[i][j] = 1;
                }
            }
        }
        this.board = newBoard;
        return this;
    }

    private int countNeighbours(int i, int j) {
        return getCell(i - 1, j - 1) + getCell(i - 1, j) + getCell(i - 1, j + 1) +
                getCell(i, j - 1) + getCell(i, j + 1) +
                getCell(i + 1, j - 1) + getCell(i + 1, j) + getCell(i + 1, j + 1);

    }

    private int getCell(int x, int y) {
        if (x < 0 || y < 0 || x > board.length - 1 || y > board[0].length - 1) return 0;
        else return board[x][y];
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("_".repeat(board[0].length + 2));
        for (int[] ints : board) {
            output.append("\n|");
            for (int j = 0; j < board[0].length; j++) {
                output.append(ints[j] == 1 ? "*" : " ");
            }
            output.append("|");
        }
        output.append("\n");
        output.append("-".repeat(board[0].length + 2));
        output.append("\n");
        return output.toString();
    }

}
