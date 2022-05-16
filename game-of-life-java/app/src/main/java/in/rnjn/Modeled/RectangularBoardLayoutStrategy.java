package in.rnjn.Modeled;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RectangularBoardLayoutStrategy {
    public List<Cell> assignNeighbours(int length, List<Cell> cells) {
        cells.stream().forEach(cell -> {
            List<Integer> neighbourIds = getNeighbourIds(length, cell.getId());
            cell.setNeighbours(
                    cells.stream()
                            .filter(c -> neighbourIds.contains(c.getId()))
                            .collect(Collectors.toList()));
        });
        return cells;
    }

    private List<Integer> getNeighbourIds(int length, int id) {
        if (id % length == 0)
            return Arrays.asList(id - length, id - length + 1, id + 1, id + length, id + length + 1);
        if (id % length == length - 1)
            return Arrays.asList(id - length, id - length - 1, id - 1, id + length - 1, id + length);
        return Arrays.asList(
                id - length - 1, id - length, id - length + 1,
                id - 1, id + 1,
                id + length - 1, id + length, id + length + 1);

    }
}
