package in.rnjn.Modeled;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RectangularBoardLayoutStrategyTest {
    @Test
    void shouldAssignNeighbours3x3() {
        ArrayList<Cell> cells = new ArrayList<>();
        IntStream.range(0, 9).forEach(id -> cells.add(new Cell(id)));
        List<Cell> assignedCells = new RectangularBoardLayoutStrategy().assignNeighbours(3, cells);
        assertEquals(3, getCellById(assignedCells, 0).getNeighbourCount());
        assertEquals(5, getCellById(assignedCells, 1).getNeighbourCount());
        assertEquals(3, getCellById(assignedCells, 2).getNeighbourCount());
        assertEquals(5, getCellById(assignedCells, 3).getNeighbourCount());
        assertEquals(8, getCellById(assignedCells, 4).getNeighbourCount());
        assertEquals(5, getCellById(assignedCells, 5).getNeighbourCount());
        assertEquals(3, getCellById(assignedCells, 6).getNeighbourCount());
        assertEquals(5, getCellById(assignedCells, 7).getNeighbourCount());
        assertEquals(3, getCellById(assignedCells, 8).getNeighbourCount());
    }

    @Test
    void shouldAssignNeighbours3x5() {
        ArrayList<Cell> cells = new ArrayList<>();
        IntStream.range(0, 15).forEach(id -> cells.add(new Cell(id)));
        List<Cell> assignedCells = new RectangularBoardLayoutStrategy().assignNeighbours(5, cells);
        assertEquals(3, getCellById(assignedCells, 0).getNeighbourCount());
        assertEquals(5, getCellById(assignedCells, 1).getNeighbourCount());
        assertEquals(5, getCellById(assignedCells, 2).getNeighbourCount());
        assertEquals(5, getCellById(assignedCells, 3).getNeighbourCount());
        assertEquals(3, getCellById(assignedCells, 4).getNeighbourCount());

        assertEquals(5, getCellById(assignedCells, 5).getNeighbourCount());
        assertEquals(8, getCellById(assignedCells, 6).getNeighbourCount());
        assertEquals(8, getCellById(assignedCells, 7).getNeighbourCount());
        assertEquals(8, getCellById(assignedCells, 8).getNeighbourCount());
        assertEquals(5, getCellById(assignedCells, 9).getNeighbourCount());
    }

    private Cell getCellById(List<Cell> cells, int id) {
        return cells.stream().filter(cell -> cell.getId() == id).findFirst().get();
    }
}