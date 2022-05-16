const gol = require('./src/gol.js');


const start = async function(iterations, gridLength, gridHeight, numberOfLiveCells) {
    const sleep = function(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
    };
    const printGrid = function(output) {
        console.clear();
        console.log(`Iteration: ${i}`);
        console.log(gol.serialise(output));
    };

    var grid = gol.seedRandomGrid(gol.createGrid(gridLength, gridHeight), numberOfLiveCells);
    var prevGrid = gol.cloneGrid(grid);
    for(var i = 0; i < iterations; i++) {
        grid = gol.tick(grid);
        if(gol.gridEquals(grid, prevGrid)) {
            console.log(`Stable after ${i} iterations`);
            break;
        }
        printGrid(grid);
        prevGrid = gol.cloneGrid(grid);
        await sleep(1000);
    }
};

start(120, 40, 40, 800);

