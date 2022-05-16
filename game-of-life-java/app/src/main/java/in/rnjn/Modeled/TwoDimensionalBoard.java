package in.rnjn.Modeled;

import in.rnjn.GameOfLifeBoard;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Collections.EMPTY_LIST;

public class TwoDimensionalBoard implements GameOfLifeBoard {

    private final int length;
    private final int breadth;
    private List<Cell> cells = new ArrayList<Cell>();
    private final RectangularBoardLayoutStrategy layoutStrategy;

    public TwoDimensionalBoard(int length, int breadth) {
        this(length, breadth, new RectangularBoardLayoutStrategy());
    }

    public TwoDimensionalBoard(int length, int breadth, RectangularBoardLayoutStrategy layoutStrategy) {

        this.length = length;
        this.breadth = breadth;
        this.layoutStrategy = layoutStrategy;
    }

    public int getLength() {
        return length;
    }

    public int getBreadth() {
        return breadth;
    }

    public int getNumberOfCells() {
        return length * breadth;
    }

    public TwoDimensionalBoard start() {
        return start(getRandomAliveCells());
    }

    public TwoDimensionalBoard start(List<Integer> aliveCells) {
        cells.clear();

        aliveCells.forEach(id -> cells.add(new Cell(id, true, EMPTY_LIST)));

        IntStream.range(0, getNumberOfCells()).filter(id -> !aliveCells.contains(id)).forEach(id -> {
            cells.add(new Cell(id, new ArrayList<>()));
        });
        layoutStrategy.assignNeighbours(length, this.cells);
        return this;
    }

    private List<Integer> getRandomAliveCells() {
        Random random = new Random();
        return random.ints(getNumberOfCells() * 2L,
                        0, getNumberOfCells())
                .distinct()
                .limit(getNumberOfCells() / 4).boxed().collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("_".repeat(length + 2));
        cells.stream().sorted(Comparator.comparing(o -> o.getId())).forEach(cell -> {
            if (cell.getId() % length == 0) output.append("\n|");
            output.append(cell.isAlive() ? "*" : " ");
            if (cell.getId() % length == length - 1) output.append("|");
        });
        output.append("\n");
        output.append("-".repeat(length + 2));
        output.append("\n");
        return output.toString();
    }

    @Override
    public TwoDimensionalBoard tick() {
        cells.stream().forEach(cell -> cell.recordNextStatus());
        cells.stream().forEach(cell -> cell.tick());
        return this;
    }

    public Map<Integer, Boolean> getCells() {
        return cells.stream().collect(Collectors.toMap(Cell::getId, Cell::isAlive));
    }
}
