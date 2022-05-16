package in.rnjn.Modeled;

import in.rnjn.Direct.Board;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TwoDimensionalBoardTest {
    @Test
    void shouldHaveDimensions() {
        TwoDimensionalBoard board = new TwoDimensionalBoard(10, 10);
        assertEquals(10, board.getBreadth());
        assertEquals(10, board.getLength());
    }

    @Test
    void shouldHaveCorrectNumberOfCells() {
        TwoDimensionalBoard board = new TwoDimensionalBoard(10, 10);
        assertEquals(100, board.getNumberOfCells());
    }

    @Test
    void shouldStartByCreatingCellsRandomly() {
        TwoDimensionalBoard board = new TwoDimensionalBoard(10, 10);
        Map<Integer, Boolean> cells = board.start().getCells();
        long numberOfAliveCells = cells.values().stream().filter(b -> b) .count() ;
        assertEquals(25, numberOfAliveCells);
    }

    @Test
    void shouldStartWithSeedIfProvided() {
        TwoDimensionalBoard board = new TwoDimensionalBoard(10, 10);
        Map<Integer, Boolean> cells = board.start(Arrays.asList(1, 2, 3)).getCells();
        long numberOfAliveCells = cells.values().stream().filter(b -> b).count();
        long numberOfDeadCells = cells.values().stream().filter(b -> !b).count();
        assertEquals(100, cells.values().stream().count());
        assertEquals(3, numberOfAliveCells);
        assertEquals(97, numberOfDeadCells);
    }

    @Test
    void shouldAssignNeighboursToCells() {
        TwoDimensionalBoard board = new TwoDimensionalBoard(3, 3);
        board.start(Arrays.asList(1, 2, 3));
        assertTrue(board.tick().getCells().get(4));
    }
    @Test
    void shouldAssignNeighboursToCellsAnotherCase() {
        TwoDimensionalBoard board = new TwoDimensionalBoard(3, 3);
        assertFalse(board.start(Arrays.asList(1, 2, 3, 4, 5)).tick().getCells().get(4));
    }

    @Test
    void shouldPrintToString() {
        int length = 3;
        int breadth = 3;
        TwoDimensionalBoard board = new TwoDimensionalBoard(length, breadth);
        String boardToString = board.toString();
        assertTrue(boardToString.contains("-----"));
        assertTrue(boardToString.contains("_____"));
        boardToString = board.start().toString();
        assertEquals( breadth + 2 + ((length + 2) * (breadth + 2)) , boardToString.toCharArray().length);
    }

    private Cell getCellById(List<Cell> cells, int id) {
        return cells.stream().filter(cell -> cell.getId() == id).findFirst().get();
    }
}