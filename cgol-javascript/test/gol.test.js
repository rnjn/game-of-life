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
});
