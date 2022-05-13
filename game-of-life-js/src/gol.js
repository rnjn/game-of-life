const createGrid = function(length, breadth) {
    return new Array(length).fill(0).map(() => new Array(breadth).fill(0));
};
    

const seedGrid = function(grid, seeds) {
    seeds.forEach(element => {
        grid[element[0]][element[1]] = 1;
    });
    return grid;
};

const seedRandomGrid = function(grid, numberOfLiveCells) {
    var length = grid.length;
    var breadth = grid[0].length;
    var randomArray = Array.apply(null, {length: numberOfLiveCells}).map(function(){
        return [Math.floor(Math.random() * length), Math.floor(Math.random() * breadth)];
    });
    return seedGrid(grid, randomArray);
};

const cloneGrid = function(grid) {
    return JSON.parse(JSON.stringify(grid));
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
    
    return grid.map((row, i) => { 
            return row.map((cell, j) => {
                const neighbourCount = countNeighbours(i, j);
                if(neighbourCount === 3  || (cell == 1 && neighbourCount === 2)) {
                    return 1;
                }
                return 0;
            });
        });
    }

const gridEquals = function(grid1, grid2) {
    if(grid1.length !== grid2.length) {
        return false;
    }
    for (var i = 0; i < grid1.length; i++) {
        for (var j = 0; j < grid1[0].length; j++) {
            if(grid1[i][j] !== grid2[i][j]) {
                return false;
            }
        }
    }
    return true;
};

const serialise = function (grid) {
    const header = "_".repeat(grid[0].length + 2);
    var output = header;
    for (var i = 0; i < grid.length; i++) {
        output += "\n|";
        for (var j = 0; j < grid[0].length; j++) {
            output += grid[i][j] ? "*" : " ";
        }
        output += "|";
    }
    output += "\n";
    output += "-".repeat(grid[0].length + 2);
    output += "\n";
    return output;
};

module.exports = {
    createGrid : createGrid,
    seedGrid : seedGrid,
    seedRandomGrid : seedRandomGrid,
    tick: tick,
    gridEquals: gridEquals,
    serialise: serialise,
    cloneGrid: cloneGrid
}