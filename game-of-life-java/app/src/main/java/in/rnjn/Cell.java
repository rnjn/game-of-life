package in.rnjn;

import java.util.List;

public class Cell {
    private final int id;
    private final boolean alive;
    private final List<Cell> neighbours;
    private final LivelinessCheckStrategy livelinessCheckStrategy;

    public Cell(int id, List<Cell> neighbours) {
        this(id, false, neighbours);
    }

    public Cell(int id, boolean isAlive, List<Cell> neighbours) {
        this(id, isAlive, neighbours, new LivelinessCheckStrategy());
    }

    public Cell(int id, boolean isAlive, List<Cell> neighbours, LivelinessCheckStrategy livelinessCheckStrategy) {
        this.id = id;
        this.alive = isAlive;
        this.neighbours = neighbours;
        this.livelinessCheckStrategy = livelinessCheckStrategy;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean getNextStatus() {
        int aliveNeighbours = neighbours.stream().mapToInt(value -> value.isAlive() ? 1 : 0).sum();
        return livelinessCheckStrategy.getNextStatus(aliveNeighbours, alive);
    }

    public int getId() {
        return this.id;
    }
}
