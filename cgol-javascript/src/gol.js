const createGrid = function(length, breadth) {
        var grid = [];
        for (var i = 0; i < length; i++) {
            grid[i] = [];
            for (var j = 0; j < breadth; j++) {
                grid[i][j] = 0;
            }
        }
        return grid;
    };

const seedGrid = function(grid, seeds) {
    for (var i = 0; i < seeds.length; i++) {
        grid[seeds[i][0]][seeds[i][1]] = 1;
    }
    return grid;
};

const tick = function(grid) {
    const isNeighbour = function(x, y) {
        return grid[x] && grid[x][y] ? grid[x][y] : 0;
    };

    const countNeighbours = function(x, y) {
        return isNeighbour(x-1,y-1) + isNeighbour(x-1, y) + isNeighbour(x-1, y+1) +
                isNeighbour(x, y-1) + isNeighbour(x, y+1) +
                isNeighbour(x+1, y-1) + isNeighbour(x+1, y) + isNeighbour(x+1, y+1);
    };

    var newGrid = createGrid(grid.length, grid[0].length);
    for (var i = 0; i < grid.length; i++) {
        for (var j = 0; j < grid[0].length; j++) {
            const neighbourCount = countNeighbours(i, j);
            if(neighbourCount === 3) {
                newGrid[i][j] = 1;
                continue;
            }
            if(grid[i][j] == 1 && neighbourCount === 2) {
                newGrid[i][j] = 1;
            }
        }
    }
    return newGrid;
};


module.exports = {
    createGrid : createGrid,
    seedGrid : seedGrid,
    tick: tick,
}