const cgol = require('../src/gol.js');

test("creates a grid", () => {
    var grid = cgol.createGrid(10, 10);
    expect(grid.length).toBe(10);
    expect(grid[0].length).toBe(10);
});
