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

def visited = [] as Set
def q = [[startX, startY]]
visited.add("${startX},${startY}")

def splits = 0
while (q) {
    def current = q.remove(0)
    def x = current[0]
    def y = current[1]
    def nextY = y + 1
    if (nextY >= height) {
        continue
    }

    def nextX = x
    def nextChar = grid[nextY][nextX]
    if (nextChar == '^') {
        def leftX = nextX - 1
        def rightX = nextX + 1
        def branchesCreated = 0
        if (leftX >= 0) {
            def leftKey = "${leftX},${nextY}"
            if (!visited.contains(leftKey)) {
                visited.add(leftKey)
                q.add([leftX, nextY])
                branchesCreated++
            }
        }

        if (rightX < width) {
            def rightKey = "${rightX},${nextY}"
            if (!visited.contains(rightKey)) {
                visited.add(rightKey)
                q.add([rightX, nextY])
                branchesCreated++
            }
        }

        if (branchesCreated > 0) {
            splits++
        }
    } else {
        def nextKey = "${nextX},${nextY}"
        if (!visited.contains(nextKey)) {
            visited.add(nextKey)
            q.add([nextX, nextY])
        }
    }
}

println splits
