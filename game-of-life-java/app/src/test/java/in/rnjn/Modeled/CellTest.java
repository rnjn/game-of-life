package in.rnjn.Modeled;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.EMPTY_LIST;
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
        List<Cell> neighbours = get3Neighbours();
        Cell cell = new Cell(1, neighbours);
        assertTrue(cell.recordNextStatus().tick().isAlive());
    }

    private List<Cell> get3Neighbours() {
        return Arrays.asList(new Cell(1, true, null), new Cell(2, true, null), new Cell(3, true, null));
    }

    @Test
    void shouldBeAliveNextIfIsAliveNowAndHas2LiveNeighbours() {
        List<Cell> neighbours = Arrays.asList(new Cell(1, true, EMPTY_LIST), new Cell(2, true, EMPTY_LIST));
        Cell cell = new Cell(1, true, neighbours);
        assertTrue(cell.recordNextStatus().tick().isAlive());
    }

    @Test
    void shouldBeDeadNextIfIHasMoreThan3LiveNeighbours() {
        List<Cell> neighbours = Arrays.asList(new Cell(1, true, EMPTY_LIST), new Cell(2, true, EMPTY_LIST),
                new Cell(3, true, EMPTY_LIST), new Cell(4, true, EMPTY_LIST));
        Cell cell = new Cell(1, true, neighbours);
        assertFalse(cell.recordNextStatus().tick().isAlive());
    }

    @Test
    void shouldBeEqualById() {
        assertEquals(new Cell(1, EMPTY_LIST), new Cell(1, get3Neighbours()));
        assertNotEquals(new Cell(1, get3Neighbours()), new Cell(2, get3Neighbours()));
        assertNotEquals(new Cell(1, get3Neighbours()).hashCode(), new Cell(2, get3Neighbours()).hashCode());
        assertNotEquals(null, new Cell(1, get3Neighbours()));
    }

    @Test
    void shouldSetNeighbours() {
        Cell cell = new Cell(1, EMPTY_LIST);
        assertEquals(0, cell.getNeighbours().stream().count());
        cell.setNeighbours(null);
        assertEquals(0, cell.getNeighbours().stream().count());
        cell.setNeighbours(get3Neighbours());
        assertEquals(3, cell.getNeighbours().stream().count());
    }

    @Test
    void shouldPrintStatus() {
        assertEquals(new Cell(1, true, EMPTY_LIST).toString(), "Cell{id=1, alive=true}");
    }
}