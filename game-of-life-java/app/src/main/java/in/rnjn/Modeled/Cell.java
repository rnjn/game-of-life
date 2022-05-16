package in.rnjn.Modeled;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.EMPTY_LIST;

public class Cell {
    private final int id;
    private final List<Cell> neighbours = new ArrayList<>();
    private final LivelinessCheckStrategy livelinessCheckStrategy;
    private boolean alive = false;
    private boolean nextStatus = false;

    public Cell(int id) {
        this(id, false, EMPTY_LIST);
    }

    public Cell(int id, List<Cell> neighbours) {
        this(id, false, neighbours);
    }

    public Cell(int id, boolean isAlive, List<Cell> neighbours) {
        this(id, isAlive, neighbours, new LivelinessCheckStrategy());
    }

    public Cell(int id, boolean isAlive, List<Cell> neighbours, LivelinessCheckStrategy livelinessCheckStrategy) {
        this.id = id;
        this.alive = isAlive;
        this.neighbours.addAll(neighbours == null ? EMPTY_LIST : neighbours);
        this.livelinessCheckStrategy = livelinessCheckStrategy;
    }

    public boolean isAlive() {
        return alive;
    }


    private boolean getNextStatus() {
        int aliveNeighbours = neighbours.stream().mapToInt(value -> value.isAlive() ? 1 : 0).sum();
        return livelinessCheckStrategy.getNextStatus(aliveNeighbours, alive);
    }

    public Cell recordNextStatus(){
        nextStatus = getNextStatus();
        return this;
    }
    public Cell tick() {
        alive = nextStatus;
        return this;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return id == cell.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public List<Cell> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Cell> neighbours) {
        this.neighbours.clear();
        this.neighbours.addAll(neighbours == null ? EMPTY_LIST : neighbours);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "id=" + id +
                ", alive=" + alive +
                '}';
    }
}
