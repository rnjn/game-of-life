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

    //TODO : how do you get rid of the if conditions ?
    // This feels rather inelegant. One option is to pad the grid witht 2 extra cells on each edge.
    // Feels even less elegant. Yet another option seems to be to create a 2D array/list. But this also goes against
    // the basic idea that a board can be of any shape. So maybe the option left is to define a new type,
    // for corner and edge cells and put this intelligence there.
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
