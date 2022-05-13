const cgol = require('../src/gol.js');

test("creates a grid", () => {
    var grid = cgol.createGrid(10, 10);
    expect(grid.length).toBe(10);
    expect(grid[0].length).toBe(10);
});

test("seeds the grid", () => {
    var grid = cgol.createGrid(10, 10);
    grid = cgol.seedGrid(grid, [[1,2], [2,3], [4,4], [9,8]]);
    expect(grid[1][2]).toBe(1);
    expect(grid[2][3]).toBe(1);
    expect(grid[4][4]).toBe(1);
    expect(grid[9][8]).toBe(1);
});

test("seeds random grid", () => {
    const sumGrid = function(grid) {
        var sum = 0;
        for (var i = 0; i < grid.length; i++) {
            for (var j = 0; j < grid[0].length; j++) {
                sum += grid[i][j] ?? 0;
            }
        }
        return sum;
    };
    
    var grid = cgol.createGrid(100, 100);
    grid = cgol.seedRandomGrid(grid, 10);
    expect(sumGrid(grid)).toBe(10);
});

test("grid equals", () => {
    var grid1 = cgol.createGrid(10, 10);
    var grid2 = cgol.createGrid(10, 10);
    expect(cgol.gridEquals(grid1, grid2)).toBe(true);
    grid1 = cgol.seedGrid(grid1, [[1,2], [2,3], [4,4], [9,8]]);
    grid2 = cgol.seedGrid(grid2, [[1,2], [2,3], [4,4], [9,8]]);
    expect(cgol.gridEquals(grid1, grid2)).toBe(true);
    var grid3 = cgol.seedRandomGrid(grid1, 10);
    expect(cgol.gridEquals(grid1, grid2)).toBe(false);

});

test("clone grid", () => {
    var grid1 = cgol.createGrid(10, 10);
    var grid2 = cgol.createGrid(10, 10);
    grid1 = cgol.seedGrid(grid1, [[1,2], [2,3], [4,4], [9,8]]);
    grid2 = cgol.cloneGrid(grid1);
    expect(cgol.gridEquals(grid1, grid2)).toBe(true);
});

test("ticks the grid, all dead the next tick if only 2 alive", () => {
    var grid = cgol.createGrid(10, 10);
    grid = cgol.seedGrid(grid, [[1,2], [2,3]]);
    grid = cgol.tick(grid);
    expect(grid[1][2]).toBe(0);
    expect(grid[2][3]).toBe(0);
});    

test("ticks the grid, cell alive if 3 neighbours", () => {
    var grid = cgol.createGrid(10, 10);
    grid = cgol.seedGrid(grid, [[0,0], [0,1], [1,1]]);
    grid = cgol.tick(grid);
    expect(grid[0][0]).toBe(1);
});

test("ticks the grid, cell dies if it has more than 3 neighbours", () => {
    var grid = cgol.createGrid(10, 10);
    grid = cgol.seedGrid(grid, [[1,4], [0,3], [0,4], [0,5], [1,5]]);
    grid = cgol.tick(grid);
    expect(grid[1][4]).toBe(0);
});


test("ticks the grid, cell doesn't come alive if it has 2 neighbours", () => {
    var grid = cgol.createGrid(10, 10);
    grid = cgol.seedGrid(grid, [[0,3], [0,4]]);
    grid = cgol.tick(grid);
    expect(grid[1][4]).toBe(0);
});

test("ticks the grid, cell comes alive if it has 3 neighbours", () => {
    var grid = cgol.createGrid(10, 10);
    grid = cgol.seedGrid(grid, [[0,3], [0,4], [0,5]]);
    grid = cgol.tick(grid);
    expect(grid[1][4]).toBe(1);
});

test("ticks the grid, some more tests on edges, still block", () => {
    var grid = cgol.createGrid(4, 8);
    grid = cgol.seedGrid(grid, [[3,6], [2,6], [2,7]]);
    grid = cgol.tick(grid);

    var i1 = cgol.seedGrid(cgol.createGrid(4, 8), [[3,6],[3,7],[2,6], [2,7]]);
    expect(cgol.gridEquals(grid, i1)).toBe(true);

    var i2 = cgol.tick(i1);
    expect(cgol.gridEquals(i2, i1)).toBe(true);
    
    var i3 = cgol.tick(i2);
    expect(cgol.gridEquals(i3, i2)).toBe(true);
    
});


test("ticks the grid, blinker", () => {
    var grid = cgol.createGrid(5, 5);
    grid = cgol.seedGrid(grid, [[1,2], [2,2], [3,2]]);
    grid = cgol.tick(grid);

    expect(cgol.gridEquals(cgol.seedGrid(cgol.createGrid(5, 5), [[2,1], [2,2], [2,3]]), grid)).toBe(true);
    grid = cgol.tick(grid);
    expect(cgol.gridEquals(cgol.seedGrid(cgol.createGrid(5, 5), [[1,2], [2,2], [3,2]]), grid)).toBe(true);
    grid = cgol.tick(grid);
    expect(cgol.gridEquals(cgol.seedGrid(cgol.createGrid(5, 5), [[2,1], [2,2], [2,3]]), grid)).toBe(true);
    grid = cgol.tick(grid);
    expect(cgol.gridEquals(cgol.seedGrid(cgol.createGrid(5, 5), [[1,2], [2,2], [3,2]]), grid)).toBe(true);

});

test("ticks the grid, when all alive, dead in 2 steps", () => {
    var grid = cgol.createGrid(5, 5);
    for (var i = 0; i < grid.length; i++) {
        for (var j = 0; j < grid[0].length; j++) {
            grid[i][j] = 1;
        }
    }
    grid = cgol.tick(grid);
    grid = cgol.tick(grid);
    expect(cgol.gridEquals(cgol.createGrid(5, 5), grid)).toBe(true);
});

test("serialise grid", () => {
    var emptyGrid = cgol.createGrid(1, 1);
    var seededGrid = cgol.seedGrid(cgol.createGrid(1, 1), [[0,0]]);
    var expectedEmpty =    "___" + "\n" +"| |" + "\n" + "---" + "\n";
    var expectedSeeded =    "___" + "\n" +"|*|" + "\n" + "---" + "\n";
    expect(cgol.serialise(emptyGrid)).toBe(expectedEmpty);
    expect(cgol.serialise(seededGrid)).toBe(expectedSeeded);
});