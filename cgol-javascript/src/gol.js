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

module.exports = {
    createGrid : createGrid
}