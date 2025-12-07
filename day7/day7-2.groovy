def input = new File('day7.txt').text.trim()
def grid = input.split('\n')
def height = grid.size()
def width = grid[0].size()
def startX = -1
def startY = -1
for (int y = 0; y < height; y++) {
    def x = grid[y].indexOf('S')
    if (x != -1) {
        startX = x
        startY = y
        break
    }
}

def dp = new long[height][width]
for (int x = 0; x < width; x++) {
    dp[height - 1][x] = 1L
}

for (int y = height - 2; y >= 0; y--) {
    for (int x = 0; x < width; x++) {
        def nextY = y + 1
        def nextChar = grid[nextY][x]
        if (nextChar == '^') {
            def leftX = x - 1
            def rightX = x + 1

            def paths = 0L
            if (leftX >= 0) {
                paths += dp[nextY][leftX]
            }
            if (rightX < width) {
                paths += dp[nextY][rightX]
            }
            dp[y][x] = paths
        } else {
            dp[y][x] = dp[nextY][x]
        }
    }
}

println dp[startY][startX]
