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