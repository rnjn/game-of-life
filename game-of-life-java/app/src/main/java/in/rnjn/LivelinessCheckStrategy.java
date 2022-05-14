package in.rnjn;

public class LivelinessCheckStrategy {
    public boolean getNextStatus(int numberOfAliveNeighbours, boolean alive) {
        return numberOfAliveNeighbours == 3 || (alive && numberOfAliveNeighbours == 2);
    }
}
