package in.rnjn;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    @Test
    void shouldBeDeadByDefault() {
        assertFalse(new Cell(0, new ArrayList<>()).isAlive());
    }

    @Test
    void shouldHaveIdentifier() {
        Cell cell = new Cell(2, new ArrayList<>());
        assertEquals(2, cell.getId());
    }

    @Test
    void shouldBeAliveOrDeadNextBasedOnNeighbours() {
        List<Cell> neighbours = Arrays.asList(new Cell(1, true, null), new Cell(2, true, null), new Cell(3, true, null));
        assertTrue(new Cell(1, neighbours).getNextStatus());
    }

    @Test
    void shouldBeAliveNextIfIsAliveNowAndHas2LiveNeighbours() {
        List<Cell> neighbours = Arrays.asList(new Cell(1, true, null), new Cell(2, true, null));
        assertTrue(new Cell(1, true, neighbours).getNextStatus());
    }
    @Test
    void shouldBeDeadNextIfIHasMoreThan3LiveNeighbours() {
        List<Cell> neighbours = Arrays.asList(new Cell(1, true, null), new Cell(2, true, null),
                new Cell(3, true, null), new Cell(4, true, null));
        assertFalse(new Cell(1, true, neighbours).getNextStatus());
    }
}