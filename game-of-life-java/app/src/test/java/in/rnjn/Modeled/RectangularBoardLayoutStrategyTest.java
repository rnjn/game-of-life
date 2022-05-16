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
        assertEquals(3, getCellById(assignedCells, 0).getNeighbours().stream().count());
        assertEquals(5, getCellById(assignedCells, 1).getNeighbours().stream().count());
        assertEquals(3, getCellById(assignedCells, 2).getNeighbours().stream().count());
        assertEquals(5, getCellById(assignedCells, 3).getNeighbours().stream().count());
        assertEquals(8, getCellById(assignedCells, 4).getNeighbours().stream().count());
        assertEquals(5, getCellById(assignedCells, 5).getNeighbours().stream().count());
        assertEquals(3, getCellById(assignedCells, 6).getNeighbours().stream().count());
        assertEquals(5, getCellById(assignedCells, 7).getNeighbours().stream().count());
        assertEquals(3, getCellById(assignedCells, 8).getNeighbours().stream().count());
    }

    @Test
    void shouldAssignNeighbours3x5() {
        ArrayList<Cell> cells = new ArrayList<>();
        IntStream.range(0, 15).forEach(id -> cells.add(new Cell(id)));
        List<Cell> assignedCells = new RectangularBoardLayoutStrategy().assignNeighbours(5, cells);
        assertEquals(3, getCellById(assignedCells, 0).getNeighbours().stream().count());
        assertEquals(5, getCellById(assignedCells, 1).getNeighbours().stream().count());
        assertEquals(5, getCellById(assignedCells, 2).getNeighbours().stream().count());
        assertEquals(5, getCellById(assignedCells, 3).getNeighbours().stream().count());
        assertEquals(3, getCellById(assignedCells, 4).getNeighbours().stream().count());

        assertEquals(5, getCellById(assignedCells, 5).getNeighbours().stream().count());
        assertEquals(8, getCellById(assignedCells, 6).getNeighbours().stream().count());
        assertEquals(8, getCellById(assignedCells, 7).getNeighbours().stream().count());
        assertEquals(8, getCellById(assignedCells, 8).getNeighbours().stream().count());
        assertEquals(5, getCellById(assignedCells, 9).getNeighbours().stream().count());
    }

    private Cell getCellById(List<Cell> cells, int id) {
        return cells.stream().filter(cell -> cell.getId() == id).findFirst().get();
    }
}