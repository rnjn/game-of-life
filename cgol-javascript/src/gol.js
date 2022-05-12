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

module.exports = {
    createGrid : createGrid,
    seedGrid : seedGrid
}