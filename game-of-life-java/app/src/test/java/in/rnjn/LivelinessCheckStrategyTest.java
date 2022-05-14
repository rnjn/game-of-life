package in.rnjn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LivelinessCheckStrategyTest {
    @Test
    void shouldBeAliveWith3AliveNeighbours() {
        LivelinessCheckStrategy livenessCheckStrategy = new LivelinessCheckStrategy();
        assertTrue(livenessCheckStrategy.getNextStatus(3,false));
        assertTrue(livenessCheckStrategy.getNextStatus(3,true));
    }

    @Test
    void shouldBeAliveWith2AliveNeighbours() {
        LivelinessCheckStrategy livenessCheckStrategy = new LivelinessCheckStrategy();
        assertFalse(livenessCheckStrategy.getNextStatus(2,false));
        assertTrue(livenessCheckStrategy.getNextStatus(2,true));
    }

    @Test
    void shouldBeDeadWith1AliveNeighbour() {
        LivelinessCheckStrategy livenessCheckStrategy = new LivelinessCheckStrategy();
        assertFalse(livenessCheckStrategy.getNextStatus(1,false));
        assertFalse(livenessCheckStrategy.getNextStatus(1,true));
    }

    @Test
    void shouldBeDeadWithMoreThan3AliveNeighbours() {
        LivelinessCheckStrategy livenessCheckStrategy = new LivelinessCheckStrategy();
        assertFalse(livenessCheckStrategy.getNextStatus(4,false));
        assertFalse(livenessCheckStrategy.getNextStatus(4,true));
    }
}