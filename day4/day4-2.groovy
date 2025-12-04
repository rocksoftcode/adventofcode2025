def grid = new File('day4.txt').readLines()*.toCharArray()
def total = 0

while (true) {
    def result = 0
    for (i in 0..<grid.size()) {
        for (j in 0..<grid[i].length) {
            if (grid[i][j] != '@' as char) continue
            def n = i == 0 ? '.' : grid[i - 1][j]
            def ne = j == grid[i].length - 1 || i == 0 ? '.' : grid[i - 1][j + 1]
            def nw = j == 0 || i == 0 ? '.' : grid[i - 1][j - 1]
            def s = i == grid.size() - 1 ? '.' : grid[i + 1][j]
            def se = j == grid[i].length - 1 || i == grid.size() - 1 ? '.' : grid[i + 1][j + 1]
            def sw = j == 0 || i == grid.size() - 1 ? '.' : grid[i + 1][j - 1]
            def w = j == 0 ? '.' : grid[i][j - 1]
            def e = j == grid[i].length - 1 ? '.' : grid[i][j + 1]
            def all = [n, ne, nw, s, se, sw, w, e]
            if (all.findAll { it == '@' as char }.size() < 4) {
                grid[i][j] = '.'
                result++
            }
        }
    }
    if (result == 0) break
    else total += result
}

println total
