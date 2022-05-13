const gol = require('./src/gol.js');


const render = function(grid) {
    process.stdout.write(`Grid size: ${grid.length} x ${grid[0].length}\n`);
    const header = "-".repeat(grid[0].length + 2);
    var output = header;
    for (var i = 0; i < grid.length; i++) {
        output += "\n|";
        for (var j = 0; j < grid[0].length; j++) {
            output += grid[i][j] ? "*" : " ";
        }
        output += "|";
    }
    output += "\n" + header;
    process.stdout.write(output);
    
};

module.exports = {
    renderGrid : render
}